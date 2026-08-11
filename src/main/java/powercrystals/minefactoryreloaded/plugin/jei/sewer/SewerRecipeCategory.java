package powercrystals.minefactoryreloaded.plugin.jei.sewer;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.MFRFluids;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.animals.TileEntitySewer;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class SewerRecipeCategory extends MachineRecipeCategory<SewerRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable arrowWidget;
    private final IDrawable tankOverlay;

    private final int primaryTankSize;
    private final int secondaryTankSize;

    private static final int BG_Y = 14;

    private final IDrawable primaryWidget;
    private final IDrawable secondaryWidget;

    public SewerRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.Sewer);

        TileEntitySewer dummy = new TileEntitySewer();
        primaryTankSize = dummy.getTanks()[1].getCapacity();
        secondaryTankSize = dummy.getTanks()[0].getCapacity();
        dummy = null;

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 64
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

        primaryWidget = guiHelper.createDrawable(
                widgetsTexture,
                0, 32,
                16, 16
        );

        secondaryWidget = guiHelper.createDrawable(
                widgetsTexture,
                16, 32,
                16, 16
        );

    }

    @Override
    @Nonnull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        arrowWidget.draw(minecraft, 73, 23);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull SewerRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {

        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        if(recipeWrapper.isPrimary()) {
            guiFluidStacks.init(0, false, 132 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, primaryTankSize, false, tankOverlay);
        } else {
            guiFluidStacks.init(0, false, 152 - BG_X, 15 - BG_Y, TANK_WIDTH, TANK_HEIGHT, secondaryTankSize, false, tankOverlay);
        }

        guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(0));

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        List<SewerRecipeWrapper> recipes = Arrays.asList(
                new SewerRecipeWrapper(
                        new FluidStack(MFRFluids.essence, primaryTankSize),
                        true,
                        primaryWidget,
                        Arrays.asList(
                                I18n.format("jei.info.mfr.sewer.essence"),
                                I18n.format("jei.info.mfr.sewer.essence.1")
                        )
                ),
                new SewerRecipeWrapper(
                        new FluidStack(MFRFluids.sewage, secondaryTankSize),
                        false,
                        secondaryWidget,
                        Arrays.asList(
                                I18n.format("jei.info.mfr.sewer.sewage"),
                                I18n.format("jei.info.mfr.sewer.sewage.1")
                        )
                )
        );

        registry.addRecipes(recipes, this.getUid());

    }

}
