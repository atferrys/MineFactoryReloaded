package powercrystals.minefactoryreloaded.net;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import powercrystals.minefactoryreloaded.core.GrindingDamage;
import powercrystals.minefactoryreloaded.tile.machine.mobs.TileEntityGrinder;

public class GrinderDropHandler {

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {

        if(!(event.getSource() instanceof GrindingDamage)) {
            return;
        }

        GrindingDamage source = (GrindingDamage) event.getSource();
        TileEntityGrinder grinder = source.grinder;

        if(grinder == null) {
            return;
        }

        for(EntityItem drop : event.getDrops()) {
            if(grinder.manageSolids() && !drop.getItem().isEmpty()) {
                grinder.doDrop(drop.getItem());
            }
        }

        event.setCanceled(true);

    }

    @SubscribeEvent
    public void onMobXpDrop(LivingExperienceDropEvent event) {

        DamageSource lastDamage = event.getEntityLiving().getLastDamageSource();

        if(!(lastDamage instanceof GrindingDamage)) {
            return;
        }

        GrindingDamage source = (GrindingDamage) lastDamage;
        TileEntityGrinder grinder = source.grinder;

        if(grinder == null) {
            return;
        }

        grinder.acceptRawXP(event.getDroppedExperience());

        event.setDroppedExperience(0);
        event.setCanceled(true);

    }

}