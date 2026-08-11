package powercrystals.minefactoryreloaded.plugin.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import powercrystals.minefactoryreloaded.plugin.jei.composter.ComposterRecipeCategory;
import powercrystals.minefactoryreloaded.plugin.jei.laserdrill.LaserDrillRecipeCategory;
import powercrystals.minefactoryreloaded.plugin.jei.lavafabricator.LavaFabricatorRecipeCategory;
import powercrystals.minefactoryreloaded.plugin.jei.meatpacker.MeatPackerRecipeCategory;
import powercrystals.minefactoryreloaded.plugin.jei.sewer.SewerRecipeCategory;
import powercrystals.minefactoryreloaded.plugin.jei.sludgeboiler.SludgeBoilerRecipeCategory;

import javax.annotation.Nonnull;

@JEIPlugin
public class MFRJEIPlugin implements IModPlugin {

    private MachineRecipeCategory<?> composterCategory;
    private MachineRecipeCategory<?> laserDrillCategory;
    private MachineRecipeCategory<?> lavaFabricatorCategory;
    private MachineRecipeCategory<?> meatPackerCategory;
    private MachineRecipeCategory<?> sewerCategory;
    private MachineRecipeCategory<?> sludgeBoilerCategory;

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {

        IJeiHelpers helpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();

        registry.addRecipeCategories(composterCategory = new ComposterRecipeCategory(guiHelper));
        registry.addRecipeCategories(laserDrillCategory = new LaserDrillRecipeCategory(guiHelper));
        registry.addRecipeCategories(lavaFabricatorCategory = new LavaFabricatorRecipeCategory(guiHelper));
        registry.addRecipeCategories(meatPackerCategory = new MeatPackerRecipeCategory(guiHelper));
        registry.addRecipeCategories(sewerCategory = new SewerRecipeCategory(guiHelper));
        registry.addRecipeCategories(sludgeBoilerCategory = new SludgeBoilerRecipeCategory(guiHelper));

    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        composterCategory.registerRecipesHandling(registry);
        laserDrillCategory.registerRecipesHandling(registry);
        lavaFabricatorCategory.registerRecipesHandling(registry);
        meatPackerCategory.registerRecipesHandling(registry);
        sewerCategory.registerRecipesHandling(registry);
        sludgeBoilerCategory.registerRecipesHandling(registry);
    }

}
