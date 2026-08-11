package powercrystals.minefactoryreloaded.plugin.jei.sewer;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class SewerRecipeWrapper implements IRecipeWrapper {

    private final FluidStack output;
    private final boolean primary;
    private final IDrawable widget;
    private final List<String> widgetTooltip;

    private static final int WIDGET_X = 40, WIDGET_Y = 22;

    public SewerRecipeWrapper(FluidStack output, boolean primary, IDrawable widget, List<String> widgetTooltip) {
        this.output = output;
        this.primary = primary;
        this.widget = widget;
        this.widgetTooltip = widgetTooltip;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, output);
        ingredients.setOutput(ItemStack.class, FluidUtil.getFilledBucket(output));
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        widget.draw(minecraft, WIDGET_X, WIDGET_Y);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if(mouseX >= WIDGET_X && mouseX < WIDGET_X + widget.getWidth() && mouseY >= WIDGET_Y && mouseY < WIDGET_Y + widget.getHeight()) {
            return widgetTooltip;
        }
        return Collections.emptyList();
    }

    public boolean isPrimary() {
        return primary;
    }

}
