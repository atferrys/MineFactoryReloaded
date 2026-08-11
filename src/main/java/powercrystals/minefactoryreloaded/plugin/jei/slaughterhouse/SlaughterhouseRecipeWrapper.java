package powercrystals.minefactoryreloaded.plugin.jei.slaughterhouse;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class SlaughterhouseRecipeWrapper implements IRecipeWrapper {

    private final FluidStack primaryOutput;
    private final FluidStack secondaryOutput;

    public SlaughterhouseRecipeWrapper(FluidStack primaryOutput, FluidStack secondaryOutput) {
        this.primaryOutput = primaryOutput;
        this.secondaryOutput = secondaryOutput;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setOutputs(FluidStack.class, Arrays.asList(primaryOutput, secondaryOutput));
        ingredients.setOutputs(ItemStack.class, Arrays.asList(FluidUtil.getFilledBucket(primaryOutput), FluidUtil.getFilledBucket(secondaryOutput)));
    }

}
