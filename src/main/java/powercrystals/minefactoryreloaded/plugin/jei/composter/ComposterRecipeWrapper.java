package powercrystals.minefactoryreloaded.plugin.jei.composter;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class ComposterRecipeWrapper implements IRecipeWrapper {

    private final FluidStack input;
    private final ItemStack output;

    public ComposterRecipeWrapper(FluidStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInput(FluidStack.class, input);
        ingredients.setInput(ItemStack.class, FluidUtil.getFilledBucket(input));
        ingredients.setOutput(ItemStack.class, output);
    }

}
