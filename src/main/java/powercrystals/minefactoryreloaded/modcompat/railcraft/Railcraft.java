package powercrystals.minefactoryreloaded.modcompat.railcraft;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator.findBlock;
import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.MFR;
import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.RAILCRAFT;

@IMFRIntegrator.DependsOn(RAILCRAFT)
public class Railcraft implements IMFRIntegrator {

	public void load() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		final Block factoryDecorativeStoneBlock = findBlock(MFR, "stone");

		String id = Block.REGISTRY.getNameForObject(factoryDecorativeStoneBlock).toString();
		FMLInterModComms.sendMessage(RAILCRAFT, "ballast", String.format("%s@%s", id, 8));
		FMLInterModComms.sendMessage(RAILCRAFT, "ballast", String.format("%s@%s", id, 9));
		// white sand? black sand?

		Object rockCrusher = Class.forName("mods.railcraft.api.crafting.Crafters").getMethod("rockCrusher")
				.invoke(null);
		Method makeRecipe = Class.forName("mods.railcraft.api.crafting.IRockCrusherCrafter").getMethod(
				"makeRecipe", Object.class);
		Method addOutput = Class.forName("mods.railcraft.api.crafting.IRockCrusherCrafter$IRockCrusherRecipeBuilder").getMethod("addOutput",
				ItemStack.class, float.class);
		Method register = Class.forName("mods.railcraft.api.crafting.IRockCrusherCrafter$IRockCrusherRecipeBuilder").getMethod("register");

		Object recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 10));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 2), 1.0f); // Paved Blackstone -> Cobble
		register.invoke(recipe);

		recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 11));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 3), 1.0f); // Paved Whitestone -> Cobble
		register.invoke(recipe);

		recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 0));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 2), 1.0f); // Smooth Blackstone -> Cobble
		register.invoke(recipe);

		recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 1));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 3), 1.0f); // Smooth Whitestone -> Cobble
		register.invoke(recipe);

		recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 2));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 8), 1.0f); // Cobble Blackstone -> Gravel + flint
		addOutput.invoke(recipe, new ItemStack(Items.FLINT, 1, 0), 0.05f);
		register.invoke(recipe);

		recipe = makeRecipe.invoke(rockCrusher, new ItemStack(factoryDecorativeStoneBlock, 1, 3));
		addOutput.invoke(recipe, new ItemStack(factoryDecorativeStoneBlock, 1, 9), 1.0f); // Cobble Whitestone -> Gravel + flint
		addOutput.invoke(recipe, new ItemStack(Items.FLINT, 1, 0), 0.05f);
		register.invoke(recipe);
	}

}
