package xyz.apex.minecraft.fantasyfurniture.common.entity;

import dev.architectury.networking.NetworkManager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.SeatBlock;

public final class Seat extends Entity
{
    public Seat(EntityType<?> entityType, Level level)
    {
        super(entityType, level);
    }

    public Seat(Level level, BlockPos pos)
    {
        this(FantasyFurniture.SEAT_ENTITY.get(), level);

        noPhysics = true;
    }

    @Override
    public void setPos(double x, double y, double z)
    {
        super.setPos(x, y, z);
        var bb = getBoundingBox();
        var diff = new Vec3(x, y, z).subtract(bb.getCenter());
        setBoundingBox(bb.move(diff));
    }

    @Override
    public void positionRider(Entity entity, MoveFunction moveFunction)
    {
        if(!hasPassenger(entity)) return;
        var d = getY() + getPassengersRidingOffset() + entity.getMyRidingOffset();
        moveFunction.accept(entity, getX(), d + getCustomEntitySeatOffset(entity), getZ());
    }

    @Override public void setDeltaMovement(Vec3 delta) {}

    @Override
    public void tick()
    {
        if(level.isClientSide) return;
        var blockPresent = level.getBlockState(blockPosition()).getBlock() instanceof SeatBlock;
        if(isVehicle() && blockPresent) return;
        discard();
    }

    @Override
    protected boolean canRide(Entity entity)
    {
        return FantasyFurniture.isEntityValidForSeat(entity);
    }

    @Override
    protected void removePassenger(Entity entity)
    {
        super.removePassenger(entity);
        if(entity instanceof TamableAnimal tamable) tamable.setInSittingPose(false);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity)
    {
        return super.getDismountLocationForPassenger(entity).add(0F, .5F, 0F);
    }

    @Override protected void defineSynchedData() {}
    @Override protected void readAdditionalSaveData(CompoundTag tag) {}
    @Override protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket()
    {
        return NetworkManager.createAddEntityPacket(this);
    }

    public static double getCustomEntitySeatOffset(Entity entity)
    {
        if(entity instanceof Slime) return .25F;
        else if(entity instanceof Parrot || entity instanceof Wolf) return 1F / 16F;
        else if(entity instanceof Skeleton || entity instanceof Creeper || entity instanceof Cat) return 1F / 8F;
        else if(entity instanceof Frog) return 1F / 8F + 1F / 64F;
        return 0F;
    }
}
