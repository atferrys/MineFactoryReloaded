package powercrystals.minefactoryreloaded.core;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import powercrystals.minefactoryreloaded.tile.machine.mobs.TileEntityGrinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class GrindingDamage extends DamageSource {

	private static final String FAKE_PLAYER_NAME = "MinefactoryReloadedGrindingPlayer";
	private static final Map<Integer, WeakReference<FakePlayer>> FAKE_PLAYER_REFS = Maps.newHashMap();

	protected int _msgCount;
	protected Random _rand;
	private WeakReference<FakePlayer> fakePlayerRef;

	@Nullable
	public final TileEntityGrinder grinder;

	public GrindingDamage(String type) {
		this(null, type, 1);
	}

	public GrindingDamage(@Nullable TileEntityGrinder grinder) {
		this(grinder, null, 1);
	}

	public GrindingDamage(@Nullable TileEntityGrinder grinder, String type) {
		this(grinder, type, 1);
	}

	public GrindingDamage(@Nullable TileEntityGrinder grinder, String type, int deathMessages) {

		super(type == null ? "mfr.grinder" : type);

		this.grinder = grinder;
		setDamageIsAbsolute();
		setDamageBypassesArmor();
		setDamageAllowedInCreativeMode();
		_msgCount = Math.max(deathMessages, 1);
		_rand = new Random();

	}

	public void setupGrindingPlayer(WorldServer world) {

		int dimId = world.provider.getDimension();

		if(!FAKE_PLAYER_REFS.containsKey(dimId)) {
			String name = FAKE_PLAYER_NAME + "_" + dimId;
			FAKE_PLAYER_REFS.put(dimId, new WeakReference<>(
					FakePlayerFactory.get(world, new GameProfile(UUID.nameUUIDFromBytes(name.getBytes()), name))
			));
		}

		fakePlayerRef = FAKE_PLAYER_REFS.get(dimId);

	}

	@Override
	@Nonnull
	public ITextComponent getDeathMessage(EntityLivingBase entity) {

		EntityLivingBase attackingEntity = entity.getAttackingEntity();
		String message = "death.attack." + this.damageType;

		if(_msgCount > 1) {
			int randomMessage = _rand.nextInt(_msgCount);
			if(randomMessage != 0) {
				message += "." + randomMessage;
			}
		}

		String playerMessage = message + ".player";

		if(attackingEntity != null && I18n.hasKey(playerMessage)) {
			return new TextComponentTranslation(playerMessage, entity.getName(), attackingEntity.getName());
		}

		return new TextComponentTranslation(message, entity.getName());

	}

	@Nullable
	@Override
	public Entity getTrueSource() {
		return fakePlayerRef != null ? fakePlayerRef.get() : null;
	}

}
