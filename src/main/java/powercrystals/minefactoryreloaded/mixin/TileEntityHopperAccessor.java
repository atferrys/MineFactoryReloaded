package powercrystals.minefactoryreloaded.mixin;

import net.minecraft.tileentity.TileEntityHopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TileEntityHopper.class)
public interface TileEntityHopperAccessor {

    @Invoker("isOnTransferCooldown")
    boolean invokeIsOnTransferCooldown();

}