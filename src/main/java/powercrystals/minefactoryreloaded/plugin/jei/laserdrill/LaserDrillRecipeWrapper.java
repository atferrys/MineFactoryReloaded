package powercrystals.minefactoryreloaded.plugin.jei.laserdrill;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import powercrystals.minefactoryreloaded.core.WeightedRandomItemStack;
import powercrystals.minefactoryreloaded.setup.MFRThings;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

public class LaserDrillRecipeWrapper implements IRecipeWrapper {

    private final ItemStack output;
    private final int laserFocus;
    private final double chance;

    public LaserDrillRecipeWrapper(WeightedRandom.Item ore, int laserFocus, int totalWeight) {
        this.output = ((WeightedRandomItemStack) ore).getStack();
        this.laserFocus = laserFocus;
        this.chance = (double) ore.itemWeight / totalWeight;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {

        if(laserFocus != -1) {
            ingredients.setInput(ItemStack.class, new ItemStack(MFRThings.laserFocusItem, 1, laserFocus));
        }

        ingredients.setOutput(ItemStack.class, output);

    }

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();
    static { PERCENT_FORMAT.setMaximumFractionDigits(2); }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        String formattedPercent = PERCENT_FORMAT.format(chance);
        int formattedPercentWidth = minecraft.fontRenderer.getStringWidth(formattedPercent);
        minecraft.fontRenderer.drawString(formattedPercent, 80 - formattedPercentWidth/2, 43, 0x808080);
    }

}
