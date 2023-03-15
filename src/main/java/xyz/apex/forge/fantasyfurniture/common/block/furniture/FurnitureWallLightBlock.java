package xyz.apex.forge.fantasyfurniture.common.block.furniture;

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
import org.jetbrains.annotations.Nullable;
import xyz.apex.forge.apexcore.lib.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllParticleTypes;
import xyz.apex.forge.fantasyfurniture.core.HitBoxes;

import java.util.List;
import java.util.function.Consumer;

public class FurnitureWallLightBlock extends WallLightBlock
{
	public FurnitureWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	protected void spawnLightParticles(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		if(AllBlocks.NORDIC_WALL_LIGHT.isIn(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY, pZ, rng);
		else if(AllBlocks.VENTHYR_WALL_LIGHT.isIn(blockState))
		{
			var x = pX;
			var y = pY + .35D;
			var z = pZ;

			var xOffset = 0D;
			var zOffset = 0D;

			if(supportsFacing(blockState))
			{
				var facing = getFacing(blockState).getOpposite();
				var face = facing.getClockWise();

				xOffset = .15D * face.getStepX();
				zOffset = .15D * face.getStepZ();

				x += .25D * facing.getStepX();
				z += .25D * facing.getStepZ();
			}

			onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
			onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
		}
		else if(AllBlocks.BONE_SKELETON_WALL_LIGHT.isIn(blockState) || AllBlocks.BONE_WITHER_WALL_LIGHT.isIn(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY + .05D, pZ, rng);
		else if(AllBlocks.ROYAL_WALL_LIGHT.isIn(blockState))
		{
			var x = pX;
			var y = pY + .25D;
			var z = pZ;

			var xOffset = 0D;
			var zOffset = 0D;

			if(supportsFacing(blockState))
			{
				var facing = getFacing(blockState).getOpposite();
				var face = facing.getClockWise();

				xOffset = .15D * face.getStepX();
				zOffset = .15D * face.getStepZ();

				x += .3D * facing.getStepX();
				z += .3D * facing.getStepZ();
			}

			onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
			onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
		}
		else if(AllBlocks.NECROLORD_WALL_LIGHT.isIn(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY + .1D, pZ, rng);
	}

	@Override
	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		var flame = ParticleTypes.FLAME;

		if(AllBlocks.BONE_WITHER_WALL_LIGHT.isIn(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;
		else if(AllBlocks.NECROLORD_WALL_LIGHT.isIn(blockState))
			flame = AllParticleTypes.NECROLORD_FLAME.get();

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(AllBlocks.NORDIC_WALL_LIGHT.isIn(blockState))
			return HitBoxes.NORDIC.wallLight(this, blockState);
		else if(AllBlocks.DUNMER_WALL_LIGHT.isIn(blockState))
			return HitBoxes.DUNMER.wallLight(this, blockState);
		else if(AllBlocks.VENTHYR_WALL_LIGHT.isIn(blockState))
			return HitBoxes.VENTHYR.wallLight(this, blockState);
		else if(AllBlocks.BONE_SKELETON_WALL_LIGHT.isIn(blockState) || AllBlocks.BONE_WITHER_WALL_LIGHT.isIn(blockState))
			return HitBoxes.BONE.wallLight(this, blockState);
		else if(AllBlocks.ROYAL_WALL_LIGHT.isIn(blockState))
			return HitBoxes.ROYAL.wallLight(this, blockState);
		else if(AllBlocks.NECROLORD_WALL_LIGHT.isIn(blockState))
			return HitBoxes.NECROLORD.wallLight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends FurnitureWallLightBlock implements IDyeable
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
