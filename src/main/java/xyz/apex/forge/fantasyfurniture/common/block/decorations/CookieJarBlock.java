package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllBlockEntities;
import xyz.apex.forge.fantasyfurniture.AllMenus;
import xyz.apex.forge.fantasyfurniture.common.block.entity.CookieJarBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.CookieJarMenu;

import java.util.function.Consumer;

public final class CookieJarBlock extends BaseBlock.WithContainer<CookieJarBlockEntity, CookieJarMenu>
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(4, 0, 4, 12, 9, 12),
			Block.box(5, 9, 5, 11, 10, 11),
			Block.box(4.5, 10, 4.5, 11.5, 12, 11.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final EnumProperty<FillStage> FILL_STAGE = EnumProperty.create("fill_stage", FillStage.class);

	public CookieJarBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(FILL_STAGE, FillStage.EMPTY));
	}

	@Override
	protected MenuType<CookieJarMenu> getContainerType()
	{
		return AllMenus.COOKIE_JAR_MENU.get();
	}

	@Override
	protected BlockEntityType<CookieJarBlockEntity> getBlockEntityType()
	{
		return AllBlockEntities.COOKIE_JAR_BLOCK_ENTITY.get();
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(FILL_STAGE);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPER.get(getFacing(blockState));
	}

	public enum FillStage implements StringRepresentable
	{
		FULL("full"),
		HALF("half"),
		EMPTY("empty");

		public final String serializedName;

		FillStage(String serializedName)
		{
			this.serializedName = serializedName;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}
