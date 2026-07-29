package powercrystals.minefactoryreloaded.setup.village;

import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import powercrystals.minefactoryreloaded.block.BlockRubberWood;
import powercrystals.minefactoryreloaded.block.decor.BlockDecorativeBricks;
import powercrystals.minefactoryreloaded.block.decor.BlockDecorativeStone;
import powercrystals.minefactoryreloaded.block.decor.BlockFactoryGlassPane;
import powercrystals.minefactoryreloaded.core.MFRDyeColor;
import powercrystals.minefactoryreloaded.setup.MFRLoot;
import powercrystals.minefactoryreloaded.setup.MFRThings;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ComponentZoologistHouse extends StructureVillagePieces.Village {

	// Structure dimensions
	private static final int xSize = 9;
	private static final int ySize = 9;
	private static final int zSize = 6;

	// region Small Bricks Pool
	private static final IBlockState[] SMALL_BRICKS = {
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.ICE),
			// --- SKIP Small Glowstone Bricks
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.LAPIS),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.OBSIDIAN),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.PAVEDSTONE),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.SNOW),
			// --- SKIP Raw Meat Block

			// --- SKIP Sugar Charcoal Block
			// Covers the skipped charcoal block, also because large red bricks are an option too
			Blocks.BRICK_BLOCK.getDefaultState(),

			// Covers the 2/4 skipped blocks
			MFRThings.factoryDecorativeStoneBlock.getDefaultState()
					.withProperty(BlockDecorativeStone.VARIANT, BlockDecorativeStone.Variant.BLACK_BRICK_SMALL),
			MFRThings.factoryDecorativeStoneBlock.getDefaultState()
					.withProperty(BlockDecorativeStone.VARIANT, BlockDecorativeStone.Variant.WHITE_BRICK_SMALL),
	};
	// endregion

	// region Large Bricks Pool
	private static final IBlockState[] LARGE_BRICKS = {
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.ICE_LARGE),
			// --- SKIP Large Glowstone Bricks
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.LAPIS_LARGE),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.OBSIDIAN_LARGE),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.PAVEDSTONE_LARGE),
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.SNOW_LARGE),
			// --- SKIP Cooked Meat Block
			MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.BRICK_LARGE),

			// Covers the 2/4 skipped blocks
			MFRThings.factoryDecorativeStoneBlock.getDefaultState()
					.withProperty(BlockDecorativeStone.VARIANT, BlockDecorativeStone.Variant.BLACK_BRICK_LARGE),
			MFRThings.factoryDecorativeStoneBlock.getDefaultState()
					.withProperty(BlockDecorativeStone.VARIANT, BlockDecorativeStone.Variant.WHITE_BRICK_LARGE)
	};
	// endregion

	private IBlockState brick;
	private IBlockState light;
	private IBlockState glass;

	private boolean hasMeatBlock;

	private boolean hasGeneratedChest;

	public ComponentZoologistHouse() {}

	public ComponentZoologistHouse(Start startPiece, int componentType, Random rand, StructureBoundingBox sbb, EnumFacing coordBaseMode) {

		super(startPiece, componentType);
		this.setCoordBaseMode(coordBaseMode);
		boundingBox = sbb;

		int randomBrick = rand.nextInt(SMALL_BRICKS.length + LARGE_BRICKS.length);
		boolean isSmallBrick = randomBrick < SMALL_BRICKS.length;

		brick = isSmallBrick
				? SMALL_BRICKS[randomBrick]
				: LARGE_BRICKS[randomBrick - SMALL_BRICKS.length];

		light = MFRThings.factoryDecorativeBrickBlock.getDefaultState()
				.withProperty(BlockDecorativeBricks.VARIANT, isSmallBrick
						? BlockDecorativeBricks.Variant.GLOWSTONE
						: BlockDecorativeBricks.Variant.GLOWSTONE_LARGE
				);

		glass = Blocks.GLASS_PANE.getDefaultState();

		// If glass panes are not being replaced by another biome-specific block (e.g. iron bars I guess),
		// then use custom MineFactory glass panes.
		if(this.getBiomeSpecificBlockState(glass).equals(glass)) {
			glass = MFRThings.factoryGlassPaneBlock.getDefaultState()
					.withProperty(BlockFactoryGlassPane.COLOR, MFRDyeColor.values()[rand.nextInt(MFRDyeColor.values().length)]);
		}

		// Whether to place a Meat Block instead of the normal Workbench
		hasMeatBlock = rand.nextInt(10) == 0;

	}

	public static ComponentZoologistHouse buildComponent(Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		StructureBoundingBox sbb = StructureBoundingBox.getComponentToAddBoundingBox(
				p1, p2, p3, 0, 0, 0, xSize, ySize, zSize, facing
		);
		return canVillageGoDeeper(sbb) && StructureComponent.findIntersecting(pieces, sbb) == null
				? new ComponentZoologistHouse(startPiece, p5, random, sbb, facing)
				: null;
	}

	@Override
	protected void writeStructureToNBT(@Nonnull NBTTagCompound tag) {

		super.writeStructureToNBT(tag);

		tag.setTag("Brick", NBTUtil.writeBlockState(new NBTTagCompound(), this.brick));
		tag.setTag("Light", NBTUtil.writeBlockState(new NBTTagCompound(), this.light));
		tag.setTag("Glass", NBTUtil.writeBlockState(new NBTTagCompound(), this.glass));

		tag.setBoolean("Meat", this.hasMeatBlock);

		tag.setBoolean("Chest", this.hasGeneratedChest);

	}

	@Override
	protected void readStructureFromNBT(@Nonnull NBTTagCompound tag, @Nonnull TemplateManager templateManager) {

		super.readStructureFromNBT(tag, templateManager);

		this.brick = NBTUtil.readBlockState(tag.getCompoundTag("Brick"));
		this.light = NBTUtil.readBlockState(tag.getCompoundTag("Light"));
		this.glass = NBTUtil.readBlockState(tag.getCompoundTag("Glass"));

		this.hasMeatBlock = tag.getBoolean("Meat");

		this.hasGeneratedChest = tag.getBoolean("Chest");

	}

	@Override
	public boolean addComponentParts(@Nonnull World world, @Nonnull Random random, @Nonnull StructureBoundingBox sbb) {

		// Level off ground
		if(this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(world, sbb);
			if(this.averageGroundLvl < 0) {
				return true;
			}
			boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + ySize - 1, 0);
		}

		// Clean up area
		this.fillWithAir(world, sbb, 1, 1, 1, 7, 5, 4);

		this.fillWithBlocks(world, sbb, 0, 0, 0, 8, 0, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 0, 5, 0, 8, 5, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 0, 6, 1, 8, 6, 4, brick, brick, false);
		this.fillWithBlocks(world, sbb, 0, 7, 2, 8, 7, 3, brick, brick, false);

		// Place stairs on the roof
		IBlockState stairsNorth = Blocks.OAK_STAIRS.getDefaultState()
				.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		IBlockState stairsSouth = Blocks.OAK_STAIRS.getDefaultState()
				.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);

		for(int zz = -1; zz <= 2; ++zz) {
			for(int xx = 0; xx <= 8; ++xx) {
				this.setBlockState(world, stairsNorth, xx, 6 + zz, zz, sbb);
				this.setBlockState(world, stairsSouth, xx, 6 + zz, 5 - zz, sbb);
			}
		}

		this.fillWithBlocks(world, sbb, 0, 1, 0, 0, 1, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 1, 1, 5, 8, 1, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 8, 1, 0, 8, 1, 4, brick, brick, false);
		this.fillWithBlocks(world, sbb, 2, 1, 0, 7, 1, 0, brick, brick, false);
		this.fillWithBlocks(world, sbb, 0, 2, 0, 0, 4, 0, brick, brick, false);
		this.fillWithBlocks(world, sbb, 0, 2, 5, 0, 4, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 8, 2, 5, 8, 4, 5, brick, brick, false);
		this.fillWithBlocks(world, sbb, 8, 2, 0, 8, 4, 0, brick, brick, false);

		IBlockState planks = Blocks.PLANKS.getDefaultState();

		this.fillWithBlocks(world, sbb, 0, 2, 1, 0, 4, 4, planks, planks, false);
		this.fillWithBlocks(world, sbb, 1, 2, 5, 7, 4, 5, planks, planks, false);
		this.fillWithBlocks(world, sbb, 8, 2, 1, 8, 4, 4, planks, planks, false);
		this.fillWithBlocks(world, sbb, 1, 2, 0, 7, 4, 0, planks, planks, false);

		IBlockState rubberLog = MFRThings.rubberWoodBlock.getDefaultState()
				.withProperty(BlockRubberWood.LOG_AXIS, BlockLog.EnumAxis.NONE);

		// Large front window
		this.setBlockState(world, rubberLog, 3, 2, 0, sbb);
		this.setBlockState(world, glass, 4, 2, 0, sbb);
		this.setBlockState(world, glass, 5, 2, 0, sbb);
		this.setBlockState(world, glass, 6, 2, 0, sbb);
		this.setBlockState(world, rubberLog, 7, 2, 0, sbb);
		this.setBlockState(world, rubberLog, 3, 3, 0, sbb);
		this.setBlockState(world, glass, 4, 3, 0, sbb);
		this.setBlockState(world, glass, 5, 3, 0, sbb);
		this.setBlockState(world, glass, 6, 3, 0, sbb);
		this.setBlockState(world, rubberLog, 7, 3, 0, sbb);

		// Side window near door
		this.setBlockState(world, rubberLog, 0, 2, 1, sbb);
		this.setBlockState(world, glass, 0, 2, 2, sbb);
		this.setBlockState(world, glass, 0, 2, 3, sbb);
		this.setBlockState(world, rubberLog, 0, 2, 4, sbb);
		this.setBlockState(world, rubberLog, 0, 3, 1, sbb);
		this.setBlockState(world, glass, 0, 3, 2, sbb);
		this.setBlockState(world, glass, 0, 3, 3, sbb);
		this.setBlockState(world, rubberLog, 0, 3, 4, sbb);

		// Window opposite above window
		this.setBlockState(world, rubberLog, 8, 2, 1, sbb);
		this.setBlockState(world, glass, 8, 2, 2, sbb);
		this.setBlockState(world, glass, 8, 2, 3, sbb);
		this.setBlockState(world, rubberLog, 8, 2, 4, sbb);
		this.setBlockState(world, rubberLog, 8, 3, 1, sbb);
		this.setBlockState(world, glass, 8, 3, 2, sbb);
		this.setBlockState(world, glass, 8, 3, 3, sbb);
		this.setBlockState(world, rubberLog, 8, 3, 4, sbb);

		// Two back windows
		this.setBlockState(world, rubberLog, 1, 2, 5, sbb);
		this.setBlockState(world, glass, 2, 2, 5, sbb);
		this.setBlockState(world, glass, 3, 2, 5, sbb);
		this.setBlockState(world, rubberLog, 4, 2, 5, sbb);
		this.setBlockState(world, glass, 5, 2, 5, sbb);
		this.setBlockState(world, glass, 6, 2, 5, sbb);
		this.setBlockState(world, rubberLog, 7, 2, 5, sbb);

		// Place bookshelves and ceiling wood
		IBlockState bookshelf = Blocks.BOOKSHELF.getDefaultState();

		this.fillWithBlocks(world, sbb, 1, 4, 1, 7, 4, 1, planks, planks, false);
		this.fillWithBlocks(world, sbb, 1, 4, 4, 7, 4, 4, planks, planks, false);
		this.fillWithBlocks(world, sbb, 1, 3, 4, 7, 3, 4, bookshelf, bookshelf, false);

		// Wrap around bench
		IBlockState stairsEast = Blocks.OAK_STAIRS.getDefaultState()
				.withProperty(BlockStairs.FACING, EnumFacing.EAST);

		this.setBlockState(world, stairsEast, 7, 1, 4, sbb);
		this.setBlockState(world, stairsEast, 7, 1, 3, sbb);
		this.setBlockState(world, stairsNorth, 6, 1, 4, sbb);
		this.setBlockState(world, stairsNorth, 5, 1, 4, sbb);
		this.setBlockState(world, stairsNorth, 4, 1, 4, sbb);
		this.setBlockState(world, stairsNorth, 3, 1, 4, sbb);

		// "Tables"
		IBlockState fence = Blocks.OAK_FENCE.getDefaultState();
		IBlockState pressurePlate = Blocks.WOODEN_PRESSURE_PLATE.getDefaultState();

		this.setBlockState(world, fence, 6, 1, 3, sbb);
		this.setBlockState(world, pressurePlate, 6, 2, 3, sbb);
		this.setBlockState(world, fence, 4, 1, 3, sbb);
		this.setBlockState(world, pressurePlate, 4, 2, 3, sbb);

		// Place workbench or meat
		IBlockState workbench = hasMeatBlock
				? MFRThings.factoryDecorativeBrickBlock.getDefaultState()
					.withProperty(BlockDecorativeBricks.VARIANT, BlockDecorativeBricks.Variant.MEAT_RAW)
				: Blocks.CRAFTING_TABLE.getDefaultState();

		this.setBlockState(world, workbench, 7, 1, 1, sbb);

		// Overhead lights (match large/small bricks)
		this.fillWithBlocks(world, sbb, 1, 5, 2, 7, 5, 3, light, light, false);

		// Place Door
		this.createVillageDoor(world, sbb, random, 1, 1, 0, EnumFacing.NORTH);

		if(this.getBlockStateFromPos(world, 1, 0, -1, sbb).getBlock() == Blocks.AIR &&
				this.getBlockStateFromPos(world, 1, -1, -1, sbb).getBlock() != Blocks.AIR) {
			IBlockState cobblestoneStairs = Blocks.STONE_STAIRS.getDefaultState()
					.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
			this.setBlockState(world, cobblestoneStairs, 1, 0, -1, sbb);
		}

		// Generate chest with loot
		if(!this.hasGeneratedChest && this.generateChest(world, sbb, random, 1, 1, 4, MFRLoot.ZOOLOGIST_CHEST)) {
			IBlockState rotatedState = this.getBlockStateFromPos(world, 1, 1, 4, sbb)
					.withProperty(BlockChest.FACING, EnumFacing.SOUTH);
			this.setBlockState(world, rotatedState, 1, 1, 4, sbb);
			this.hasGeneratedChest = true;
		}

		// Prevent structure from floating or being blocked on top by terrain
		for(int zz = 0; zz < zSize; ++zz) {
			for(int xx = 0; xx < xSize; ++xx) {
				this.clearCurrentPositionBlocksUpwards(world, xx, ySize, zz, sbb);
				this.replaceAirAndLiquidDownwards(world, brick, xx, -1, zz, sbb);
			}
		}

		this.spawnVillagers(world, sbb, 2, 1, 2, 1);

		return true;

	}

	@Override
	@Nonnull
	protected VillagerRegistry.VillagerProfession chooseForgeProfession(int count, @Nonnull VillagerRegistry.VillagerProfession prof) {
		return Zoologist.zoologistProfession;
	}

}
