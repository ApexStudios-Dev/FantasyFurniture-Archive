package xyz.apex.forge.fantasyfurniture.block;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.Property;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.Decorations;
import xyz.apex.java.utility.Lazy;
import xyz.apex.java.utility.nullness.NonnullSupplier;

import java.util.Map;

public final class BerryBasketBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 3.5D, 14D, 5D, 12.5D),
			box(1.5D, 5D, 3D, 14.5D, 6D, 13D),
			box(7D, 6D, 3.25D, 9D, 11.75D, 12.75D)
	);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	private static final Map<ResourceLocation, Lazy<BerryBasketBlock>> basketMap = Maps.newHashMap();
	private static final Map<ResourceLocation, Lazy<Item>> berryMap = Maps.newHashMap();

	public BerryBasketBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		boolean waterLogged = blockState.getValue(WATERLOGGED);

		// fail and let waterlogging logic do its thing
		if((waterLogged && item == Items.BUCKET) || (!waterLogged && item == Items.WATER_BUCKET))
			return ActionResultType.FAIL;

		ResourceLocation basketName = getRegistryName();
		Validate.notNull(basketName); // should never happen

		if(berryMap.containsKey(basketName))
		{
			Item berry = berryMap.get(basketName).get();
			popResource(level, pos, berry.getDefaultInstance());
			BlockState emptyBlockState = Decorations.BERRY_BASKET_EMPTY.defaultBlockState();
			emptyBlockState = copyBlockProperties(blockState, emptyBlockState);
			level.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 1F, 1F);
			level.setBlockAndUpdate(pos, emptyBlockState);
			return ActionResultType.sidedSuccess(level.isClientSide);
		}
		else
		{
			if(!stack.isEmpty())
			{
				ResourceLocation berryName = item.getRegistryName();
				Validate.notNull(berryName); // should never happen

				if(basketMap.containsKey(berryName))
				{
					BerryBasketBlock basket = basketMap.get(berryName).get();
					BlockState filledBlockState = basket.defaultBlockState();
					filledBlockState = copyBlockProperties(blockState, filledBlockState);
					level.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 1F, 1F);
					level.setBlockAndUpdate(pos, filledBlockState);
					return ActionResultType.sidedSuccess(level.isClientSide);
				}
			}
		}

		return ActionResultType.FAIL;
	}

	public static void registerBasketMapping(IItemProvider berry, NonnullSupplier<BerryBasketBlock> filledBasketBlock)
	{
		ResourceLocation berryName = berry.asItem().getRegistryName();
		ResourceLocation basketName = filledBasketBlock.get().getRegistryName();

		Validate.notNull(berryName, "Attempt to register Berry <-> Basket mapping for invalid Item state");
		Validate.notNull(basketName, "Attempt to register Berry <-> Basket mapping for invalid Block state");

		Validate.isTrue(!basketMap.containsKey(berryName), "Duplicate to Berry <-> Basket mapping for Item '%s' (This is not allowed!)", berryName);
		Validate.isTrue(!berryMap.containsKey(basketName), "Duplicate to Berry <-> Basket mapping for Item '%s' (This is not allowed!)", basketName);

		basketMap.put(berryName, Lazy.of(filledBasketBlock));
		berryMap.put(basketName, Lazy.of(berry::asItem));
	}

	public static BlockState copyBlockProperties(BlockState from, BlockState to)
	{
		for(Property<?> property : from.getProperties())
		{
			to = copyBlockProperty(from, to, property);
		}

		return to;
	}

	public static <T extends Comparable<T>> BlockState copyBlockProperty(BlockState from, BlockState to, Property<T> property)
	{
		if(from.hasProperty(property) && to.hasProperty(property))
			return to.setValue(property, from.getValue(property));
		return to;
	}
}
