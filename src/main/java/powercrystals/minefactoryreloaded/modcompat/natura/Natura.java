package powercrystals.minefactoryreloaded.modcompat.natura;

import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;
import powercrystals.minefactoryreloaded.modcompat.natura.plantables.PlantableNaturaBerry;
import powercrystals.minefactoryreloaded.modcompat.natura.plantables.PlantableNaturaCrop;
import powercrystals.minefactoryreloaded.modcompat.natura.plantables.PlantableNaturaNetherBerry;
import powercrystals.minefactoryreloaded.modcompat.natura.plantables.PlantableStandard;

import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.NATURA;

@IMFRIntegrator.DependsOn(NATURA)
public class Natura implements IMFRIntegrator {

    @Override
    public void load() throws Throwable {
        registerFarmables();
        // TODO: Harvestables
        registerSludgeDrops();
    }

    private void registerFarmables() {

        REGISTRY.registerPlantable(new PlantableNaturaCrop(NaturaOverworld.barley_seeds.getItem(), NaturaOverworld.barleyCrop));

        REGISTRY.registerPlantable(new PlantableNaturaBerry(Item.getItemFromBlock(NaturaOverworld.overworldBerryBushBlackberry), NaturaOverworld.overworldBerryBushBlackberry));
        REGISTRY.registerPlantable(new PlantableNaturaBerry(Item.getItemFromBlock(NaturaOverworld.overworldBerryBushBlueberry), NaturaOverworld.overworldBerryBushBlueberry));
        REGISTRY.registerPlantable(new PlantableNaturaBerry(Item.getItemFromBlock(NaturaOverworld.overworldBerryBushMaloberry), NaturaOverworld.overworldBerryBushMaloberry));
        REGISTRY.registerPlantable(new PlantableNaturaBerry(Item.getItemFromBlock(NaturaOverworld.overworldBerryBushRaspberry), NaturaOverworld.overworldBerryBushRaspberry));

        REGISTRY.registerPlantable(new PlantableNaturaNetherBerry(Item.getItemFromBlock(NaturaNether.netherBerryBushDuskberry), NaturaNether.netherBerryBushDuskberry));
        REGISTRY.registerPlantable(new PlantableNaturaNetherBerry(Item.getItemFromBlock(NaturaNether.netherBerryBushSkyberry), NaturaNether.netherBerryBushSkyberry));
        REGISTRY.registerPlantable(new PlantableNaturaNetherBerry(Item.getItemFromBlock(NaturaNether.netherBerryBushStingberry), NaturaNether.netherBerryBushStingberry));
        REGISTRY.registerPlantable(new PlantableNaturaNetherBerry(Item.getItemFromBlock(NaturaNether.netherBerryBushBlightberry), NaturaNether.netherBerryBushBlightberry));

        REGISTRY.registerPlantable(new PlantableStandard(NaturaOverworld.saguaroFruitItem, NaturaOverworld.saguaro));

        REGISTRY.registerPlantable(new PlantableStandard(Item.getItemFromBlock(NaturaOverworld.appleSapling), NaturaOverworld.appleSapling));
        REGISTRY.registerPlantable(new PlantableStandard(Item.getItemFromBlock(NaturaOverworld.overworldSapling), NaturaOverworld.overworldSapling));
        REGISTRY.registerPlantable(new PlantableStandard(Item.getItemFromBlock(NaturaOverworld.overworldSapling2), NaturaOverworld.overworldSapling2));
        REGISTRY.registerPlantable(new PlantableStandard(Item.getItemFromBlock(NaturaOverworld.redwoodSapling), NaturaOverworld.redwoodSapling));

    }

    private void registerSludgeDrops() {
        REGISTRY.registerSludgeDrop(5, new ItemStack(NaturaNether.netherHeatSand));
        REGISTRY.registerSludgeDrop(5, new ItemStack(NaturaNether.netherTaintedSoil));
    }

}
