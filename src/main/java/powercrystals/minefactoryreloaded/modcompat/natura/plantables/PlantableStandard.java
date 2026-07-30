package powercrystals.minefactoryreloaded.modcompat.natura.plantables;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.plant.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.plant.IReplacementBlock;
import powercrystals.minefactoryreloaded.api.plant.ReplacementBlock;

import javax.annotation.Nonnull;

/*
 * Used for directly placing blocks (ie saplings) and items (ie sugarcane). Pass in source ID to constructor,
 * so one instance per source ID.
 */

public class PlantableStandard implements IFactoryPlantable {

    protected Item _sourceId;
    protected Block _plantedBlockId;

    public PlantableStandard(Item sourceId, Block plantedBlockId)
    {
        this._sourceId = sourceId;
        this._plantedBlockId = plantedBlockId;
    }

    public boolean canBePlanted(ItemStack stack, boolean forFermenting) {
        return true;//TODO figure out what on earth this is supposed to be
    }

    @Nonnull
    @Override
    public IReplacementBlock getPlantedBlock(World world, BlockPos pos, @Nonnull ItemStack stack) {
        if (stack.getItem() != _sourceId) {
            return new ReplacementBlock(Blocks.AIR);
        }
        return new ReplacementBlock(_plantedBlockId).setMeta(getMeta(stack));
    }

    @Override
    public boolean canBePlantedHere(World world, BlockPos pos, @Nonnull ItemStack stack) {

        Block groundId = world.getBlockState(pos.down()).getBlock();

        if (!world.isAirBlock(pos)) {
            return false;
        }

        return (_plantedBlockId.canPlaceBlockAt(world, pos) && canBlockStay(_plantedBlockId, world, pos, world.getBlockState(pos)))
                || (_plantedBlockId instanceof IPlantable && groundId != null && groundId.canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP,
                ((IPlantable)  _plantedBlockId)));
    }

    private boolean canBlockStay(Block block, World worldIn, BlockPos pos, IBlockState state)
    {
        if (block instanceof IPlantable && state.getBlock() == block) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, (IPlantable) block);
        }
        IBlockState down = worldIn.getBlockState(pos.down());
        return down.getBlock() == Blocks.GRASS || down.getBlock() == Blocks.DIRT || down.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public Item getSeed ()
    {
        return _sourceId;
    }

    public int getMeta (ItemStack i) {
        return i.getItemDamage();
    }

}