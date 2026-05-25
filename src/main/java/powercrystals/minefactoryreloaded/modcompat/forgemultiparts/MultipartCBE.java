package powercrystals.minefactoryreloaded.modcompat.forgemultiparts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;

import javax.annotation.Nonnull;

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
		addSubtypes(factoryDecorativeBrickBlock);
		addSubtypes(factoryDecorativeStoneBlock);
		addSubtypes(factoryGlassBlock);
		addSubtypes(factoryRoadBlock);
		addSubtypes(rubberLeavesBlock);
		registerBlock(stack(rubberWoodBlock, 1, 0));
	}

	private void addSubtypes(Item item) {

		NonNullList<ItemStack> items = NonNullList.create();
		item.getSubItems(CreativeTabs.SEARCH, items);

		for(int i = items.size(); i-- > 0;) {
			registerBlock(items.get(i));
		}

	}

	private void registerBlock(ItemStack data) {
		FMLInterModComms.sendMessage(MULTIPARTCBE, "microMaterial", data);
	}

}
