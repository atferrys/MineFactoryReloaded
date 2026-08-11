package powercrystals.minefactoryreloaded.plugin.jei.harvester;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.MFRFluids;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.plants.TileEntityHarvester;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class HarvesterRecipeCategory extends MachineRecipeCategory<HarvesterRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable tankOverlay;
    private final IDrawable energyOverlay;
    private final IDrawableAnimated workOverlay;

    private final int sludgePerOperation;
    private final int energyPerOperation;
    private final int tankSize;

    private static final int ENERGY_X = 140, ENERGY_Y = 15;
    private static final int WORK_X = 150, WORK_Y = 15;

    public HarvesterRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.Harvester);

        TileEntityHarvester dummy = new TileEntityHarvester();
        int workTicks = Math.max(dummy.getWorkMax(), 10);
        sludgePerOperation = 10;
        energyPerOperation = dummy.getActivationEnergy() * dummy.getWorkMax();
        tankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
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
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull HarvesterRecipeWrapper recipeWrapper, IIngredients ingredients) {

        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(0, false, 122 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, tankSize, true, tankOverlay);

        guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(0));

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        List<HarvesterRecipeWrapper> recipes = Collections.singletonList(
                new HarvesterRecipeWrapper(new FluidStack(MFRFluids.sludge, sludgePerOperation))
        );

        registry.addRecipes(recipes, this.getUid());

    }

}
