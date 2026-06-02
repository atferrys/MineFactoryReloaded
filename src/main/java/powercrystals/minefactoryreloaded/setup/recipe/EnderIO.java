package powercrystals.minefactoryreloaded.setup.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import powercrystals.minefactoryreloaded.api.integration.IMFRRecipeSet;
import powercrystals.minefactoryreloaded.setup.Machine;

@IMFRRecipeSet.DependsOn("enderio")
public class EnderIO implements IMFRRecipeSet {

    // region Items
    @GameRegistry.ItemStackHolder("enderio:item_basic_capacitor")
    public static final ItemStack capacitorBasic = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:item_basic_capacitor", meta = 1)
    public static final ItemStack capacitorDouble = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:item_basic_capacitor", meta = 2)
    public static final ItemStack capacitorOctadic = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 10)
    public static final ItemStack gear = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_material")
    public static final ItemStack chassis = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 40)
    public static final ItemStack zombieElectrode = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 42)
    public static final ItemStack zombieController = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_dark_steel_axe")
    public static final ItemStack dsAxe = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_dark_steel_pickaxe")
    public static final ItemStack dsPick = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_dark_steel_sword")
    public static final ItemStack dsSword = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_conduit_probe")
    public static final ItemStack probe = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_liquid_conduit")
    public static final ItemStack conduitLiquid = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_xp_transfer")
    public static final ItemStack xpRod = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:item_soul_vial")
    public static final ItemStack soulVial = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "forge:bucketfilled", nbt = "{FluidName:\"rocket_fuel\",Amount:1000}")
    public static final ItemStack fireWaterBucket = ItemStack.EMPTY;
    // endregion

    // region Blocks
    @GameRegistry.ItemStackHolder(value = "enderio:block_electric_light", meta = 2)
    public static final ItemStack light = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_reservoir")
    public static final ItemStack reservoir = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_transceiver")
    public static final ItemStack dimTrans = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_vacuum_chest")
    public static final ItemStack vacuumChest = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_tank")
    public static final ItemStack tank = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:block_tank", meta = 1)
    public static final ItemStack tankPressurized = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_experience_obelisk")
    public static final ItemStack xpObelisk = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_dark_steel_anvil")
    public static final ItemStack darkSteelAnvil = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder(value = "enderio:block_capacitor_bank", meta = 2)
    public static final ItemStack capBank = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_reinforced_obsidian")
    public static final ItemStack reinforcedObsidian = ItemStack.EMPTY;
    // endregion

    // region Machines
    @GameRegistry.ItemStackHolder("enderio:block_crafter")
    public static final ItemStack crafter = ItemStack.EMPTY;

    @GameRegistry.ItemStackHolder("enderio:block_combustion_generator")
    public static final ItemStack combustionGen = ItemStack.EMPTY;
    // endregion

    @Override
    public void registerRecipes() {
        registerMachines();
        registerMiscItems();
        registerRedNet();
    }

    // region Machines
    private final IRecipeHolder Planter = IRecipeHolder.EMPTY;
    private final IRecipeHolder Fisher = IRecipeHolder.EMPTY;
    private final IRecipeHolder Harvester = IRecipeHolder.EMPTY;
    private final IRecipeHolder Rancher = IRecipeHolder.EMPTY;
    private final IRecipeHolder Fertilizer = IRecipeHolder.EMPTY;
    private final IRecipeHolder Vet = IRecipeHolder.EMPTY;
    private final IRecipeHolder ItemCollector = IRecipeHolder.EMPTY;
    private final IRecipeHolder BlockBreaker = IRecipeHolder.EMPTY;
    private final IRecipeHolder WeatherCollector = IRecipeHolder.EMPTY;
    private final IRecipeHolder SludgeBoiler = IRecipeHolder.EMPTY;
    private final IRecipeHolder Sewer = IRecipeHolder.EMPTY;
    private final IRecipeHolder Composter = IRecipeHolder.EMPTY;
    private final IRecipeHolder Breeder = IRecipeHolder.EMPTY;
    private final IRecipeHolder Grinder = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoEnchanter = IRecipeHolder.EMPTY;
    private final IRecipeHolder Chronotyper = IRecipeHolder.EMPTY;
    private final IRecipeHolder Ejector = IRecipeHolder.EMPTY;
    private final IRecipeHolder ItemRouter = IRecipeHolder.EMPTY;
    private final IRecipeHolder LiquidRouter = IRecipeHolder.EMPTY;
    private final IRecipeHolder LiquiCrafter = IRecipeHolder.EMPTY;
    private final IRecipeHolder LavaFabricator = IRecipeHolder.EMPTY;
    private final IRecipeHolder SteamBoiler = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoJukebox = IRecipeHolder.EMPTY;
    private final IRecipeHolder Unifier = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoSpawner = IRecipeHolder.EMPTY;
    private final IRecipeHolder BioReactor = IRecipeHolder.EMPTY;
    private final IRecipeHolder BiofuelGenerator = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoDisenchanter = IRecipeHolder.EMPTY;
    private final IRecipeHolder Slaughterhouse = IRecipeHolder.EMPTY;
    private final IRecipeHolder MeatPacker = IRecipeHolder.EMPTY;
    private final IRecipeHolder EnchantmentRouter = IRecipeHolder.EMPTY;
    private final IRecipeHolder LaserDrill = IRecipeHolder.EMPTY;
    private final IRecipeHolder LaserDrillPrecharger = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoAnvil = IRecipeHolder.EMPTY;
    private final IRecipeHolder BlockSmasher = IRecipeHolder.EMPTY;
    private final IRecipeHolder RedNote = IRecipeHolder.EMPTY;
    private final IRecipeHolder AutoBrewer = IRecipeHolder.EMPTY;
    private final IRecipeHolder FruitPicker = IRecipeHolder.EMPTY;
    private final IRecipeHolder BlockPlacer = IRecipeHolder.EMPTY;
    private final IRecipeHolder MobCounter = IRecipeHolder.EMPTY;
    private final IRecipeHolder SteamTurbine = IRecipeHolder.EMPTY;
    private final IRecipeHolder Fountain = IRecipeHolder.EMPTY;
    private final IRecipeHolder DeepStorageUnit = IRecipeHolder.EMPTY;
    private final IRecipeHolder cheap_DeepStorageUnit = IRecipeHolder.EMPTY;
    private final IRecipeHolder ChunkLoader = IRecipeHolder.EMPTY;
    private final IRecipeHolder cheap_ChunkLoader = IRecipeHolder.EMPTY;
    private final IRecipeHolder MobRouter = IRecipeHolder.EMPTY;

    // Ingredients
    private final IRecipeHolder syringe_empty = IRecipeHolder.EMPTY;
    private final IRecipeHolder pinkslime_gem = IRecipeHolder.EMPTY;
    private final IRecipeHolder plastic_pipe = IRecipeHolder.EMPTY;
    private final IRecipeHolder plastic_tank = IRecipeHolder.EMPTY;
    private final IRecipeHolder hammer = IRecipeHolder.EMPTY;

    private void registerMachines() {

        Planter.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.FLOWER_POT,
                'S', Blocks.PISTON,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', zombieController
        );

        Fisher.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.FISHING_ROD,
                'S', Items.BUCKET,
                'F', chassis,
                'O', "ingotIron",
                'C', zombieController
        );

        Harvester.addShaped(
                "PSP",
                "TFT",
                "OCO",
                'P', "sheetPlastic",
                'S', Items.SHEARS,
                'T', dsAxe,
                'F', chassis,
                'O', "ingotGold",
                'C', zombieController
        );

        Rancher.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', conduitLiquid,
                'S', Items.SHEARS,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', zombieController
        );

        Fertilizer.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.GLASS_BOTTLE,
                'S', Items.LEATHER,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', zombieController
        );

        Vet.addShaped(
                "PTP",
                "TFT",
                "OCO",
                'P', "sheetPlastic",
                'T', syringe_empty,
                'F', chassis,
                'O', "ingotCopper",
                'C', zombieElectrode
        );

        ItemCollector.addShaped(
                "P P",
                " F ",
                "PCP",
                'P', "sheetPlastic",
                'F', chassis,
                'C', Blocks.CHEST
        );

        BlockBreaker.addShaped(
                "PTP",
                "SFA",
                "OCO",
                'P', "sheetPlastic",
                'T', "ingotElectricalSteel",
                'S', dsPick,
                'F', chassis,
                'A', Items.IRON_SHOVEL,
                'O', "ingotIron",
                'C', "dustRedstone"
        );

        WeatherCollector.addShaped(
                "PBP",
                "TFT",
                "OCO",
                'P', "sheetPlastic",
                'B', Blocks.IRON_BARS,
                'T', Items.BUCKET,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', "dustRedstone"
        );

        SludgeBoiler.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.BUCKET,
                'S', Blocks.FURNACE,
                'F', chassis,
                'O', "ingotIron",
                'C', "dustRedstone"
        );

        Sewer.addShaped(
                "PTP",
                "SFS",
                "SQS",
                'P', "sheetPlastic",
                'T', Items.BUCKET,
                'S', Items.BRICK,
                'F', chassis,
                'Q', reservoir
        );

        Composter.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.FURNACE,
                'S', Blocks.PISTON,
                'F', chassis,
                'O', Items.BRICK,
                'C', "dustRedstone"
        );

        Breeder.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.GOLDEN_APPLE,
                'S', Items.GOLDEN_CARROT,
                'F', chassis,
                'O', "dyePurple",
                'C', zombieElectrode
        );

        Grinder.addShaped(
                "PTP",
                "OFO",
                "SCS",
                'P', "sheetPlastic",
                'T', dsSword,
                'O', Items.BOOK,
                'F', chassis,
                'S', "ingotElectricalSteel",
                'C', conduitLiquid
        );

        AutoEnchanter.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.OBSIDIAN,
                'S', Items.BOOK,
                'F', chassis,
                'O', "gemDiamond",
                'C', xpRod
        );

        Chronotyper.addShaped(
                "PTP",
                "TFT",
                "OCO",
                'P', "sheetPlastic",
                'T', "gemEmerald",
                'F', chassis,
                'O', "dyePurple",
                'C', soulVial
        );

        Ejector.addShaped(
                "PPP",
                " T ",
                "OFO",
                'P', "sheetPlastic",
                'T', Blocks.HOPPER,
                'F', chassis,
                'O', "dustRedstone"
        );

        ItemRouter.addShaped(
                "PTP",
                "SFS",
                "PHP",
                'P', "sheetPlastic",
                'T', Blocks.CHEST,
                'S', probe,
                'F', chassis,
                'H', Blocks.HOPPER
        );

        LiquidRouter.addShaped(
                "PTP",
                "SFS",
                "PHP",
                'P', "sheetPlastic",
                'T', conduitLiquid,
                'S', probe,
                'F', chassis,
                'H', Blocks.HOPPER
        );

        DeepStorageUnit.addShaped(
                "PCP",
                "CFC",
                "PCP",
                'P', "sheetPlastic",
                'C', reinforcedObsidian,
                'F', dimTrans
        );

        cheap_DeepStorageUnit.addShaped(
                "PCP",
                "CFC",
                "PCP",
                'P', "sheetPlastic",
                'C', vacuumChest,
                'F', chassis
        );

        LiquiCrafter.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.CRAFTING_TABLE,
                'S', tank,
                'F', chassis,
                'O', Items.BOOK,
                'C', crafter
        );

        LavaFabricator.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.OBSIDIAN,
                'S', Items.MAGMA_CREAM,
                'F', chassis,
                'O', Items.BLAZE_ROD,
                'C', "dustRedstone"
        );

        SteamBoiler.addShaped(
                "PPP",
                "TBT",
                "OOO",
                'P', "sheetPlastic",
                'T', tankPressurized,
                'B', Machine.SludgeBoiler.getItemStack(),
                'O', Blocks.NETHER_BRICK
        );

        AutoJukebox.addShaped(
                "PJP",
                "PFP",
                'P', "sheetPlastic",
                'J', Blocks.JUKEBOX,
                'F', chassis
        );

        Unifier.addShaped(
                "PTP",
                "OFO",
                "SCS",
                'P', "sheetPlastic",
                'T', probe,
                'O', Items.COMPARATOR,
                'F', chassis,
                'S', "ingotElectricalSteel",
                'C', Items.BOOK
        );

        AutoSpawner.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.NETHER_WART,
                'S', Items.MAGMA_CREAM,
                'F', chassis,
                'O', "gemEmerald",
                'C', zombieController
        );

        BioReactor.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Items.FERMENTED_SPIDER_EYE,
                'S', "slimeball",
                'F', chassis,
                'O', Items.BRICK,
                'C', Items.SUGAR
        );

        BiofuelGenerator.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.FURNACE,
                'S', Blocks.PISTON,
                'F', chassis,
                'O', Items.BLAZE_ROD,
                'C', Blocks.PISTON
        );

        AutoDisenchanter.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', Blocks.NETHER_BRICK,
                'S', Items.BOOK,
                'F', chassis,
                'O', "gemDiamond",
                'C', xpObelisk
        );

        Slaughterhouse.addShaped(
                "GIG",
                "SFS",
                "XCX",
                'G', "sheetPlastic",
                'S', dsSword,
                'X', dsAxe,
                'I', "ingotElectricalSteel",
                'F', chassis,
                'C', "dustRedstone"
        );

        MeatPacker.addShaped(
                "GSG",
                "BFB",
                "BCB",
                'G', "sheetPlastic",
                'B', Blocks.BRICK_BLOCK,
                'S', fireWaterBucket,
                'F', chassis,
                'C', "dustRedstone"
        );

        EnchantmentRouter.addShaped(
                "PBP",
                "SFS",
                "PSP",
                'P', "sheetPlastic",
                'B', Items.BOOK,
                'S', Items.REPEATER,
                'F', chassis
        );

        LaserDrill.addShaped(
                "GFG",
                "CFC",
                "DHD",
                'G', "sheetPlastic",
                'D', "gemDiamond",
                'H', "blockGlassHardened",
                'F', light,
                'C', capacitorOctadic
        );

        LaserDrillPrecharger.addShaped(
                "GSG",
                "HFH",
                "CDC",
                'G', "sheetPlastic",
                'D', "gemDiamond",
                'S', pinkslime_gem,
                'H', "blockGlassHardened",
                'F', light,
                'C', capacitorDouble
        );

        AutoAnvil.addShaped(
                "GGG",
                "AFA",
                "OCO",
                'G', "sheetPlastic",
                'A', Blocks.ANVIL,
                'F', chassis,
                'C', darkSteelAnvil,
                'O', "ingotIron"
        );

        BlockSmasher.addShaped(
                "GPG",
                "HFH",
                "BCB",
                'G', "sheetPlastic",
                'P', Blocks.PISTON,
                'H', hammer,
                'B', Items.BOOK,
                'F', chassis,
                'C', "dustRedstone"
        );

        RedNote.addShaped(
                "GNG",
                "CFC",
                'G', "sheetPlastic",
                'C', "cableRedNet",
                'N', Blocks.NOTEBLOCK,
                'F', chassis
        );

        AutoBrewer.addShaped(
                "GBG",
                "CFC",
                "RPR",
                'G', "sheetPlastic",
                'C', conduitLiquid,
                'B', Items.BREWING_STAND,
                'R', Items.REPEATER,
                'F', chassis,
                'P', "dustRedstone"
        );

        FruitPicker.addShaped(
                "GXG",
                "SFS",
                "OCO",
                'G', "sheetPlastic",
                'X', dsAxe,
                'S', Items.SHEARS,
                'F', chassis,
                'C', zombieController,
                'O', "ingotElectricalSteel"
        );

        BlockPlacer.addShaped(
                "GDG",
                "DMD",
                "GSG",
                'G', "sheetPlastic",
                'D', Blocks.DISPENSER,
                'S', "dustRedstone",
                'M', chassis
        );

        MobCounter.addShaped(
                "GGG",
                "RCR",
                "SMS",
                'G', "sheetPlastic",
                'R', Items.REPEATER,
                'C', Items.COMPARATOR,
                'S', probe,
                'M', chassis
        );

        SteamTurbine.addShaped(
                "PTP",
                "SFS",
                "OCO",
                'P', "sheetPlastic",
                'T', combustionGen,
                'S', Blocks.PISTON,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', gear
        );

        ChunkLoader.addShaped(
                "PEP",
                "TET",
                "OCO",
                'P', "sheetPlastic",
                'T', dimTrans,
                'E', capBank,
                'O', "ingotEnergeticAlloy",
                'C', capacitorOctadic
        );

        cheap_ChunkLoader.addShaped(
                "PEP",
                "FDF",
                "OCO",
                'P', "sheetPlastic",
                'D', dimTrans,
                'E', capBank,
                'F', chassis,
                'O', "ingotEnergeticAlloy",
                'C', capacitorOctadic
        );

        Fountain.addShaped(
                "PBP",
                "TFT",
                "OCO",
                'P', "sheetPlastic",
                'B', Blocks.IRON_BARS,
                'T', tank,
                'F', chassis,
                'O', "ingotElectricalSteel",
                'C', "dustRedstone"
        );

        MobRouter.addShaped(
                "PPP",
                "BRB",
                "OCO",
                'P', "sheetPlastic",
                'B', Blocks.IRON_BARS,
                'R', ItemRouter,
                'O', "dyeOrange",
                'C', Chronotyper
        );

        plastic_tank.addShaped(
                "PPP",
                "P P",
                "PMP",
                'P', "sheetPlastic",
                'M', chassis
        );

    }
    // endregion

    //region Misc
    private final IRecipeHolder fertilizer_item = IRecipeHolder.EMPTY;
    private final IRecipeHolder porta_spawner = IRecipeHolder.EMPTY;
    private final IRecipeHolder spyglass = IRecipeHolder.EMPTY;
    private final IRecipeHolder detcord = IRecipeHolder.EMPTY;
    private final IRecipeHolder fishing_rod = IRecipeHolder.EMPTY;

    private void registerMiscItems() {

        fertilizer_item.addShaped(
                "WBW",
                "STS",
                "WBW",
                'W', Items.WHEAT,
                'B', new ItemStack(Items.DYE, 1, 15),
                'S', Items.STRING,
                'T', "stickWood"
        );

        spyglass.addShaped(
                "GLG",
                "PLP",
                " S ",
                'G', "ingotGold",
                'L', "blockGlass",
                'P', "sheetPlastic",
                'S', "stickWood"
        );

        porta_spawner.addShaped(
                "GLG",
                "DND",
                "GLG",
                'G', "ingotElectricalSteel",
                'L', "blockGlass",
                'D', "ingotVibrantAlloy",
                'N', Items.NETHER_STAR
        );

        detcord.addShaped(
                "PPP",
                "PTP",
                "PPP",
                'P', "itemRubber",
                'T', Blocks.TNT
        );

        fishing_rod.addShaped(
                "DD ",
                "DFD",
                "TDD",
                'D', "wireExplosive",
                'F', Items.FISHING_ROD,
                'T', Blocks.REDSTONE_TORCH
        );

    }
    // endregion

    // region Rednet
    private final IRecipeHolder rednet_cable_energy_single = IRecipeHolder.EMPTY;
    private final IRecipeHolder rednet_cable_energy_multi = IRecipeHolder.EMPTY;
    private final IRecipeHolder rednet_multimeter = IRecipeHolder.EMPTY;

    // Ingredients
    private final IRecipeHolder rednet_cable = IRecipeHolder.EMPTY;
    private final IRecipeHolder rednet_meter = IRecipeHolder.EMPTY;

    protected void registerRedNet() {

        rednet_cable_energy_single.addShapeless(
                "nuggetGold",
                "nuggetGold",
                "nuggetGold",
                "dustRedstone",
                "dustRedstone",
                rednet_cable
        );

        rednet_cable_energy_multi.addShapeless(
                "ingotGold",
                "ingotGold",
                Blocks.REDSTONE_BLOCK,
                rednet_cable,
                rednet_cable,
                rednet_cable,
                rednet_cable,
                rednet_cable,
                rednet_cable
        );

        rednet_multimeter.addShaped(
                "RGR",
                "IMI",
                "PPP",
                'P', "sheetPlastic",
                'G', capacitorBasic,
                'I', "ingotCopper",
                'R', "dustRedstone",
                'M', rednet_meter
        );

    }
    // endregion

}
