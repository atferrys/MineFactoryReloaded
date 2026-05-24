package powercrystals.minefactoryreloaded.mixin;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityLiving.class)
public interface EntityLivingAccessor {

    @Accessor("experienceValue")
    int getExperienceValue();

    @Accessor("inventoryArmor")
    NonNullList<ItemStack> getInventoryArmor();

    @Accessor("inventoryArmorDropChances")
    float[] getInventoryArmorDropChances();

    @Accessor("inventoryHands")
    NonNullList<ItemStack> getInventoryHands();

    @Accessor("inventoryHandsDropChances")
    float[] getInventoryHandsDropChances();

}