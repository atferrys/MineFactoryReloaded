package powercrystals.minefactoryreloaded.modcompat.agricraft;

import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.api.integration.IMFRIntegrator;

import static powercrystals.minefactoryreloaded.modcompat.Compats.ModIds.AGRICRAFT;

@IMFRIntegrator.DependsOn(AGRICRAFT)
public class AgriCraft implements IMFRIntegrator {

    @Override
    public void load() throws Throwable {
        AgriCraftCrop crop = new AgriCraftCrop();
        MFRRegistry.registerHarvestable(crop);
        MFRRegistry.registerFertilizable(crop);
    }

}
