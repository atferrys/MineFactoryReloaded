package powercrystals.minefactoryreloaded.modcompat.forgemultiparts;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;

import static powercrystals.minefactoryreloaded.api.integration.IMFRRecipeSet.stack;
import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.MFR;
import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.MULTIPARTCBE;

@IMFRIntegrator.DependsOn(MULTIPARTCBE)
public class MultipartCBE implements IMFRIntegrator {

	@GameRegistry.ObjectHolder(value = MFR + ":brick")
	public static final Item factoryDecorativeBrickBlock = Items.AIR;
	@GameRegistry.ObjectHolder(value = MFR + ":stone")
	public static final Item factoryDecorativeStoneBlock = Items.AIR;
	@GameRegistry.ObjectHolder(value = MFR + ":stained_glass_block_block")
	public static final Item factoryGlassBlock = Items.AIR;
	@GameRegistry.ObjectHolder(value = MFR + ":rubber_wood_leaves")
	public static final Item rubberLeavesBlock = Items.AIR;
	@GameRegistry.ObjectHolder(value = MFR + ":road")
	public static final Item factoryRoadBlock = Items.AIR;
	@GameRegistry.ObjectHolder(value = MFR + ":rubber_wood_log")
	public static final Item rubberWoodBlock = Items.AIR;

	public void load() {

		this.getSubtypes(factoryDecorativeBrickBlock).forEach(this::registerViaIMC);
		this.getSubtypes(factoryDecorativeStoneBlock).forEach(this::registerViaIMC);
		this.getSubtypes(factoryRoadBlock).forEach(this::registerViaIMC);
		this.getSubtypes(rubberLeavesBlock).forEach(this::registerViaIMC);
		this.registerViaIMC(stack(rubberWoodBlock, 1, 0));

		this.getSubtypes(factoryGlassBlock).forEach(stack -> {
			IBlockState state = Block.getBlockFromItem(stack.getItem()).getStateFromMeta(stack.getMetadata());
			String key = BlockMicroMaterial.materialKey(state);
			MicroMaterialRegistry.registerMaterial(new FactoryGlassMicroMaterial(state, key), key);
		});

	}

	private NonNullList<ItemStack> getSubtypes(Item item) {

		NonNullList<ItemStack> items = NonNullList.create();
		item.getSubItems(CreativeTabs.SEARCH, items);

		return items;

	}

	private void registerViaIMC(ItemStack stack) {
		FMLInterModComms.sendMessage(MULTIPARTCBE, "microMaterial", stack);
	}

}
