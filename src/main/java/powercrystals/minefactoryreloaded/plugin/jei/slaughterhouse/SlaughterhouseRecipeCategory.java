package powercrystals.minefactoryreloaded.plugin.jei.slaughterhouse;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.MFRFluids;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.mobs.TileEntitySlaughterhouse;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class SlaughterhouseRecipeCategory extends MachineRecipeCategory<SlaughterhouseRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable tankOverlay;
    private final IDrawable energyOverlay;

    private final int energyPerOperation;
    private final int primaryTankSize;
    private final int secondaryTankSize;

    private static final int ENERGY_X = 140, ENERGY_Y = 15;

    public SlaughterhouseRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.Slaughterhouse);

        TileEntitySlaughterhouse dummy = new TileEntitySlaughterhouse();
        energyPerOperation = dummy.getActivationEnergy() * dummy.getWorkMax();
        primaryTankSize = dummy.getTanks()[1].getCapacity();
        secondaryTankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
        );

        tankOverlay = guiHelper.createDrawable(
                texture,
                176, 0,
                16, 60
        );

        int energyHeight = this.calculateEnergyHeight(energyPerOperation);
        energyOverlay = guiHelper.createDrawable(
                texture,
                176, BAR_HEIGHT + (BAR_HEIGHT - energyHeight),
                BAR_WIDTH, energyHeight
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
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if(mouseX >= ENERGY_X - BG_X && mouseX < ENERGY_X - BG_X + BAR_WIDTH && mouseY >= ENERGY_Y - BG_Y && mouseY < ENERGY_Y - BG_Y + BAR_HEIGHT) {
            return Collections.singletonList(energyPerOperation + " RF");
        }
        return Collections.emptyList();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull SlaughterhouseRecipeWrapper recipeWrapper, IIngredients ingredients) {

        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(0, false, 102 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, primaryTankSize, false, tankOverlay);
        guiFluidStacks.init(1, false, 122 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, secondaryTankSize, false, tankOverlay);

        guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(0));
        guiFluidStacks.set(1, ingredients.getOutputs(FluidStack.class).get(1));

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        List<SlaughterhouseRecipeWrapper> recipes = Collections.singletonList(
                new SlaughterhouseRecipeWrapper(new FluidStack(MFRFluids.pinkSlime, primaryTankSize), new FluidStack(MFRFluids.meat, secondaryTankSize))
        );

        registry.addRecipes(recipes, this.getUid());

    }

}