package powercrystals.minefactoryreloaded.modcompat.agricraft;

import com.google.common.collect.Lists;
import com.infinityraider.agricraft.api.v1.seed.AgriSeed;
import com.infinityraider.agricraft.api.v1.util.MethodResult;
import com.infinityraider.agricraft.compat.vanilla.BonemealWrapper;
import com.infinityraider.agricraft.init.AgriBlocks;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.plant.FertilizerType;
import powercrystals.minefactoryreloaded.api.plant.HarvestType;
import powercrystals.minefactoryreloaded.api.plant.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.plant.IFactoryHarvestable;
import powercrystals.minefactoryreloaded.api.util.IFactorySettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class AgriCraftCrop implements IFactoryHarvestable, IFactoryFertilizable {

    @Override
    @Nonnull
    public Block getPlant() {
        return AgriBlocks.getInstance().CROP;
    }

    @Override
    public boolean canFertilize(World world, BlockPos pos, FertilizerType fertilizerType) {

        if(fertilizerType != FertilizerType.GrowPlant && fertilizerType != FertilizerType.Grass) {
            return false;
        }

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileEntityCrop) {
            return ((TileEntityCrop) tile).acceptsFertilizer(BonemealWrapper.INSTANCE);
        }

        return false;

    }

    @Override
    public boolean fertilize(World world, Random rand, BlockPos pos, FertilizerType fertilizerType) {

        if(fertilizerType != FertilizerType.GrowPlant && fertilizerType != FertilizerType.Grass) {
            return false;
        }

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileEntityCrop) {
            return ((TileEntityCrop) tile).onApplyFertilizer(BonemealWrapper.INSTANCE, rand) == MethodResult.SUCCESS;
        }

        return false;

    }

    @Override
    @Nonnull
    public HarvestType getHarvestType() {
        return HarvestType.Normal;
    }

    @Override
    public boolean breakBlock() {
        return false;
    }

    @Override
    public boolean canBeHarvested(World world, BlockPos pos, IBlockState harvestState, IFactorySettings harvesterSettings) {

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileEntityCrop) {
            return ((TileEntityCrop) tile).canBeHarvested();
        }

        return false;

    }

    @Nullable
    @Override
    public List<ItemStack> getDrops(World world, BlockPos pos, IBlockState harvestState, Random rand, IFactorySettings harvesterSettings) {

        TileEntity tile = world.getTileEntity(pos);
        List<ItemStack> drops = Lists.newArrayList();

        if(tile instanceof TileEntityCrop) {

            TileEntityCrop crop = (TileEntityCrop) tile;
            crop.getDrops(drops::add, false, false, true);

            // Add the bonus chance to get seeds
            AgriSeed seed = crop.getSeed();

            if(seed != null && seed.getPlant().getSeedDropChanceBonus() > rand.nextDouble()) {
                drops.add(seed.toStack());
            }

        }

        return drops;
    }

    @Override
    public boolean postHarvest(World world, BlockPos pos, IBlockState harvestState) {

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileEntityCrop) {
            ((TileEntityCrop) tile).setGrowthStage(0);
        }

        return false; // Do not play the break block sound and effect.

    }

}