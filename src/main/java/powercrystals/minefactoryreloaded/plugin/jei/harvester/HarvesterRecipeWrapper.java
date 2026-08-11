package powercrystals.minefactoryreloaded.plugin.jei.harvester;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class HarvesterRecipeWrapper implements IRecipeWrapper {

    private final FluidStack output;

    public HarvesterRecipeWrapper(FluidStack output) {
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, output);
        ingredients.setOutput(ItemStack.class, FluidUtil.getFilledBucket(output));
    }

}
