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

import javax.annotation.Nonnull;

public class PlantableNaturaNetherBerry extends PlantableStandard {

    public PlantableNaturaNetherBerry(Item sourceId, Block plantedBlockId) {
        super(sourceId, plantedBlockId);
    }

    @Override
    public boolean canBePlantedHere(World world, BlockPos pos, @Nonnull ItemStack stack) {
        Block groundBlock = world.getBlockState(pos.down()).getBlock();

        return (groundBlock != null && _plantedBlockId instanceof IPlantable && (groundBlock.canSustainPlant(world.getBlockState(pos.down()), world, pos.down(), EnumFacing.UP, (IPlantable) _plantedBlockId) || groundBlock == Blocks.NETHERRACK) && world
                .isAirBlock(pos));
    }

    @Override
    public int getMeta (ItemStack stack)
    {
        return stack.getItemDamage() % 4;
    }

}