package powercrystals.minefactoryreloaded.modcompat.chococraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableCropPlant;

import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.CHOCOCRAFT;

@IMFRIntegrator.DependsOn(CHOCOCRAFT)
public class Chococraft implements IMFRIntegrator {

	public void load() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

		Class<?> blocks = Class.forName("net.xalcon.chococraft.common.init.ModBlocks");

		Block blockId = ((Block) (blocks.getField("gysahlGreen").get(null)));

		Class<?> items = Class.forName("net.xalcon.chococraft.common.init.ModItems");
		Item seedId = ((Item) (items.getField("gysahlGreenSeeds").get(null)));

		REGISTRY.registerPlantable(new PlantableCropPlant(seedId, blockId));
		REGISTRY.registerHarvestable(new HarvestableChococraft(blockId));
		REGISTRY.registerFertilizable(new FertilizableChococraft(blockId));
	}

}

