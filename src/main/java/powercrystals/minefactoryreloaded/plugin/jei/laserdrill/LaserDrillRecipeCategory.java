package powercrystals.minefactoryreloaded.plugin.jei.laserdrill;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.core.WeightedRandomItemStack;
import powercrystals.minefactoryreloaded.plugin.jei.MachineRecipeCategory;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.machine.power.TileEntityLaserDrillPrecharger;
import powercrystals.minefactoryreloaded.tile.machine.processing.TileEntityLaserDrill;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

public class LaserDrillRecipeCategory extends MachineRecipeCategory<LaserDrillRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable focusSlotBackground;
    private final IDrawable energyOverlay;

    private final int energyPerOperation;
    private final Map<Integer, List<ItemStack>> laserPreferredOres = new HashMap<>();

    private static final int ENERGY_X = 150, ENERGY_Y = 15;
    private static final int SLOT_SIZE = 18;

    public LaserDrillRecipeCategory(IGuiHelper guiHelper) {

        super(Machine.LaserDrill);

        TileEntityLaserDrillPrecharger dummyPrecharger = new TileEntityLaserDrillPrecharger();
        TileEntityLaserDrill dummyDrill = new TileEntityLaserDrill();
        energyPerOperation = dummyPrecharger.getActivationEnergy() * dummyDrill.getWorkMax();
        dummyPrecharger = null;
        dummyDrill = null;

        for(int color = 0; color < 16; color++) {
            laserPreferredOres.put(color, MFRRegistry.getLaserPreferredOres(color));
        }

        background = guiHelper.createDrawable(
                texture,
                BG_X, BG_Y,
                168, 73
        );

        focusSlotBackground = guiHelper.createDrawable(
                texture,
                61, 78,
                SLOT_SIZE, SLOT_SIZE
        );

        energyOverlay = guiHelper.createDrawable(
                texture,
                176, 60,
                BAR_WIDTH, BAR_HEIGHT
        );

    }

    @Override
    @Nonnull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        focusSlotBackground.draw(minecraft, 10, 22);
        energyOverlay.draw(minecraft, ENERGY_X - BG_X, ENERGY_Y - BG_Y);

        String focusText = I18n.format("jei.info.mfr.laser_focus");
        minecraft.fontRenderer.drawString(focusText, 10 + SLOT_SIZE/2 - minecraft.fontRenderer.getStringWidth(focusText)/2, 43, 0x808080);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if(mouseX >= ENERGY_X - BG_X && mouseX < ENERGY_X - BG_X + BAR_WIDTH && mouseY >= ENERGY_Y - BG_Y && mouseY < ENERGY_Y - BG_Y + BAR_HEIGHT) {
            return Collections.singletonList(I18n.format("jei.info.mfr.energy", energyPerOperation));
        }
        return Collections.emptyList();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull LaserDrillRecipeWrapper recipeWrapper, IIngredients ingredients) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, false, 70, 22);
        guiItemStacks.init(1, true, 10, 22);

        guiItemStacks.set(0, ingredients.getOutputs(ItemStack.class).get(0));

        if(!ingredients.getInputs(ItemStack.class).isEmpty()) {
            guiItemStacks.set(1, ingredients.getInputs(ItemStack.class).get(0));
        }

    }

    @Override
    public void registerRecipesHandling(@Nonnull IModRegistry registry) {

        registry.addRecipeCatalyst(this.machine.getItemStack(), this.getUid());

        List<WeightedRandom.Item> laserOres = MFRRegistry.getLaserOres();
        int totalWeight = WeightedRandom.getTotalWeight(laserOres);

        List<LaserDrillRecipeWrapper> recipes = laserOres.stream()
                .map(ore -> {

                    ItemStack oreStack = ((WeightedRandomItemStack) ore).getStack();
                    int focusColor = -1;

                    outer:
                    for(Map.Entry<Integer, List<ItemStack>> laserEntry : laserPreferredOres.entrySet()) {

                        // Ores preferred by this laser color
                        List<ItemStack> preferredOres = laserEntry.getValue();

                        if(preferredOres == null) {
                            continue;
                        }

                        for(ItemStack preferredOre : preferredOres) {
                            if(oreStack.isItemEqual(preferredOre)) {
                                focusColor = laserEntry.getKey();
                                break outer;
                            }
                        }

                    }

                    return new LaserDrillRecipeWrapper(ore, focusColor, totalWeight);

                })
                .collect(Collectors.toList());

        registry.addRecipes(recipes, this.getUid());

    }

}
