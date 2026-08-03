package powercrystals.minefactoryreloaded.modcompat.pvj;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;
import powercrystals.minefactoryreloaded.farmables.fertilizables.FertilizableStandard;
import powercrystals.minefactoryreloaded.farmables.fruits.FactoryFruitStandard;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableShearable;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableTreeLeaves;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableWood;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableSapling;
import vibrantjourneys.init.PVJBiomes;
import vibrantjourneys.init.PVJBlocks;

import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.PROJECT_VIBRANT_JOURNEYS;

@IMFRIntegrator.DependsOn(PROJECT_VIBRANT_JOURNEYS)
public class ProjectVibrantJourneys implements IMFRIntegrator {

    @Override
    public void load() throws Throwable {
        registerRubberTreeBiomes();
        registerFarmables();
    }

    private void registerRubberTreeBiomes() {
        REGISTRY.registerRubberTreeBiome(PVJBiomes.willow_swamp);
        REGISTRY.registerRubberTreeBiome(PVJBiomes.boreal_forest);
        REGISTRY.registerRubberTreeBiome(PVJBiomes.aspen_grove);
        REGISTRY.registerRubberTreeBiome(PVJBiomes.overgrown_spires);
    }

    private void registerFarmables() {

        Block[] pvjMiscStandardHarvestables = {
                PVJBlocks.short_grass,
                PVJBlocks.frost_lotus,
                PVJBlocks.silverleaf,
                PVJBlocks.chickweed,
                PVJBlocks.clovers,
                PVJBlocks.crabgrass,
                PVJBlocks.waxcap,
                PVJBlocks.orange_mushroom,
                PVJBlocks.deathcap,
                PVJBlocks.cattail,
                PVJBlocks.beach_grass,
                PVJBlocks.sea_oats,
                PVJBlocks.sundew,
                PVJBlocks.small_cactus,
                PVJBlocks.void_grass,
                PVJBlocks.wild_wheat,
                PVJBlocks.wild_potato,
                PVJBlocks.wild_carrot,
                PVJBlocks.wild_beetroot,
                PVJBlocks.bloodnettle,
                PVJBlocks.glowcap,
                PVJBlocks.witherweed,
                PVJBlocks.flower_patch
        };

        for(Block leaves : PVJBlocks.LEAVES) {
            REGISTRY.registerHarvestable(new HarvestableTreeLeaves(leaves));
        }

        for(Block log : PVJBlocks.LOGS) {
            REGISTRY.registerHarvestable(new HarvestableWood(log));
        }

        for(Block harvestable : pvjMiscStandardHarvestables) {
            REGISTRY.registerHarvestable(new HarvestableShearable(harvestable));
        }

        for(Block sapling : PVJBlocks.SAPLINGS) {
            REGISTRY.registerPlantable(new PlantableSapling(sapling));
            REGISTRY.registerFertilizable(new FertilizableStandard((IGrowable) sapling));
        }

        REGISTRY.registerFruit(new FactoryFruitStandard(PVJBlocks.coconut) {
            @Override
            public boolean canBePicked(World world, BlockPos pos) {
                return true;
            }
        });

    }

}
