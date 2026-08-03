package powercrystals.minefactoryreloaded.setup.village;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import powercrystals.minefactoryreloaded.MFRProps;
import powercrystals.minefactoryreloaded.item.ItemSafariNet;
import powercrystals.minefactoryreloaded.setup.MFRThings;

import javax.annotation.Nonnull;
import java.util.Random;

public class Zoologist {

	public static final VillagerProfession zoologistProfession = new VillagerProfession(
			MFRProps.PREFIX + "zoologist",
			MFRProps.VILLAGER_FOLDER + "zoologist.png",
			"minecraft:textures/entity/zombie_villager/zombie_villager.png"
	);

	public static void init() {

		ForgeRegistries.VILLAGER_PROFESSIONS.register(zoologistProfession);

		VillagerCareer zoologist = new VillagerCareer(zoologistProfession, "zoologist");

		zoologist.addTrade(1, new ListItemForEmeraldAndItem(new ItemStack(MFRThings.rubberSaplingBlock, 8, 0),
				ItemBlock.getItemFromBlock(Blocks.SAPLING), 8, 6));
		zoologist.addTrade(1, new ListItemForEmeralds(MFRThings.safariNetSingleItem, new PriceInfo(1, 1)));
		zoologist.addTrade(2, new ListItemForEmeraldAndItem(getHiddenNetStack(), MFRThings.safariNetSingleItem));
		zoologist.addTrade(3, new ListItemForEmeralds(MFRThings.safariNetItem, new PriceInfo(3, 1)));

	}

	@Nonnull
	public static ItemStack getHiddenNetStack() {
		return ItemSafariNet.makeMysteryNet(new ItemStack(MFRThings.safariNetSingleItem));
	}

	private static class ListItemForEmeraldAndItem implements EntityVillager.ITradeList {

		@Nonnull
		private final ItemStack itemToBuy;

		private final Item itemToPay;
		private final int maxRandomMeta;
		private final int payCount;

		public ListItemForEmeraldAndItem(@Nonnull ItemStack itemToBuy, Item itemToPay, int payCount, int maxRandomMeta) {
			this.itemToBuy = itemToBuy;
			this.itemToPay = itemToPay;
			this.payCount = payCount;
			this.maxRandomMeta = maxRandomMeta;
		}

		public ListItemForEmeraldAndItem(@Nonnull ItemStack itemToBuy, Item itemToPay) {
			this(itemToBuy, itemToPay, 1, 0);
		}

		@Override
		public void addMerchantRecipe(@Nonnull IMerchant merchant, MerchantRecipeList recipeList, @Nonnull Random random) {
			recipeList.add(new MerchantRecipe(
					new ItemStack(Items.EMERALD),
					new ItemStack(itemToPay, 1, maxRandomMeta == 0 ? 0 : random.nextInt(maxRandomMeta)),
					itemToBuy.copy()
			));
		}

	}

}
