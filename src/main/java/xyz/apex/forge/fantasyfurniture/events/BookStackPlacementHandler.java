/*
package xyz.apex.forge.fantasyfurniture.events;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.BookStackBlock;
import xyz.apex.forge.fantasyfurniture.init.Decorations;

import javax.annotation.Nullable;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = FantasyFurniture.ID)
public final class BookStackPlacementHandler
{
	*/
/*
	 *  Custom Block Placement handle for Book Stack blocks
	 *      These blocks have no registered BlockItem and are placed via the vanilla BOOK Item
	 *
	 *      This handler uses similar logic to the Vanilla BlockItem#place() method to place
	 *      down book stacks directly from the BOOK Item
	 *
	 *      This handler also manages when a BOOK is placed onto a BOOK STACK
	 *      Doing this will try to increment the amount of books in the BOOK STACK
	 *          If this fails at all it will then try to place down a new BOOK STACK
	 *//*


	@SubscribeEvent
	public static void onItemRightClick(PlayerInteractEvent.RightClickBlock event)
	{
		World level = event.getWorld();
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockRayTraceResult hitVec = event.getHitVec();

		PlayerEntity player = event.getPlayer();
		Hand hand = event.getHand();
		ItemStack stack = event.getItemStack();

		if(stack.getItem() == Items.BOOK)
		{
			BlockState lookingBlockState = level.getBlockState(pos);

			if(Decorations.BOOK_STACK.hasBlockState(lookingBlockState))
			{
				ActionResultType placeBookResult = lookingBlockState.use(level, player, hand, hitVec);

				if(placeBookResult.consumesAction())
				{
					event.setCancellationResult(placeBookResult);
					event.setUseItem(Event.Result.DENY);
					event.setUseBlock(Event.Result.DENY);
					event.setCanceled(true);
					return;
				}
			}

			if(face != null && stack.getItem() == Items.BOOK)
			{
				BlockPos placementPos = pos.relative(face);
				BlockItemUseContext ctx = new BlockItemUseContext(level, player, hand, stack, hitVec);

				if(level.getBlockState(placementPos).canBeReplaced(ctx))
				{
					BookStackBlock block = Decorations.BOOK_STACK.asBlock();
					ActionResultType placementResult = placeBlock(ctx, block);

					if(placementResult.consumesAction())
					{
						player.swing(hand);
						event.setCancellationResult(placementResult);
						event.setUseItem(Event.Result.DENY);
						event.setUseBlock(Event.Result.DENY);
						event.setCanceled(true);
					}
				}
			}
		}
	}

	public static ActionResultType placeBlock(BlockItemUseContext ctx, Block block)
	{
		if(ctx.canPlace())
		{
			BlockState placementState = block.getStateForPlacement(ctx);

			if(placementState != null)
			{
				PlayerEntity player = ctx.getPlayer();
				ISelectionContext selection = player == null ? ISelectionContext.empty() : ISelectionContext.of(player);

				World level = ctx.getLevel();
				BlockPos pos = ctx.getClickedPos();

				if(placementState.canSurvive(level, pos) && level.isUnobstructed(placementState, pos, selection))
				{
					if(level.setBlock(pos, placementState, 11))
					{
						ItemStack stack = ctx.getItemInHand();
						BlockState levelBlockState = level.getBlockState(pos);
						Block levelBlock = levelBlockState.getBlock();

						if(levelBlock == placementState.getBlock())
						{
							levelBlockState = updateBlockStateFromTag(level, pos, levelBlockState, stack);
							updateCustomBlockEntityTag(level, pos, levelBlockState, player, stack);
							block.setPlacedBy(level, pos, levelBlockState, player, stack);

							if(player instanceof ServerPlayerEntity)
								CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
						}

						SoundType soundType = levelBlockState.getSoundType(level, pos, player);
						level.playSound(player, pos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1F) / 2F, soundType.getPitch() * .8F);

						if(player == null || !player.abilities.instabuild)
							stack.shrink(1);

						return ActionResultType.sidedSuccess(level.isClientSide);
					}
				}
			}
		}

		return ActionResultType.FAIL;
	}

	private static boolean updateCustomBlockEntityTag(World level, BlockPos pos, BlockState blockState, @Nullable PlayerEntity player, ItemStack stack)
	{
		MinecraftServer server = level.getServer();
		CompoundNBT stackTag = stack.getTag();
		TileEntity blockEntity = level.getBlockEntity(pos);

		if(server != null && blockEntity != null)
		{
			if(!level.isClientSide && blockEntity.onlyOpCanSetNbt() && (player == null || !player.canUseGameMasterBlocks()))
				return false;

			if(stackTag != null && stackTag.contains("BlockEntityTag", Constants.NBT.TAG_COMPOUND))
			{
				CompoundNBT blockEntityTag = stackTag.getCompound("BlockEntityTag");
				CompoundNBT newBlockEntityTag = blockEntity.save(new CompoundNBT());
				CompoundNBT originalBlockEntityTag = newBlockEntityTag.copy();
				newBlockEntityTag.merge(blockEntityTag);
				newBlockEntityTag.putInt("X", pos.getX());
				newBlockEntityTag.putInt("Y", pos.getY());
				newBlockEntityTag.putInt("Z", pos.getZ());

				if(!newBlockEntityTag.equals(originalBlockEntityTag))
				{
					blockEntity.load(blockState, newBlockEntityTag);
					blockEntity.setChanged();
					return true;
				}
			}
		}

		return false;
	}

	private static BlockState updateBlockStateFromTag(World level, BlockPos pos, BlockState blockState, ItemStack stack)
	{
		BlockState result = blockState;
		CompoundNBT stackTag = stack.getTag();

		if(stackTag != null)
		{
			if(stackTag.contains("BlockStackTag", Constants.NBT.TAG_COMPOUND))
			{
				CompoundNBT blockStateTag = stackTag.getCompound("BlockStackTag");
				StateContainer<Block, BlockState> stateContainer = blockState.getBlock().getStateDefinition();

				for(String key : blockStateTag.getAllKeys())
				{
					Property<?> property = stateContainer.getProperty(key);

					if(property != null)
					{
						String value = Objects.requireNonNull(stackTag.get(key)).getAsString();
						result = updateBlockState(result, property, value);
					}
				}
			}
		}

		if(result != blockState)
			level.setBlock(pos, result, 2);

		return result;
	}

	private static <T extends Comparable<T>> BlockState updateBlockState(BlockState blockState, Property<T> property, String value)
	{
		return property.getValue(value).map(v -> blockState.setValue(property, v)).orElse(blockState);
	}
}
*/
