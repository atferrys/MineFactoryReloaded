package powercrystals.minefactoryreloaded.tile.machine.mobs;

import cofh.core.fluid.FluidTankCore;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.api.mob.IFactoryGrindable;
import powercrystals.minefactoryreloaded.api.mob.MobDrop;
import powercrystals.minefactoryreloaded.core.GrindingDamage;
import powercrystals.minefactoryreloaded.gui.client.GuiFactoryInventory;
import powercrystals.minefactoryreloaded.gui.client.GuiFactoryPowered;
import powercrystals.minefactoryreloaded.gui.container.ContainerFactoryPowered;
import powercrystals.minefactoryreloaded.setup.MFRFluids;
import powercrystals.minefactoryreloaded.setup.Machine;
import powercrystals.minefactoryreloaded.tile.base.TileEntityFactoryPowered;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class TileEntityGrinder extends TileEntityFactoryPowered {

	public static final float DAMAGE = 0x1.fffffeP+120f;

	protected Random _rand;
	protected GrindingDamage _damageSource;

	protected TileEntityGrinder(Machine machine) {

		super(machine);

		createEntityHAM(this);
		_rand = new Random();
		setManageSolids(true);
		setCanRotate(true);
		_tanks[0].setLock(MFRFluids.getFluid("mob_essence"));

	}

	public TileEntityGrinder() {

		this(Machine.Grinder);

		_damageSource = new GrindingDamage(this);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiFactoryInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiFactoryPowered(getContainer(inventoryPlayer), this);
	}

	@Override
	public ContainerFactoryPowered getContainer(InventoryPlayer inventoryPlayer) {
		return new ContainerFactoryPowered(this, inventoryPlayer);
	}

	@Override
	public void setWorld(@Nonnull World world) {

		super.setWorld(world);

		if(this.world instanceof WorldServer) {
			_damageSource.setupGrindingPlayer((WorldServer) this.world);
		}

	}

	public Random getRandom() {
		return _rand;
	}

	@Override
	protected boolean shouldPumpLiquid() {
		return true;
	}

	@Override
	public int getWorkMax() {
		return 1;
	}

	@Override
	public int getIdleTicksMax() {
		return 200;
	}

	@Override
	public boolean activateMachine() {

		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, _areaManager.getHarvestArea().toAxisAlignedBB());

		entityList:
		for(EntityLivingBase entity : entities) {

			if(entity instanceof EntityAgeable && ((EntityAgeable) entity).getGrowingAge() < 0
					|| entity.isEntityInvulnerable(_damageSource) || entity.getHealth() <= 0) {
				continue;
			}

			processEntity:
			{

				if(MFRRegistry.getGrindables().containsKey(entity.getClass())) {

					IFactoryGrindable grindable = MFRRegistry.getGrindables().get(entity.getClass());
					List<MobDrop> drops = grindable.grind(entity.world, entity, getRandom());

					if(drops != null && !drops.isEmpty() && WeightedRandom.getTotalWeight(drops) > 0) {

						@Nonnull
						ItemStack drop = WeightedRandom.getRandomItem(_rand, drops).getStack();

						doDrop(drop);

					}

					if(grindable.processEntity(entity)) {

						if(entity.getHealth() <= 0) {
							setIdleTicks(20);
							return true;
						}

						break processEntity;

					}

				}

				for(Class<?> blacklistedClass : MFRRegistry.getGrinderBlacklist()) {
					if(blacklistedClass.isInstance(entity)) {
						continue entityList;
					}
				}

			}

			damageEntity(entity);

			if(entity.getHealth() <= 0) {
				setIdleTicks(20);
			} else {
				setIdleTicks(10);
			}

			return true;

		}

		setIdleTicks(getIdleTicksMax());

		return false;

	}

	protected void setRecentlyHit(EntityLivingBase entity, int t) {
		entity.recentlyHit = t;
	}

	protected void damageEntity(EntityLivingBase entity) {
		setRecentlyHit(entity, 100);
		entity.attackEntityFrom(_damageSource, DAMAGE);
	}

	public void acceptRawXP(int xpAmount) {

		if(xpAmount < 0) {
			return;
		}

		int essenceAmount = (int) (xpAmount * 66.66666667f);

		_tanks[0].fill(FluidRegistry.getFluidStack("mob_essence", essenceAmount), true);
		markDirty();

	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	protected void fillTank(FluidTankCore tank, String fluid, float amount) {
		tank.fill(FluidRegistry.getFluidStack(fluid, (int) (100 * amount)), true);
		markDirty();
	}

	@Override
	protected FluidTankCore[] createTanks() {
		return new FluidTankCore[] { new FluidTankCore(4 * BUCKET_VOLUME) };
	}

	@Override
	public boolean allowBucketDrain(EnumFacing facing, @Nonnull ItemStack stack) {
		return true;
	}

	@Override
	protected boolean canFillTank(EnumFacing facing, int index) {
		return false;
	}

	@Override
	public int fill(EnumFacing facing, FluidStack resource, boolean doFill) {
		return 0;
	}

}
