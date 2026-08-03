package powercrystals.minefactoryreloaded.modcompat.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;
import powercrystals.minefactoryreloaded.farmables.fertilizables.FertilizableStandard;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableShearable;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableTreeLeaves;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableWood;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableSapling;

import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.BIOMES_O_PLENTY;

@IMFRIntegrator.DependsOn(BIOMES_O_PLENTY)
public class BiomesOPlenty implements IMFRIntegrator {

    @Override
    public void load() throws Throwable {
        registerRubberTreeBiomes();
        registerFarmables();
        registerSludgeDrops();
    }

    private void registerRubberTreeBiomes() {

        Biome[] bopBiomes = {
                BOPBiomes.bayou.orNull(),
                BOPBiomes.bog.orNull(),
                BOPBiomes.boreal_forest.orNull(),
                BOPBiomes.grove.orNull(),
                BOPBiomes.lush_swamp.orNull(),
                BOPBiomes.maple_woods.orNull(),
                BOPBiomes.rainforest.orNull(),
                BOPBiomes.seasonal_forest.orNull(),
                BOPBiomes.shield.orNull(),
                BOPBiomes.temperate_rainforest.orNull(),
                BOPBiomes.tropical_rainforest.orNull(),
                BOPBiomes.woodland.orNull(),
                BOPBiomes.land_of_lakes.orNull(),
                BOPBiomes.wetland.orNull(),
                BOPBiomes.fen.orNull(),
                BOPBiomes.orchard.orNull()
        };

        for(Biome biome : bopBiomes) {
            if(biome != null) {
                REGISTRY.registerRubberTreeBiome(biome);
            }
        }

    }

    private void registerFarmables() {

        Block[] bopLeaves = {
                BOPBlocks.leaves_0,
                BOPBlocks.leaves_1,
                BOPBlocks.leaves_2,
                BOPBlocks.leaves_3,
                BOPBlocks.leaves_4,
                BOPBlocks.leaves_5,
                BOPBlocks.leaves_6,
                BOPBlocks.willow_vine,
                BOPBlocks.ivy,
        };
        Block[] bopLogs = {
                BOPBlocks.log_0,
                BOPBlocks.log_1,
                BOPBlocks.log_2,
                BOPBlocks.log_3,
                BOPBlocks.log_4,
                BOPBlocks.bamboo
        };
        Block[] bopMiscStandardHarvestables = {
                BOPBlocks.flower_0,
                BOPBlocks.flower_1,
                BOPBlocks.plant_0,
                BOPBlocks.plant_1,
                BOPBlocks.double_plant,
                BOPBlocks.mushroom
        };
        Block[] bopSaplings = {
                BOPBlocks.sapling_0,
                BOPBlocks.sapling_1,
                BOPBlocks.sapling_2
        };

        for(Block leaves : bopLeaves) {
            REGISTRY.registerHarvestable(new HarvestableTreeLeaves(leaves));
        }

        for(Block log : bopLogs) {
            REGISTRY.registerHarvestable(new HarvestableWood(log));
        }

        for(Block harvestable : bopMiscStandardHarvestables) {
            REGISTRY.registerHarvestable(new HarvestableShearable(harvestable));
        }

        for(Block sapling : bopSaplings) {
            REGISTRY.registerPlantable(new PlantableSapling(sapling));
            REGISTRY.registerFertilizable(new FertilizableStandard((IGrowable) sapling));
        }

    }

    private void registerSludgeDrops() {
        REGISTRY.registerSludgeDrop(15, new ItemStack(BOPBlocks.dried_sand));
        REGISTRY.registerSludgeDrop(15, new ItemStack(BOPBlocks.dirt, 1, 1));
        REGISTRY.registerSludgeDrop(15, new ItemStack(BOPBlocks.dirt, 1, 9));
        REGISTRY.registerSludgeDrop(15, new ItemStack(BOPItems.ash, 4));
        REGISTRY.registerSludgeDrop(25, new ItemStack(BOPItems.mudball, 4));
    }

}
