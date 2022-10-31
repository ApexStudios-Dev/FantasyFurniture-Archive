package xyz.apex.forge.fantasyfurniture.common.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.OvenMenu;

public final class OvenBlockEntity extends InventoryBlockEntity implements ContainerData
{
	public static final String NBT_BURN_TIME = "BurnTime";
	public static final String NBT_SMELT_TIME = "SmeltTime";
	public static final String NBT_TOTAL_SMELT_TIME = "TotalSmeltTime";
	public static final String NBT_RECIPES_USED = "RecipesUsed";

	private int burnTime;
	private int totalBurnTime;
	private int smeltTime;
	private int totalSmeltTime;
	@Nullable private AbstractCookingRecipe recipe;

	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	private final RecipeType<? extends AbstractCookingRecipe> recipeType = RecipeType.SMOKING;
	private final Container container;

	public OvenBlockEntity(BlockEntityType<? extends OvenBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, OvenMenu.SLOT_COUNT);

		container = new RecipeWrapper(itemHandler);
	}

	@Override
	public CompoundTag serializeData()
	{
		var tagCompound = super.serializeData();

		tagCompound.putInt(NBT_BURN_TIME, burnTime);
		tagCompound.putInt(NBT_SMELT_TIME, smeltTime);
		tagCompound.putInt(NBT_TOTAL_SMELT_TIME, totalSmeltTime);

		var recipesUsedTag = new CompoundTag();
		recipesUsed.forEach((recipeId, amount) -> recipesUsedTag.putInt(recipeId.toString(), amount));
		tagCompound.put(NBT_RECIPES_USED, recipesUsedTag);

		return tagCompound;
	}

	@Override
	public void deserializeData(CompoundTag tagCompound)
	{
		super.deserializeData(tagCompound);

		burnTime = tagCompound.getInt(NBT_BURN_TIME);
		totalBurnTime = getBurnTime(itemHandler.getStackInSlot(OvenMenu.SLOT_FUEL));
		smeltTime = tagCompound.getInt(NBT_SMELT_TIME);
		totalSmeltTime = tagCompound.getInt(NBT_TOTAL_SMELT_TIME);

		var recipesUsedTag = tagCompound.getCompound(NBT_RECIPES_USED);

		for(var key : recipesUsedTag.getAllKeys())
		{
			recipesUsed.put(new ResourceLocation(key), recipesUsedTag.getInt(key));
		}
	}

	@Override
	public int get(int index)
	{
		return switch(index) {
			case OvenMenu.DATA_SLOT_BURN_TIME -> burnTime;
			case OvenMenu.DATA_SLOT_TOTAL_BURN_TIME -> totalBurnTime;
			case OvenMenu.DATA_SLOT_SMELT_TIME -> smeltTime;
			case OvenMenu.DATA_SLOT_TOTAL_SMELT_TIME -> totalSmeltTime;
			default -> -1;
		};
	}

	@Override
	public void set(int index, int value)
	{
		switch(index) {
			case OvenMenu.DATA_SLOT_BURN_TIME -> burnTime = value;
			case OvenMenu.DATA_SLOT_TOTAL_BURN_TIME -> totalBurnTime = value;
			case OvenMenu.DATA_SLOT_SMELT_TIME -> smeltTime = value;
			case OvenMenu.DATA_SLOT_TOTAL_SMELT_TIME -> totalSmeltTime = value;
			default -> { }
		}
	}

	@Override
	public int getCount()
	{
		return OvenMenu.DATA_SLOT_COUNT;
	}

	public void awardRecipesAndExperience(ServerLevel level, @Nullable ServerPlayer player, Vec3 pos)
	{
		for(var entry : recipesUsed.object2IntEntrySet())
		{
			level.getRecipeManager().byKey(entry.getKey()).filter(AbstractCookingRecipe.class::isInstance).map(AbstractCookingRecipe.class::cast).ifPresent(recipe -> {
				var useAmount = entry.getIntValue();
				var experience = recipe.getExperience();

				var i = Mth.floor((float) useAmount * experience);
				var f = Mth.frac((float) useAmount * experience);

				if(f != 0F && Math.random() < (double) f)
					i++;

				ExperienceOrb.award(level, pos, i);
			});
		}
	}

	public void serverTick(Level level, BlockPos pos, BlockState blockState)
	{
		var input = itemHandler.getStackInSlot(OvenMenu.SLOT_INPUT);
		var fuel = itemHandler.getStackInSlot(OvenMenu.SLOT_FUEL);
		var output = itemHandler.getStackInSlot(OvenMenu.SLOT_OUTPUT);

		var smeltAmount = itemHandler.getSlotLimit(OvenMenu.SLOT_OUTPUT);
		var isBurning = isBurning();
		var dirty = false;

		if(isBurning)
			burnTime = Math.max(burnTime - 1, 0);
		else
		{
			if(smeltTime > 0)
				smeltTime = Math.max(smeltTime - 1, 0);
			else
			{
				smeltTime = 0;
				totalSmeltTime = 0;
				recipe = null;
			}
		}

		if(isBurning || canBurn(fuel))
		{
			recipe = level.getRecipeManager().getRecipeFor(recipeType, container, level).orElse(null);

			if(!isBurning && canSmelt(output, smeltAmount))
			{
				var burnTime = getBurnTime(fuel);

				if(burnTime > 0)
				{
					this.burnTime = burnTime;
					totalBurnTime = burnTime;
					fuel = decrementFuel(fuel);
					dirty = true;
				}
			}

			if(recipe == null)
			{
				totalSmeltTime = 0;
				smeltTime = 0;
			}
			else
			{
				if(totalSmeltTime == 0)
				{
					if(canSmelt(output, smeltAmount))
					{
						smeltTime = 0;
						totalSmeltTime = recipe.getCookingTime();
					}
				}
				else
				{
					smeltTime = Math.min(smeltTime + 1, totalSmeltTime);

					if(smeltTime == totalSmeltTime)
					{
						var result = recipe.assemble(container);

						if(output.isEmpty())
						{
							output = result.copy();
							itemHandler.setStackInSlot(OvenMenu.SLOT_OUTPUT, output);
						}
						else if(output.is(result.getItem()))
							output.grow(result.getCount());

						recipesUsed.addTo(recipe.getId(), 1);
						input.shrink(1);
						recipe = null;
						totalSmeltTime = 0;
						smeltTime = 0;
						dirty = true;
					}
				}
			}
		}

		if(isBurning != isBlockStateLit(level, pos, blockState))
		{
			dirty = setBlockStateLit(level, pos, blockState, isBurning);
			blockState = level.getBlockState(pos);
		}

		if(dirty)
			setChanged(level, pos, blockState);
	}

	private boolean isBlockStateLit(Level level, BlockPos pos, BlockState blockState)
	{
		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);
			return level.getBlockState(origin).getValue(BlockStateProperties.LIT);
		}

		return blockState.getValue(BlockStateProperties.LIT);
	}

	private boolean setBlockStateLit(Level level, BlockPos pos, BlockState blockState, boolean lit)
	{
		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);
			var changed = false;

			for(var localPos : multiBlock.getMultiBlockLocalPositions())
			{
				var worldPos = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localPos);
				var worldBlockState = level.getBlockState(worldPos);
				var newWorldBlockState = worldBlockState.setValue(BlockStateProperties.LIT, lit);

				if(newWorldBlockState != worldBlockState)
					changed = level.setBlockAndUpdate(worldPos, newWorldBlockState) || changed;
			}

			return changed;
		}

		var newBlockState = blockState.setValue(BlockStateProperties.LIT, lit);

		if(newBlockState != blockState)
			return level.setBlockAndUpdate(pos, newBlockState);

		return false;
	}

	private ItemStack decrementFuel(ItemStack fuel)
	{
		if(fuel.hasContainerItem())
		{
			var result = fuel.getContainerItem();
			itemHandler.setStackInSlot(OvenMenu.SLOT_FUEL, result);
			return result;
		}

		fuel.shrink(1);
		return fuel;
	}

	private boolean canSmelt(ItemStack output, int amount)
	{
		if(recipe != null)
		{
			var result = recipe.assemble(container);

			if(result.isEmpty())
				return false;

			if(output.isEmpty())
				return true;
			if(!output.sameItem(result))
				return false;
			if(output.getCount() + result.getCount() <= amount && output.getCount() + result.getCount() <= output.getMaxStackSize())
				return true;
			return output.getCount() + result.getCount() <= result.getMaxStackSize();
		}

		return false;
	}

	private boolean isBurning()
	{
		return burnTime > 0;
	}

	private int getBurnTime(ItemStack stack)
	{
		if(stack.isEmpty() || stack.is(ItemTags.NON_FLAMMABLE_WOOD))
			return 0;

		return ForgeHooks.getBurnTime(stack, recipeType);
	}

	public boolean canBurn(ItemStack stack)
	{
		return getBurnTime(stack) > 0;
	}
}
