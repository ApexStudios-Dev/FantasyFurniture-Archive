package xyz.apex.forge.fantasyfurniture.block.furniture;

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
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

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
		if(ModBlocks.NORDIC_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .34D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .27D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .27D, rng);
		}
		else if(ModBlocks.DUNMER_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .5D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
		else if(ModBlocks.VENTHYR_FLOOR_LIGHT.isIn(blockState))
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
		else if(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || ModBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState) || ModBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .45D;
			var z = pos.getZ() + .5D;

			if(ModBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
				y += .075D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
			onLightParticle(level, pos, blockState, x + .25D, y - .05D, z, rng);
			onLightParticle(level, pos, blockState, x - .25D, y - .05D, z, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		var flame = ParticleTypes.FLAME;

		if(ModBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x1x2_PAINTING;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.NORDIC.floorLight(this, blockState);
		else if(ModBlocks.DUNMER_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.DUNMER.floorLight(this, blockState);
		else if(ModBlocks.VENTHYR_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.VENTHYR.floorLight(this, blockState);
		else if(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || ModBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.BONE.floorLight(this, blockState);
		else if(ModBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
			return HitBoxes.ROYAL.floorLight(this, blockState);

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
			if(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.isIn(blockState) || ModBlocks.BONE_WITHER_FLOOR_LIGHT.isIn(blockState) || ModBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
			{
				var x = pos.getX() + .5D;
				var y = pos.getY() + .5D + .45D;
				var z = pos.getZ() + .5D;

				if(ModBlocks.ROYAL_FLOOR_LIGHT.isIn(blockState))
					y += .075D;

				var facing = getFacing(blockState).getClockWise();
				var stepX = facing.getStepX();
				var stepZ = facing.getStepZ();

				onLightParticle(level, pos, blockState, x, y, z, rng);
				onLightParticle(level, pos, blockState, x + (stepX * .25D), y - .05D, z + (stepZ * .25D), rng);
				onLightParticle(level, pos, blockState, x - (stepX * .25D), y - .05D, z - (stepZ * .25D), rng);
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
			IDyeable.appendHoverText(tooltip);
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