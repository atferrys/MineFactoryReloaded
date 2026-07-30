package powercrystals.minefactoryreloaded.modcompat.natura.plantables;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.plant.IReplacementBlock;

import javax.annotation.Nonnull;

public class PlantableNaturaCrop extends PlantableStandard {

    public PlantableNaturaCrop(Item sourceId, Block plantedBlockId) {
        super(sourceId, plantedBlockId);
    }

    @Override
    public boolean canBePlantedHere(World world, BlockPos pos, @Nonnull ItemStack stack) {
        if (stack.getItemDamage() == 0 || stack.getItemDamage() == 1)
        {
            Block groundId = world.getBlockState(pos.down()).getBlock();
            if (!world.isAirBlock(pos))
            {
                return false;
            }
            return (groundId == Blocks.DIRT || groundId == Blocks.GRASS || groundId == Blocks.FARMLAND || (_plantedBlockId instanceof IPlantable
                    && groundId != null && groundId.canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP, ((IPlantable) _plantedBlockId))));
        }
        return false;
    }

    @Nonnull
    @Override
    public IReplacementBlock getPlantedBlock(World world, BlockPos pos, @Nonnull ItemStack stack) {
        prePlant(world, pos);
        return super.getPlantedBlock(world, pos, stack);
    }

    private void prePlant (World world, BlockPos pos) {
        Block groundId = world.getBlockState(pos.down()).getBlock();
        if (groundId == Blocks.DIRT || groundId == Blocks.GRASS) {
            world.setBlockState(pos.down(), Blocks.FARMLAND.getDefaultState());
        }
    }

    @Override
    public int getMeta (ItemStack stack)
    {
        return stack.getItemDamage() == 0 ? 0 : 4;
    }
}