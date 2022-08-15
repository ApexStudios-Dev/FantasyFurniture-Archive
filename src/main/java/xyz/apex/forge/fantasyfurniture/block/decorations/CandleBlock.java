package xyz.apex.forge.fantasyfurniture.block.decorations;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.Random;
import java.util.function.Consumer;

public final class CandleBlock extends BaseBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(5, 0, 4, 8, 3, 7),
			box(9, 0, 5, 12, 6, 8),
			box(8, 0, 10, 11, 8, 13),
			box(4, 0, 9, 7, 5, 12)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public CandleBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(LIT);
	}

	@Override
	public void onProjectileHit(Level level, BlockState blockState, BlockHitResult result, Projectile projectile)
	{
		super.onProjectileHit(level, blockState, result, projectile);

		if(!level.isClientSide && projectile.isOnFire() && canBeLit(blockState))
			level.setBlock(result.getBlockPos(), setLit(blockState, true), UPDATE_ALL_IMMEDIATE);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!isWaterLogged(blockState) && isLit(blockState))
			spawnLightParticles(blockState, level, pos, rng);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		if(player.getAbilities().mayBuild && player.getItemInHand(hand).isEmpty() && isLit(blockState))
		{
			extinguish(player, blockState, level, pos);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return InteractionResult.PASS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = getFacing(blockState);
		return SHAPER.get(facing);
	}

	private boolean canBeLit(BlockState blockState)
	{
		if(isWaterLogged(blockState))
			return false;
		return !isLit(blockState);
	}

	private void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		var facing = getFacing(blockState);

		var x = pos.getX();
		var y = pos.getY();
		var z = pos.getZ();

		var smokeChance = rng.nextFloat();

		if(facing == Direction.NORTH)
		{
			onLightParticle(level, pos, blockState, x + .4D, y + .313D, z + .35D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .65D, y + .5D, z + .4D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .375D, y + .45D, z + .65D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .6D, y + .65D, z + .725D, smokeChance, rng);
		}
		else if(facing == Direction.EAST)
		{
			onLightParticle(level, pos, blockState, x + .65D, y + .313D, z + .4D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .6D, y + .5D, z + .65D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .35D, y + .45D, z + .35D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .3D, y + .65D, z + .6D, smokeChance, rng);
		}
		else if(facing == Direction.SOUTH)
		{
			onLightParticle(level, pos, blockState, x + .6D, y + .313D, z + .65D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .35D, y + .5D, z + .6D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .4D, y + .65D, z + .3D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .65D, y + .45D, z + .3D, smokeChance, rng);
		}
		else if(facing == Direction.WEST)
		{
			onLightParticle(level, pos, blockState, x + .35D, y + .313D, z + .6D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .4D, y + .5D, z + .35D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .7D, y + .65D, z + .4D, smokeChance, rng);
			onLightParticle(level, pos, blockState, x + .65D, y + .45D, z + .65D, smokeChance, rng);
		}

		if(smokeChance < .17F)
			level.playLocalSound(x, y, z, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1F + rng.nextFloat(), rng.nextFloat() * .7F + .3F, false);
	}

	private void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, float chance, Random rng)
	{
		var flame = ParticleTypes.SMALL_FLAME;

		if(ModBlocks.BONE_CANDLES.isIn(blockState))
			flame = ModElements.SMALL_SOUL_FLAME.get();

		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);

		if(chance < .3F)
			level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
	}

	private void onExtinguishParticle(LevelAccessor level, BlockPos pos, BlockState blockState, Random rng)
	{
		var facing = getFacing(blockState);

		var x = pos.getX();
		var y = pos.getY();
		var z = pos.getZ();

		if(facing == Direction.NORTH)
		{
			level.addParticle(ParticleTypes.SMOKE, x + .4D, y + .313D, z + .35D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .65D, y + .5D, z + .4D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .375D, y + .45D, z + .65D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .6D, y + .65D, z + .725D, 0D, 0D, 0D);
		}
		else if(facing == Direction.EAST)
		{
			level.addParticle(ParticleTypes.SMOKE, x + .65D, y + .313D, z + .4D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .6D, y + .5D, z + .65D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .35D, y + .45D, z + .35D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .3D, y + .65D, z + .6D, 0D, 0D, 0D);
		}
		else if(facing == Direction.SOUTH)
		{
			level.addParticle(ParticleTypes.SMOKE, x + .6D, y + .313D, z + .65D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .35D, y + .5D, z + .6D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .4D, y + .65D, z + .3D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .65D, y + .45D, z + .3D, 0D, 0D, 0D);
		}
		else if(facing == Direction.WEST)
		{
			level.addParticle(ParticleTypes.SMOKE, x + .35D, y + .313D, z + .6D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .4D, y + .5D, z + .35D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .7D, y + .65D, z + .4D, 0D, 0D, 0D);
			level.addParticle(ParticleTypes.SMOKE, x + .65D, y + .45D, z + .65D, 0D, 0D, 0D);
		}
	}

	public static BlockState setLit(BlockState blockState, boolean lit)
	{
		return supportsLit(blockState) ? blockState.setValue(LIT, lit) : blockState;
	}

	public static boolean isLit(BlockState blockState)
	{
		return blockState.getOptionalValue(LIT).orElse(false);
	}

	public static boolean supportsLit(BlockState blockState)
	{
		return blockState.hasProperty(LIT);
	}

	public static void extinguish(@Nullable Player player, BlockState blockState, LevelAccessor level, BlockPos pos)
	{
		var block = blockState.getBlock();

		if(block instanceof AbstractCandleBlock)
			AbstractCandleBlock.extinguish(player, blockState, level, pos);
		else if(block instanceof CandleBlock candle)
		{
			level.setBlock(pos, setLit(blockState, false), UPDATE_ALL_IMMEDIATE);
			candle.onExtinguishParticle(level, pos, blockState, level.getRandom());
			level.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1F, 1F);
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
		}
	}
}