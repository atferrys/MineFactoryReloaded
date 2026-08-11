package powercrystals.minefactoryreloaded.plugin.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import javax.annotation.Nonnull;

@JEIPlugin
public class MFRJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {

        IJeiHelpers helpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();

    }

    @Override
    public void register(@Nonnull IModRegistry registry) {

    }

}
