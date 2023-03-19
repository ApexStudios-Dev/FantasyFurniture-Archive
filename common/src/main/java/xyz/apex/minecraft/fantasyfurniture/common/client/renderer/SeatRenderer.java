package xyz.apex.minecraft.fantasyfurniture.common.client.renderer;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import xyz.apex.minecraft.fantasyfurniture.common.entity.Seat;

public final class SeatRenderer extends EntityRenderer<Seat>
{
    public SeatRenderer(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }

    @Override
    public boolean shouldRender(Seat seat, Frustum frustum, double x, double y, double z)
    {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(Seat seat)
    {
        return MissingTextureAtlasSprite.getLocation();
    }
}
