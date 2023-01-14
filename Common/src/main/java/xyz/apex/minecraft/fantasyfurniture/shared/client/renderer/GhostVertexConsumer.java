package xyz.apex.minecraft.fantasyfurniture.shared.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * Thanks <a href="https://github.com/XFactHD">XFactHD</a> | <a href="https://github.com/XFactHD/FramedBlocks/blob/48188e56c2f80fe9024949c0deb822b999548bbd/src/main/java/xfacthd/framedblocks/client/util/GhostVertexConsumer.java">Original Sources</a>
 * <p>
 * Modification to default {@link VertexConsumer} which overrides alpha to allow semi-transparent rendering
 *
 * @param delegate original {@link VertexConsumer} to render with transparency
 * @param alpha transparency amount to render with (0-255)
 * @author <a href="https://github.com/XFactHD">XFactHD</a>
 */
public record GhostVertexConsumer(VertexConsumer delegate, int alpha) implements VertexConsumer
{
    @Override
    public VertexConsumer vertex(double x, double y, double z)
    {
        return delegate.vertex(x, y, z);
    }

    @Override
    public VertexConsumer color(int red, int green, int blue, int alpha)
    {
        return delegate.color(red, green, blue, (alpha * this.alpha) / 0xFF);
    }

    @Override
    public VertexConsumer uv(float u, float v)
    {
        return delegate.uv(u, v);
    }

    @Override
    public VertexConsumer overlayCoords(int u, int v)
    {
        return delegate.overlayCoords(u, v);
    }

    @Override
    public VertexConsumer uv2(int u, int v)
    {
        return delegate.uv2(u, v);
    }

    @Override
    public VertexConsumer normal(float x, float y, float z)
    {
        return delegate.normal(x, y, z);
    }

    @Override
    public void endVertex()
    {
        delegate.endVertex();
    }

    @Override
    public void defaultColor(int defaultR, int defaultG, int defaultB, int defaultA)
    {
        delegate.defaultColor(defaultR, defaultG, defaultB, defaultA);
    }

    @Override
    public void unsetDefaultColor()
    {
        delegate.unsetDefaultColor();
    }
}
