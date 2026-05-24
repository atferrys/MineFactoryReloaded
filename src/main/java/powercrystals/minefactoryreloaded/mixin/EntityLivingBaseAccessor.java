package powercrystals.minefactoryreloaded.mixin;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityLivingBase.class)
public interface EntityLivingBaseAccessor {

    @Accessor("recentlyHit")
    void setRecentlyHit(int value);

}