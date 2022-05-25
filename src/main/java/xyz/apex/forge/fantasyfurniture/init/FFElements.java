package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.block.entity.*;
import xyz.apex.forge.fantasyfurniture.client.renderer.entity.SeatEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.screen.*;
import xyz.apex.forge.fantasyfurniture.container.*;
import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.ContainerEntry;
import xyz.apex.forge.utility.registrator.entry.EntityEntry;
import xyz.apex.forge.utility.registrator.factory.BlockEntityFactory;
import xyz.apex.java.utility.nullness.NonnullSupplier;

import java.util.List;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FFElements
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Seat Entity
	public static final EntityEntry<SeatEntity> SEAT_ENTITY = REGISTRY
			.<SeatEntity>entity("seat", EntityClassification.MISC, SeatEntity::new)
				.lang("Seat")
				.lang(EN_GB, "Seat")

				.sized(0F, 0F)
				.setCustomClientFactory((spawnEntity, level) -> new SeatEntity(level))

				.noSummon()
				.fireImmune()
				.immuneTo(() -> Blocks.TNT, () -> Blocks.LAVA)

				.renderer(() -> SeatEntityRenderer::new)
			.register();
	// endregion

	public static final ResourceLocation SMALL_STORAGE_TEXTURE = REGISTRY.id("textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = REGISTRY.id("textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = REGISTRY.id("textures/gui/container/large_storage.png");

	// region: Drawer
	public static final ContainerEntry<SetDrawerContainer> DRAWER_CONTAINER = Registrations.container("drawer", SetDrawerContainer.ROWS, SetDrawerContainer.COLS, SetDrawerContainer::new, () -> SetDrawerContainerScreen::new);
	public static final BlockEntityEntry<SetDrawerBlockEntity> DRAWER_BLOCK_ENTITY;
	// endregion

	// region: Chest
	public static final ContainerEntry<SetChestContainer> CHEST_CONTAINER = Registrations.container("chest", SetChestContainer.ROWS, SetChestContainer.COLS, SetChestContainer::new, () -> SetChestContainerScreen::new);
	public static final BlockEntityEntry<SetChestBlockEntity> CHEST_BLOCK_ENTITY;
	// endregion

	// region: Dresser
	public static final ContainerEntry<SetDresserContainer> DRESSER_CONTAINER = Registrations.container("dresser", SetDresserContainer.ROWS, SetDresserContainer.COLS, SetDresserContainer::new, () -> SetDresserContainerScreen::new);
	public static final BlockEntityEntry<SetDresserBlockEntity> DRESSER_BLOCK_ENTITY;
	// endregion

	// region: Wardrobe
	public static final ContainerEntry<SetWardrobeContainer> WARDROBE_CONTAINER = Registrations.container("wardrobe", SetWardrobeContainer.ROWS, SetWardrobeContainer.COLS, SetWardrobeContainer::new, () -> SetWardrobeContainerScreen::new);
	public static final BlockEntityEntry<SetWardrobeBlockEntity> WARDROBE_BLOCK_ENTITY;
	// endregion

	// region: Bookshelf
	public static final ContainerEntry<SetBookshelfContainer> BOOKSHELF_CONTAINER = Registrations.container("bookshelf", SetBookshelfContainer.ROWS, SetBookshelfContainer.COLS, SetBookshelfContainer::new, () -> SetBookshelfContainerScreen::new);
	public static final BlockEntityEntry<SetBookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY;
	// endregion

	static
	{
		List<NonnullSupplier<Block>> drawers = Lists.newArrayList();
		List<NonnullSupplier<Block>> chests = Lists.newArrayList();
		List<NonnullSupplier<Block>> dressers = Lists.newArrayList();
		List<NonnullSupplier<Block>> wardrobes = Lists.newArrayList();
		List<NonnullSupplier<Block>> bookshelves = Lists.newArrayList();

		for(FurnitureSet furnitureSet : FurnitureSet.values())
		{
			drawers.add(furnitureSet.drawerBlock::asBlock);
			chests.add(furnitureSet.chestBlock::asBlock);
			dressers.add(furnitureSet.dresserBlock::asBlock);
			wardrobes.add(furnitureSet.wardrobeBlock::asBlock);
			bookshelves.add(furnitureSet.bookshelfBlock::asBlock);
		}

		DRAWER_BLOCK_ENTITY = blockEntity("drawer", SetDrawerBlockEntity::new, drawers.toArray(new NonnullSupplier[0]));
		CHEST_BLOCK_ENTITY = blockEntity("chest", SetChestBlockEntity::new, chests.toArray(new NonnullSupplier[0]));
		DRESSER_BLOCK_ENTITY = blockEntity("dresser", SetDresserBlockEntity::new, dressers.toArray(new NonnullSupplier[0]));
		WARDROBE_BLOCK_ENTITY = blockEntity("wardrobe", SetWardrobeBlockEntity::new, wardrobes.toArray(new NonnullSupplier[0]));
		BOOKSHELF_BLOCK_ENTITY = blockEntity("bookshelf", SetBookshelfBlockEntity::new, bookshelves.toArray(new NonnullSupplier[0]));
	}

	static void bootstrap()
	{
	}

	private static <BLOCK_ENTITY extends TileEntity> BlockEntityEntry<BLOCK_ENTITY> blockEntity(String registryName, BlockEntityFactory<BLOCK_ENTITY> blockEntityFactory, NonnullSupplier<Block>... validBlocks)
	{
		return REGISTRY.blockEntity(registryName, blockEntityFactory).validBlocks(validBlocks).register();
	}
}
