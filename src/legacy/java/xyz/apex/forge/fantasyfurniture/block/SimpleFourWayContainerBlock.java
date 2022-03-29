package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.IItemHandler;

import xyz.apex.forge.apexcore.lib.util.ContainerHelper;
import xyz.apex.forge.fantasyfurniture.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.container.InventoryContainer;

import javax.annotation.Nullable;

public abstract class SimpleFourWayContainerBlock<BLOCK_ENTITY extends InventoryBlockEntity<CONTAINER>, CONTAINER extends InventoryContainer> extends SimpleFourWayBlockEntityBlock<BLOCK_ENTITY>
{
	public SimpleFourWayContainerBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		ItemStack stack = player.getItemInHand(hand);

		if(player instanceof ServerPlayerEntity)
		{
			if(!openContainerScreen(blockState, level, pos, (ServerPlayerEntity) player, hand, stack, stack.getHoverName()))
				return ActionResultType.PASS;
		}

		return ActionResultType.sidedSuccess(level.isClientSide);
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.setPlacedBy(level, pos, blockState, placer, stack);

		BLOCK_ENTITY blockEntity = getBlockEntity(level, pos);

		if(blockEntity != null && stack.hasCustomHoverName())
		{
			ITextComponent customName = stack.getHoverName();
			blockEntity.setCustomName(customName);
		}
	}

	@Override
	public void onRemove(BlockState blockState, World level, BlockPos pos, BlockState newBlockState, boolean isMoving)
	{
		BLOCK_ENTITY blockEntity = getBlockEntity(level, pos);

		if(blockEntity != null)
		{
			IItemHandler itemHandler = blockEntity.getItemHandler();

			for(int i = 0; i < itemHandler.getSlots(); i++)
			{
				ItemStack stack = itemHandler.getStackInSlot(i);
				InventoryHelper.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
			}
		}

		super.onRemove(blockState, level, pos, newBlockState, isMoving);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState blockState)
	{
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World level, BlockPos pos)
	{
		return ContainerHelper.getRedstoneSignalFromContainer(level, pos);
	}

	protected boolean openContainerScreen(BlockState blockState, World level, BlockPos pos, ServerPlayerEntity player, Hand hand, ItemStack stack, ITextComponent titleComponent)
	{
		NetworkHooks.openGui(player, getMenuProvider(blockState, level, pos));
		return true;
	}
}
