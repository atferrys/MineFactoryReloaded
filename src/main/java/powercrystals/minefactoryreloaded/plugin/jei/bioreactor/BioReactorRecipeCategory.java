package powercrystals.minefactoryreloaded.plugin.jei.bioreactor;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.api.plant.IFactoryPlantable;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.MFRFluids;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.processing.TileEntityBioReactor;

import javax.annotation.Nonnull;
import java.util.*;

public class BioReactorRecipeCategory extends MachineRecipeCategory<BioReactorRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable arrowWidget;
    private final IDrawable tankOverlay;
    private final IDrawable efficiencyOverlay;
    private final IDrawableAnimated bufferOverlay;

    private final int tankSize;

    private static final int EFFICIENCY_X = 150, EFFICIENCY_Y = 15;
    private static final int BUFFER_X = 160, BUFFER_Y = 15;

    public BioReactorRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.BioReactor);

        TileEntityBioReactor dummy = new TileEntityBioReactor();
        tankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
        );

        arrowWidget = guiHelper.createDrawable(
                widgetsTexture,
                0, 0,
                22, 15
        );

        tankOverlay = guiHelper.createDrawable(
                texture,
                176, 0,
                TANK_WIDTH, TANK_HEIGHT
        );

        efficiencyOverlay = guiHelper.createDrawable(
                texture,
                176, 60 + (BAR_HEIGHT - BAR_HEIGHT/2),
                BAR_WIDTH, BAR_HEIGHT/2
        );

        bufferOverlay = guiHelper.createAnimatedDrawable(
                guiHelper.createDrawable(
                        texture,
                        185, 60,
                        BAR_WIDTH, BAR_HEIGHT
                ),
                120,
                IDrawableAnimated.StartDirection.BOTTOM,
                false
        );

    }

    @Override
    @Nonnull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        arrowWidget.draw(minecraft, 78, 30);
        efficiencyOverlay.draw(minecraft, EFFICIENCY_X - BG_X, EFFICIENCY_Y - BG_Y + (BAR_HEIGHT - efficiencyOverlay.getHeight()));
        bufferOverlay.draw(minecraft, BUFFER_X - BG_X, BUFFER_Y - BG_Y);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if(mouseX >= EFFICIENCY_X - BG_X && mouseX < EFFICIENCY_X - BG_X + BAR_WIDTH && mouseY >= EFFICIENCY_Y - BG_Y && mouseY < EFFICIENCY_Y - BG_Y + BAR_HEIGHT) {
            return Arrays.asList(
                    I18n.format("jei.info.mfr.bioreactor.efficiency"),
                    I18n.format("jei.info.mfr.bioreactor.efficiency.1"),
                    I18n.format("jei.info.mfr.bioreactor.efficiency.2"),
                    I18n.format("jei.info.mfr.bioreactor.efficiency.3")
            );
        }
        return Collections.emptyList();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull BioReactorRecipeWrapper recipeWrapper, IIngredients ingredients) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiItemStacks.init(0, false, 7 - BG_X, 14 - BG_Y);
        guiFluidStacks.init(0, true, 132 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, tankSize, false, tankOverlay);

        guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
        guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(0));

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        Map<Item, IFactoryPlantable> plantables = MFRRegistry.getPlantables();
        List<BioReactorRecipeWrapper> recipes = new ArrayList<>();

        for(Item plantable : plantables.keySet()) {

            ItemStack plantableStack = new ItemStack(plantable, 1, OreDictionary.WILDCARD_VALUE);

            if(plantableStack.isEmpty()) {
                continue;
            }

            if(plantables.get(plantable).canBePlanted(plantableStack, true)) {
                recipes.add(new BioReactorRecipeWrapper(plantableStack, new FluidStack(MFRFluids.biofuel, tankSize)));
            }

        }

        registry.addRecipes(recipes, this.getUid());

    }

}
