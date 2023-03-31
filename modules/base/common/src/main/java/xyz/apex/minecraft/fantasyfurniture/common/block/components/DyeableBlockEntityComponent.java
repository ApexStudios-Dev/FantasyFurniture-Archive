package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BaseEntityBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BaseBlockEntityComponent;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentType;
import xyz.apex.minecraft.apexcore.common.component.entity.BlockEntityComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.entity.types.NameableBlockEntityComponent;
import xyz.apex.minecraft.apexcore.common.hooks.ItemHooks;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Optional;
import java.util.OptionalInt;

public final class DyeableBlockEntityComponent extends BaseBlockEntityComponent
{
    public static final BlockEntityComponentType<DyeableBlockEntityComponent> COMPONENT_TYPE = BlockEntityComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "dyeable"),
            DyeableBlockEntityComponent::new
    );

    public static final TextColor APEX_COLOR = TextColor.fromRgb(0x3C40C7);
    public static final TextColor FF_COLOR = TextColor.fromRgb(0x97C6E7);
    public static final TextColor FD_COLOR = TextColor.fromRgb(0xE27E8B);
    public static final TextColor IF_COLOR = TextColor.fromRgb(0x7A4DAF);
    public static final TextColor IR_COLOR = TextColor.fromRgb(0xB50E0E);

    public static final int TINT_INDEX = 1;

    @Nullable private TextColor color;

    private DyeableBlockEntityComponent(BlockEntityComponentHolder holder)
    {
        super(holder);
    }

    public InteractionResult tryDyeOrClear(Level level, Player player, InteractionHand hand)
    {
        var result = tryDyeFrom(level, player, hand);
        if(result.consumesAction()) return result;
        return tryClearColor(level, player, hand);
    }

    public InteractionResult tryClearColor(Level level, Player player, InteractionHand hand)
    {
        if(color != null && player.isShiftKeyDown())
        {
            clearColor();
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    public InteractionResult tryDyeFrom(Level level, Player player, InteractionHand hand)
    {
        var stack = player.getItemInHand(hand);
        return tryDyeFrom(level, stack);
    }

    public InteractionResult tryDyeFrom(Level level, ItemStack stack)
    {
        var dyeColor = ItemHooks.getInstance().getColorForItem(stack);
        if(dyeColor == null) return InteractionResult.PASS;

        setColor(dyeColor);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public void clearColor()
    {
        color = null;

        markDirty();
        runForLevel(level -> level.sendBlockUpdated(toBlockPos(), toBlockState(), toBlockState(), Block.UPDATE_ALL));
    }

    public boolean hasColor()
    {
        return color != null;
    }

    public void setColor(TextColor color)
    {
        this.color = color;

        markDirty();
        runForLevel(level -> level.sendBlockUpdated(toBlockPos(), toBlockState(), toBlockState(), Block.UPDATE_ALL));
    }

    public void setColor(DyeColor color)
    {
        if(color == DyeColor.BLACK) setColor(0x212121);
        else
        {
            var colors = color.getTextureDiffuseColors();
            setColor(colors[0], colors[1], colors[2]);
        }
    }

    public void setColor(ChatFormatting color)
    {
        var legacy = TextColor.fromLegacyFormat(color);
        if(legacy != null) setColor(legacy);
    }

    public void setColor(int color)
    {
        setColor(TextColor.fromRgb(color));
    }

    public void setColor(String color)
    {
        var parsed = TextColor.parseColor(color);
        if(parsed != null) setColor(parsed);
    }

    public void setColor(float red, float green, float blue)
    {
        var color = Mth.color(red, green, blue);
        setColor(color);
    }

    public Optional<TextColor> getColorFromName()
    {
        return getOptionalComponent(BlockEntityComponentTypes.NAMEABLE).map(NameableBlockEntityComponent::getDisplayName).map(DyeableBlockEntityComponent::getColorFromName);
    }

    public Optional<TextColor> getColor()
    {
        return getColorFromName().or(() -> Optional.ofNullable(color));
    }

    public int getColorAsInt()
    {
        return getColor().map(TextColor::getValue).orElse(0x00FFFFFF);
    }

    public float getRed()
    {
        return FastColor.ARGB32.red(getColorAsInt());
    }

    public float getGreen()
    {
        return FastColor.ARGB32.green(getColorAsInt());
    }

    public float getBlue()
    {
        return FastColor.ARGB32.blue(getColorAsInt());
    }

    @Nullable
    public String getColorAsString()
    {
        var component = getComponent(BlockEntityComponentTypes.NAMEABLE);

        if(component != null)
        {
            var displayName = component.getDisplayName();
            if(getColorFromName(displayName) != null) return displayName.getString();
        }

        if(color != null) return color.serialize();
        return null;
    }

    @Nullable
    @Override
    public Tag writeNbt()
    {
        if(color == null) return null;
        return StringTag.valueOf(color.serialize());
    }

    @Override
    public void readNbt(Tag nbt)
    {
        if(nbt instanceof NumericTag num) color = TextColor.fromRgb(num.getAsInt());
        else if(nbt instanceof StringTag tag)
        {
            var str = tag.getAsString();
            var color = TextColor.parseColor(str);

            if(color == null)
            {
                var dyeColor = DyeColor.byName(str, null);

                if(dyeColor != null)
                {
                    var colors = dyeColor.getTextureDiffuseColors();
                    color = TextColor.fromRgb(Mth.color(colors[0], colors[1], colors[2]));
                }
            }

            if(color != null) this.color = color;
        }
    }

    @Override
    public void onRegistered(BlockEntityComponentHolder.Registrar registrar)
    {
        var blockState = toBlockState();
        Validate.isInstanceOf(BlockComponentHolder.class, blockState.getBlock());
        var holder = (BlockComponentHolder) blockState.getBlock();
        Validate.isTrue(holder.hasComponent(DyeableBlockComponent.COMPONENT_TYPE));
    }

    public static OptionalInt buildBlockColor(BlockState blockState, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex)
    {
        if(tintIndex != TINT_INDEX) return OptionalInt.empty();
        if(level == null || pos == null) return OptionalInt.empty();
        var blockEntity = BaseEntityBlockComponentHolder.getBlockEntityUnsafe(blockState, level, pos);
        if(!(blockEntity instanceof BlockEntityComponentHolder holder)) return OptionalInt.empty();
        var component = holder.getComponent(COMPONENT_TYPE);
        if(component == null) return OptionalInt.empty();
        return OptionalInt.of(component.getColorAsInt());
    }

    public static OptionalInt buildItemColor(ItemStack stack, int tintIndex)
    {
        if(tintIndex != TINT_INDEX) return OptionalInt.empty();

        if(stack.hasCustomHoverName())
        {
            var customName = stack.getHoverName();
            var color = getColorFromName(customName);
            if(color != null) return OptionalInt.of(color.getValue());
        }

        return OptionalInt.of(rainbowColor(stack.getItem()));
    }

    private static int rainbowColor(Item item)
    {
        var itemId = Item.getId(item);
        var offset = itemId * 25;
        var speed = 10000;

        var h = (float) ((Util.getMillis() + offset) % speed) / speed;
        var s = .75F;
        var b = 1F;

        return HSBtoRGB(h, s, b);
    }

    // copied from java awt
    private static int HSBtoRGB(float hue, float saturation, float brightness)
    {
        var r = 0;
        var g = 0;
        var b = 0;

        if(saturation == 0)
        {
            r = (int) (brightness * 255F + .5F);
            g = r;
            b = r;
        }
        else
        {
            var h  = (hue - (float) Math.floor(hue)) * 6F;
            var f = h - (float) Math.floor(h);
            var p = brightness * (1F - saturation);
            var q = brightness * (1F - saturation * f);
            var t = brightness * (1F - (saturation * (1F - f)));

            switch((int) h) {
                case 0 -> {
                    r = (int) (brightness * 255F + .5F);
                    g = (int) (t * 255 + .5F);
                    b = (int) (p * 255 + .5F);
                }

                case 1 -> {
                    r = (int) (q * 255F + .5F);
                    g = (int) (brightness * 255 + .5F);
                    b = (int) (p * 255 + .5F);
                }

                case 2 -> {
                    r = (int) (p * 255F + .5F);
                    g = (int) (brightness * 255 + .5F);
                    b = (int) (t * 255 + .5F);
                }

                case 3 -> {
                    r = (int) (p * 255F + .5F);
                    g = (int) (q * 255 + .5F);
                    b = (int) (brightness * 255 + .5F);
                }

                case 4 -> {
                    r = (int) (t * 255F + .5F);
                    g = (int) (p * 255 + .5F);
                    b = (int) (brightness * 255 + .5F);
                }

                case 5 -> {
                    r = (int) (brightness * 255F + .5F);
                    g = (int) (p * 255 + .5F);
                    b = (int) (q * 255 + .5F);
                }
            }
        }

        return 0xFF000000 | (r << 16) | (g << 8) | (b << 0);
    }

    @Nullable
    private static TextColor getColorFromName(Component name)
    {
        var str = name.getString();
        // custom hover names are wrapped in `[` `]`
        if(str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') str = str.substring(1, str.length() - 1);

        if(StringUtils.equalsAnyIgnoreCase(str, "apex", "apexstudios")) return APEX_COLOR;
        if(StringUtils.equalsAnyIgnoreCase(str, "fantasyfurniture", "fantasy furniture", "fantasy's furniture")) return FF_COLOR;
        if(StringUtils.equalsAnyIgnoreCase(str, "fantasydice", "fantasy dice", "fantasy's dice")) return FD_COLOR;
        if(StringUtils.equalsAnyIgnoreCase(str, "infusedfoods", "infused foods")) return IF_COLOR;
        if(StringUtils.equalsAnyIgnoreCase(str, "itemresistance", "item resistance")) return IR_COLOR;

        return TextColor.parseColor(str);
    }
}
