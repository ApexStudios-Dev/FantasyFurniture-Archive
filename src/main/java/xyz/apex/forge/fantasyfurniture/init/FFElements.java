package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.collect.Lists;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import xyz.apex.forge.fantasyfurniture.block.entity.*;
import xyz.apex.forge.fantasyfurniture.client.screen.*;
import xyz.apex.forge.fantasyfurniture.container.*;

public final class FFElements
{
	public static final ResourceLocation SMALL_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/large_storage.png");

	public static final ModBlocks[] FURNITURE_SETS = new ModBlocks[] {
			NordicBlocks.INSTANCE,
			VenthyrBlocks.INSTANCE,
			DunmerBlocks.INSTANCE,
			BoneBlocks.INSTANCE
	};

	// region: Drawer
	public static final MenuEntry<SetDrawerContainer> DRAWER_CONTAINER = Registrations.container("drawer", SetDrawerContainer::new, () -> SetDrawerContainerScreen::new);
	public static final BlockEntityEntry<SetDrawerBlockEntity> DRAWER_BLOCK_ENTITY;
	// endregion

	// region: Chest
	public static final MenuEntry<SetChestContainer> CHEST_CONTAINER = Registrations.container("chest", SetChestContainer::new, () -> SetChestContainerScreen::new);
	public static final BlockEntityEntry<SetChestBlockEntity> CHEST_BLOCK_ENTITY;
	// endregion

	// region: Dresser
	public static final MenuEntry<SetDresserContainer> DRESSER_CONTAINER = Registrations.container("dresser", SetDresserContainer::new, () -> SetDresserContainerScreen::new);
	public static final BlockEntityEntry<SetDresserBlockEntity> DRESSER_BLOCK_ENTITY;
	// endregion

	// region: Desk
	public static final MenuEntry<SetDeskContainer> DESK_CONTAINER = Registrations.container("desk", SetDeskContainer::new, () -> SetDeskContainerScreen::new);
	public static final BlockEntityEntry<SetDeskBlockEntity> DESK_BLOCK_ENTITY;
	// endregion

	// region: Wardrobe
	public static final MenuEntry<SetWardrobeContainer> WARDROBE_CONTAINER = Registrations.container("wardrobe", SetWardrobeContainer::new, () -> SetWardrobeContainerScreen::new);
	public static final BlockEntityEntry<SetWardrobeBlockEntity> WARDROBE_BLOCK_ENTITY;
	// endregion

	// region: Bookshelf
	public static final MenuEntry<SetBookshelfContainer> BOOKSHELF_CONTAINER = Registrations.container("bookshelf", SetBookshelfContainer::new, () -> SetBookshelfContainerScreen::new);
	public static final BlockEntityEntry<SetBookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY;
	// endregion

	// region: Lockbox
	public static final MenuEntry<SetLockboxContainer> LOCKBOX_CONTAINER = Registrations.container("lockbox", SetLockboxContainer::new, () -> SetLockboxContainerScreen::new);
	public static final BlockEntityEntry<SetLockboxBlockEntity> LOCKBOX_BLOCK_ENTITY;
	// endregion

	static
	{
		var drawers = Lists.<NonNullSupplier<Block>>newArrayList();
		var chests = Lists.<NonNullSupplier<Block>>newArrayList();
		var dressers = Lists.<NonNullSupplier<Block>>newArrayList();
		var wardrobes = Lists.<NonNullSupplier<Block>>newArrayList();
		var bookshelves = Lists.<NonNullSupplier<Block>>newArrayList();
		var desks = Lists.<NonNullSupplier<Block>>newArrayList();
		var lockboxes = Lists.<NonNullSupplier<Block>>newArrayList();

		for(var furnitureSet : FURNITURE_SETS)
		{
			drawers.add(furnitureSet.drawerBlock::get);
			chests.add(furnitureSet.chestBlock::get);
			dressers.add(furnitureSet.dresserBlock::get);
			wardrobes.add(furnitureSet.wardrobeBlock::get);
			bookshelves.add(furnitureSet.bookshelfBlock::get);
			desks.add(furnitureSet.deskLeftBlock::get);
			desks.add(furnitureSet.deskRightBlock::get);
			lockboxes.add(furnitureSet.lockboxBlock::get);
		}

		DRAWER_BLOCK_ENTITY = blockEntity("drawer", SetDrawerBlockEntity::new, drawers.toArray(NonNullSupplier[]::new));
		CHEST_BLOCK_ENTITY = blockEntity("chest", SetChestBlockEntity::new, chests.toArray(NonNullSupplier[]::new));
		DRESSER_BLOCK_ENTITY = blockEntity("dresser", SetDresserBlockEntity::new, dressers.toArray(NonNullSupplier[]::new));
		WARDROBE_BLOCK_ENTITY = blockEntity("wardrobe", SetWardrobeBlockEntity::new, wardrobes.toArray(NonNullSupplier[]::new));
		BOOKSHELF_BLOCK_ENTITY = blockEntity("bookshelf", SetBookshelfBlockEntity::new, bookshelves.toArray(NonNullSupplier[]::new));
		DESK_BLOCK_ENTITY = blockEntity("desk", SetDeskBlockEntity::new, desks.toArray(NonNullSupplier[]::new));
		LOCKBOX_BLOCK_ENTITY = blockEntity("lockbox", SetLockboxBlockEntity::new, lockboxes.toArray(NonNullSupplier[]::new));
	}

	static void bootstrap()
	{
	}

	private static <BLOCK_ENTITY extends BlockEntity> BlockEntityEntry<BLOCK_ENTITY> blockEntity(String registryName, BlockEntityBuilder.BlockEntityFactory<BLOCK_ENTITY> blockEntityFactory, NonNullSupplier<Block>... validBlocks)
	{
		return FFRegistry.INSTANCE.object(registryName).blockEntity(blockEntityFactory).validBlocks(validBlocks).register();
	}
}
