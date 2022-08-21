package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.List;
import java.util.function.Consumer;

public class ChandelierBlock extends BaseBlock
{
	public ChandelierBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(!isWaterLogged(blockState))
			spawnLightParticles(blockState, level, pos, rng);
	}

	protected void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(ModBlocks.NORDIC_CHANDELIER.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .65D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x + .25D, y, z - .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z - .25D, rng);
		}
		else if(ModBlocks.DUNMER_CHANDELIER.isIn(blockState) || ModBlocks.ROYAL_CHANDELIER.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .875D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x + .275D, y, z - .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z - .275D, rng);
		}
		else if(ModBlocks.VENTHYR_CHANDELIER.isIn(blockState))
		{
			var x = pos.getX() + .2D;
			var y = pos.getY() + .55D;
			var z = pos.getZ() + .2D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
			onLightParticle(level, pos, blockState, x + .6D, y, z, rng);
			onLightParticle(level, pos, blockState, x + .6D, y, z + .6D, rng);
			onLightParticle(level, pos, blockState, x, y, z + .6D, rng);

			onLightParticle(level, pos, blockState, x + .1D, y + .2D, z + .1D, rng);
			onLightParticle(level, pos, blockState, x + .1D + .4D, y + .2D, z + .1D, rng);
			onLightParticle(level, pos, blockState, x + .1D + .4D, y + .2D, z + .1D + .4D, rng);
			onLightParticle(level, pos, blockState, x + .1D, y + .2D, z + .1D + .4D, rng);
		}
		else if(ModBlocks.BONE_SKELETON_CHANDELIER.isIn(blockState) || ModBlocks.BONE_WITHER_CHANDELIER.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .8D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .4D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .4D, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		var flame = ParticleTypes.FLAME;

		if(ModBlocks.BONE_WITHER_CHANDELIER.isIn(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_CHANDELIER.isIn(blockState))
			return HitBoxes.NORDIC.chandelier(this, blockState);
		else if(ModBlocks.DUNMER_CHANDELIER.isIn(blockState))
			return HitBoxes.DUNMER.chandelier(this, blockState);
		else if(ModBlocks.VENTHYR_CHANDELIER.isIn(blockState))
			return HitBoxes.VENTHYR.chandelier(this, blockState);
		else if(ModBlocks.BONE_SKELETON_CHANDELIER.isIn(blockState) || ModBlocks.BONE_WITHER_CHANDELIER.isIn(blockState))
			return HitBoxes.BONE.chandelier(this, blockState);
		else if(ModBlocks.ROYAL_CHANDELIER.isIn(blockState))
			return HitBoxes.ROYAL.chandelier(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends ChandelierBlock implements IDyeable
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(IDyeable.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return IDyeable.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			IDyeable.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
			return IDyeable.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = IDyeable.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return IDyeable.getCloneItemStack(blockState, level, pos, stack);
		}

		@Override
		public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
		{
			super.appendHoverText(stack, level, tooltip, flag);
			IDyeable.appendHoverText(this, tooltip);
		}
	}
}