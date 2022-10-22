package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.furniture.IDyeable;

public final class MeadBottlesBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 10.5D, 9.5D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 9D, 12D, 10.5D, 12D),
			box(3.8065629880438667D, 0D, 3.958803906224312D, 7.806562988043867D, 10.5D, 7.958803906224312D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(7D, 0D, 10D, 10D, 10.5D, 13D),
			box(1.8065629880438667D, 0D, 4.958803906224312D, 5.806562988043867D, 10.5D, 8.958803906224311D),
			box(10.456562988043867D, 0D, 2.458803906224312D, 14.456562988043867D, 10.5D, 6.458803906224311D)
	);

	public static final IntegerProperty BOTTLES = IntegerProperty.create("bottles", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public MeadBottlesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return BOTTLES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(BOTTLES);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		return shaper.get(facing);
	}

	@Override
	protected ItemStack getPoppedStack(Level level, BlockPos pos, BlockState blockState, int count, Player player, InteractionHand hand)
	{
		var stack = super.getPoppedStack(level, pos, blockState, count, player, hand);
		return IDyeable.getCloneItemStack(blockState, level, pos, stack);
	}
}