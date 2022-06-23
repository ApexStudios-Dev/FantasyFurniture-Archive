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

import java.util.List;

public final class FFElements
{
	public static final ResourceLocation SMALL_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = FFRegistry.INSTANCE.id("textures/gui/container/large_storage.png");

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

	static
	{
		List<NonNullSupplier<Block>> drawers = Lists.newArrayList();
		List<NonNullSupplier<Block>> chests = Lists.newArrayList();
		List<NonNullSupplier<Block>> dressers = Lists.newArrayList();
		List<NonNullSupplier<Block>> wardrobes = Lists.newArrayList();
		List<NonNullSupplier<Block>> bookshelves = Lists.newArrayList();
		List<NonNullSupplier<Block>> desks = Lists.newArrayList();

		for(var furnitureSet : FurnitureSet.values())
		{
			drawers.add(furnitureSet.drawerBlock::get);
			chests.add(furnitureSet.chestBlock::get);
			dressers.add(furnitureSet.dresserBlock::get);
			wardrobes.add(furnitureSet.wardrobeBlock::get);
			bookshelves.add(furnitureSet.bookshelfBlock::get);
			desks.add(furnitureSet.deskLeftBlock::get);
			desks.add(furnitureSet.deskRightBlock::get);
		}

		DRAWER_BLOCK_ENTITY = blockEntity("drawer", SetDrawerBlockEntity::new, drawers.toArray(new NonNullSupplier[0]));
		CHEST_BLOCK_ENTITY = blockEntity("chest", SetChestBlockEntity::new, chests.toArray(new NonNullSupplier[0]));
		DRESSER_BLOCK_ENTITY = blockEntity("dresser", SetDresserBlockEntity::new, dressers.toArray(new NonNullSupplier[0]));
		WARDROBE_BLOCK_ENTITY = blockEntity("wardrobe", SetWardrobeBlockEntity::new, wardrobes.toArray(new NonNullSupplier[0]));
		BOOKSHELF_BLOCK_ENTITY = blockEntity("bookshelf", SetBookshelfBlockEntity::new, bookshelves.toArray(new NonNullSupplier[0]));
		DESK_BLOCK_ENTITY = blockEntity("desk", SetDeskBlockEntity::new, desks.toArray(new NonNullSupplier[0]));
	}

	static void bootstrap()
	{
	}

	private static <BLOCK_ENTITY extends BlockEntity> BlockEntityEntry<BLOCK_ENTITY> blockEntity(String registryName, BlockEntityBuilder.BlockEntityFactory<BLOCK_ENTITY> blockEntityFactory, NonNullSupplier<Block>... validBlocks)
	{
		return FFRegistry.INSTANCE.object(registryName).blockEntity(blockEntityFactory).validBlocks(validBlocks).register();
	}
}
