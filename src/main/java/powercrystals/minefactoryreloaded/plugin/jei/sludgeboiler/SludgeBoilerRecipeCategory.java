package powercrystals.minefactoryreloaded.plugin.jei.sludgeboiler;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.core.WeightedRandomItemStack;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.processing.TileEntitySludgeBoiler;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SludgeBoilerRecipeCategory extends MachineRecipeCategory<SludgeBoilerRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable arrowWidget;
    private final IDrawable tankOverlay;
    private final IDrawable energyOverlay;
    private final IDrawableAnimated workOverlay;

    private final int sludgePerOperation;
    private final int energyPerOperation;
    private final int tankSize;

    private static final int ENERGY_X = 140, ENERGY_Y = 15;
    private static final int WORK_X = 150, WORK_Y = 15;

    public SludgeBoilerRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.SludgeBoiler);

        TileEntitySludgeBoiler dummy = new TileEntitySludgeBoiler();
        int workTicks = dummy.getWorkMax();
        sludgePerOperation = dummy.getWorkMax() * 10;
        energyPerOperation = dummy.getActivationEnergy() * dummy.getWorkMax();
        tankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
        );

        arrowWidget = guiHelper.createDrawable(
                widgetsTexture,
                0, 15,
                22, 15
        );

        tankOverlay = guiHelper.createDrawable(
                texture,
                176, 0,
                TANK_WIDTH, TANK_HEIGHT
        );

        int energyHeight = this.calculateEnergyHeight(energyPerOperation);
        energyOverlay = guiHelper.createDrawable(
                texture,
                176, 60 + (BAR_HEIGHT - energyHeight),
                BAR_WIDTH, energyHeight
        );

        workOverlay = guiHelper.createAnimatedDrawable(
                guiHelper.createDrawable(
                        texture,
                        185, 60,
                        BAR_WIDTH, BAR_HEIGHT
                ),
                workTicks,
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
        arrowWidget.draw(minecraft, 73, 30);
        energyOverlay.draw(minecraft, ENERGY_X - BG_X, ENERGY_Y - BG_Y + (BAR_HEIGHT - energyOverlay.getHeight()));
        workOverlay.draw(minecraft, WORK_X - BG_X, WORK_Y - BG_Y);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if(mouseX >= ENERGY_X - BG_X && mouseX < ENERGY_X - BG_X + BAR_WIDTH && mouseY >= ENERGY_Y - BG_Y && mouseY < ENERGY_Y - BG_Y + BAR_HEIGHT) {
            return Collections.singletonList(I18n.format("jei.info.mfr.energy", energyPerOperation));
        }
        return Collections.emptyList();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull SludgeBoilerRecipeWrapper recipeWrapper, IIngredients ingredients) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiItemStacks.init(0, false, 35, 22);
        guiFluidStacks.init(0, true, 122 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, tankSize, true, tankOverlay);

        guiItemStacks.set(0, ingredients.getOutputs(ItemStack.class).get(0));
        guiFluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        List<WeightedRandom.Item> sludgeDrops = MFRRegistry.getSludgeDrops();
        int totalWeight = WeightedRandom.getTotalWeight(sludgeDrops);

        List<SludgeBoilerRecipeWrapper> recipes = sludgeDrops.stream()
                .map(drop -> new SludgeBoilerRecipeWrapper(
                        sludgePerOperation,
                        ((WeightedRandomItemStack) drop).getStack(),
                        (double) drop.itemWeight / totalWeight)
                )
                .collect(Collectors.toList());

        registry.addRecipes(recipes, this.getUid());

    }

}
