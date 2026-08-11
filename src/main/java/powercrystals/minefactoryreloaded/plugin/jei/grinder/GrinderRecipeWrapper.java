package powercrystals.minefactoryreloaded.plugin.jei.grinder;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class GrinderRecipeWrapper implements IRecipeWrapper {

    private final FluidStack output;

    public GrinderRecipeWrapper(FluidStack output) {
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, output);
        ingredients.setOutput(ItemStack.class, FluidUtil.getFilledBucket(output));
    }

}
