package powercrystals.minefactoryreloaded.plugin.jei;

import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import powercrystals.minefactoryreloaded.MFRProps;
import powercrystals.minefactoryreloaded.setup.Machine;

import javax.annotation.Nonnull;

public abstract class MachineRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {

    public static final int BG_X = 5, BG_Y = 5;

    public static final int BAR_HEIGHT = 60, BAR_WIDTH = 8;
    public static final int TANK_HEIGHT = 60, TANK_WIDTH = 16;

    protected final Machine machine;
    protected final ResourceLocation texture;

    public MachineRecipeCategory(Machine machine) {
        this.machine = machine;
        this.texture = new ResourceLocation(MFRProps.GUI_FOLDER + this.machine.getName().toLowerCase() + ".png");
    }

    @Override
    @Nonnull
    public String getUid() {
        return MFRProps.MOD_ID + "." + this.machine.getName().toLowerCase();
    }

    @Override
    @Nonnull
    public String getTitle() {
        return I18n.format(this.machine.getInternalName() + ".name");
    }

    @Override
    @Nonnull
    public String getModName() {
        return MFRProps.MOD_NAME;
    }

    public abstract void registerRecipesHandling(@Nonnull IModRegistry registry);

    protected int calculateEnergyHeight(int energy) {
        return BAR_HEIGHT * energy / this.machine.getMaxEnergyStorage();
    }

}
