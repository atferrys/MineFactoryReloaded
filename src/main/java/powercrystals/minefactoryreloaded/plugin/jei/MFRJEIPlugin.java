package powercrystals.minefactoryreloaded.plugin.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import powercrystals.minefactoryreloaded.plugin.jei.sludgeboiler.SludgeBoilerRecipeCategory;

import javax.annotation.Nonnull;

@JEIPlugin
public class MFRJEIPlugin implements IModPlugin {

    private MachineRecipeCategory<?> sludgeBoilerCategory;

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {

        IJeiHelpers helpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();

        registry.addRecipeCategories(sludgeBoilerCategory = new SludgeBoilerRecipeCategory(guiHelper));

    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        sludgeBoilerCategory.registerRecipesHandling(registry);
    }

}
