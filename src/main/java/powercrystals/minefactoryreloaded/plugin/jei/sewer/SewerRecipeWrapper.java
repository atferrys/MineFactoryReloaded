package powercrystals.minefactoryreloaded.plugin.jei.sewer;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class SewerRecipeWrapper implements IRecipeWrapper {

    private final FluidStack output;
    private final boolean primary;

    public SewerRecipeWrapper(FluidStack output, boolean primary) {
        this.output = output;
        this.primary = primary;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, output);
        ingredients.setOutput(ItemStack.class, FluidUtil.getFilledBucket(output));
    }

    public boolean isPrimary() {
        return primary;
    }

}
