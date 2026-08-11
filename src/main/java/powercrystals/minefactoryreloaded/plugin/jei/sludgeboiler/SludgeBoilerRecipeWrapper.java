package powercrystals.minefactoryreloaded.plugin.jei.sludgeboiler;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.setup.MFRFluids;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

public class SludgeBoilerRecipeWrapper implements IRecipeWrapper {

    private final int sludgeAmount;
    private final ItemStack drop;
    private final double chance;

    public SludgeBoilerRecipeWrapper(int sludgeAmount, ItemStack drop, double chance) {
        this.sludgeAmount = sludgeAmount;
        this.drop = drop;
        this.chance = chance;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInput(FluidStack.class, new FluidStack(MFRFluids.sludge, sludgeAmount));
        ingredients.setOutput(ItemStack.class, drop);
    }

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();
    static { PERCENT_FORMAT.setMaximumFractionDigits(2); }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        String formattedPercent = PERCENT_FORMAT.format(chance);
        int formattedPercentWidth = minecraft.fontRenderer.getStringWidth(formattedPercent);
        minecraft.fontRenderer.drawString(formattedPercent, 45 - formattedPercentWidth/2, 43, 0x808080);
    }

}
