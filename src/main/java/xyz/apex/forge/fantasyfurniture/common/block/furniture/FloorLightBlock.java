package xyz.apex.forge.fantasyfurniture.common.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllParticleTypes;
import xyz.apex.forge.fantasyfurniture.AllPatterns;
import xyz.apex.forge.fantasyfurniture.core.HitBoxes;

import java.util.List;
import java.util.function.Consumer;

public class FloorLightBlock extends BaseMultiBlock
{
	public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);

	public FloorLightBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(PART, Part.BOTTOM));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(PART);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(!isWaterLogged(blockState) && blockState.getOptionalValue(PART).orElse(Part.BOTTOM).isTop())
			spawnLightParticles(blockState, level, pos, rng);
	}

	protected void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
	{
		if(AllBlocks.NORDIC_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .34D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .27D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .27D, rng);
		}
		else if(AllBlocks.DUNMER_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .5D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
		else if(AllBlocks.VENTHYR_FLOOR_LIGHT.isIn(blockState))
		{
			if(blockState.getOptionalValue(PART).orElse(Part.BOTTOM) == Part.TOP)
			{
				var x = pos.getX() + .3D;
				var y = pos.getY() + .9D;
				var z = pos.getZ() + .3D;

				onLightParticle(level, pos, blockState, x, y, z, rng);

				onLightParticle(level, pos, blockState, x + .4D, y, z, rng);
				onLightParticle(level, pos, blockState, x, y, z + .4D, rng);
				onLightParticle(level, pos, blockState, x + .4D, y, z + .4D, rng);
			}
		}
		else if(AllBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || AllBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState) || AllBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState) || AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .45D;
			var z = pos.getZ() + .5D;

			var offsetH = .25D;
			var offsetV = .05D;

			if(AllBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
				y += .075D;
			else if(AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
			{
				offsetH = .3D;
				y += .05D;
			}

			onLightParticle(level, pos, blockState, x, y, z, rng);
			onLightParticle(level, pos, blockState, x + offsetH, y - offsetV, z, rng);
			onLightParticle(level, pos, blockState, x - offsetH, y - offsetV, z, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		var flame = ParticleTypes.FLAME;

		if(AllBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;
		else if(AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
			flame = AllParticleTypes.NECROLORD_FLAME.get();

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return AllPatterns.PATTERN_1x1x2_PAINTING;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(AllBlocks.NORDIC_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.NORDIC.floorLight(this, blockState);
		else if(AllBlocks.DUNMER_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.DUNMER.floorLight(this, blockState);
		else if(AllBlocks.VENTHYR_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.VENTHYR.floorLight(this, blockState);
		else if(AllBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || AllBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.BONE.floorLight(this, blockState);
		else if(AllBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.ROYAL.floorLight(this, blockState);
		else if(AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.NECROLORD.floorLight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class WithFacing extends FloorLightBlock
	{
		public WithFacing(Properties properties)
		{
			super(properties);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			consumer.accept(FACING_4_WAY);
		}

		@Override
		protected void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
		{
			if(AllBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || AllBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState) || AllBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState) || AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
			{
				var x = pos.getX() + .5D;
				var y = pos.getY() + .5D + .45D;
				var z = pos.getZ() + .5D;

				var offsetH = .25D;
				var offsetV = .05D;

				if(AllBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
					y += .075D;
				else if(AllBlocks.NECROLORD_FLOOR_LIGHT.isIn(blockState))
				{
					offsetH = .3D;
					y += .05D;
				}

				var facing = getFacing(blockState).getClockWise();
				var stepX = facing.getStepX();
				var stepZ = facing.getStepZ();

				onLightParticle(level, pos, blockState, x, y, z, rng);
				onLightParticle(level, pos, blockState, x + (stepX * offsetH), y - offsetV, z + (stepZ * offsetH), rng);
				onLightParticle(level, pos, blockState, x - (stepX * offsetH), y - offsetV, z - (stepZ * offsetH), rng);
			}
		}
	}

	public static class Dyeable extends FloorLightBlock implements IDyeable
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

	public enum Part implements StringRepresentable
	{
		TOP("top"),
		BOTTOM("bottom");

		public final String serializedName;

		Part(String serializedName)
		{
			this.serializedName = serializedName;
		}

		public boolean isTop()
		{
			return this == TOP;
		}

		public boolean isBottom()
		{
			return this == BOTTOM;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}
