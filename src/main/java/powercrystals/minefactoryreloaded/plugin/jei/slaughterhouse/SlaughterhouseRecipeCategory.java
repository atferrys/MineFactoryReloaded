package powercrystals.minefactoryreloaded.plugin.jei.slaughterhouse;

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
import powercrystals.minefactoryreloaded.tile.machine.mobs.TileEntitySlaughterhouse;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlaughterhouseRecipeCategory extends MachineRecipeCategory<SlaughterhouseRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable animalWidget;
    private final IDrawable arrowWidget;
    private final IDrawable tankOverlay;
    private final IDrawable energyOverlay;
    private final IDrawableAnimated workOverlay;

    private final int energyPerOperation;
    private final int primaryTankSize;
    private final int secondaryTankSize;

    private static final int ANIMAL_WIDGET_X = 24, ANIMAL_WIDGET_Y = 31;
    private static final int ENERGY_X = 140, ENERGY_Y = 15;
    private static final int WORK_X = 150, WORK_Y = 15;

    public SlaughterhouseRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.Slaughterhouse);

        TileEntitySlaughterhouse dummy = new TileEntitySlaughterhouse();
        int workTicks = Math.max(dummy.getWorkMax(), 10);
        energyPerOperation = dummy.getActivationEnergy() * dummy.getWorkMax();
        primaryTankSize = dummy.getTanks()[1].getCapacity();
        secondaryTankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
        );

        animalWidget = guiHelper.createDrawable(
                widgetsTexture,
                16, 32,
                16, 16
        );

        arrowWidget = guiHelper.createDrawable(
                widgetsTexture,
                0, 0,
                22, 15
        );

        tankOverlay = guiHelper.createDrawable(
                texture,
                176, 0,
                16, 60
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
        animalWidget.draw(minecraft, ANIMAL_WIDGET_X, ANIMAL_WIDGET_Y);
        arrowWidget.draw(minecraft, 57, 32);
        energyOverlay.draw(minecraft, ENERGY_X - BG_X, ENERGY_Y - BG_Y + (BAR_HEIGHT - energyOverlay.getHeight()));
        workOverlay.draw(minecraft, WORK_X - BG_X, WORK_Y - BG_Y);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {

        if(mouseX >= ENERGY_X - BG_X && mouseX < ENERGY_X - BG_X + BAR_WIDTH && mouseY >= ENERGY_Y - BG_Y && mouseY < ENERGY_Y - BG_Y + BAR_HEIGHT) {
            return Collections.singletonList(I18n.format("jei.info.mfr.energy", energyPerOperation));
        }

        if(mouseX >= ANIMAL_WIDGET_X && mouseX < ANIMAL_WIDGET_X + animalWidget.getWidth() && mouseY >= ANIMAL_WIDGET_Y && mouseY < ANIMAL_WIDGET_Y + animalWidget.getHeight()) {
            return Arrays.asList(
                    I18n.format("jei.info.mfr.slaughterhouse"),
                    I18n.format("jei.info.mfr.slaughterhouse.1")
            );
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