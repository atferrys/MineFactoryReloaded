package powercrystals.minefactoryreloaded.plugin.jei.bioreactor;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class BioReactorRecipeWrapper implements IRecipeWrapper {

    private final ItemStack input;
    private final FluidStack output;

    public BioReactorRecipeWrapper(ItemStack input, FluidStack output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(FluidStack.class, output);
        ingredients.setOutput(ItemStack.class, FluidUtil.getFilledBucket(output));
    }

}
