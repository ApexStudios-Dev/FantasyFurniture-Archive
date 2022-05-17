package xyz.apex.forge.fantasyfurniture.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import xyz.apex.forge.fantasyfurniture.block.base.ISeatBlock;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

import javax.annotation.Nullable;

public class SeatEntity extends Entity
{
	@Nullable private BlockPos source;

	// default constructor for registration, do not call manually
	@Deprecated
	public SeatEntity(EntityType<? extends SeatEntity> entityType, World level)
	{
		super(entityType, level);

		noPhysics = true;
	}

	// used for entity client factory, do not call manually
	@Deprecated
	public SeatEntity(World level)
	{
		this(Registrations.SEAT_ENTITY.asEntityType(), level);
	}

	@Override
	public void tick()
	{
		super.tick();

		if(source == null)
		{
			source = blockPosition();
			setPos(source.getX() + .5D, source.getY() + .5D, source.getZ() + .5D);
		}

		if(!level.isClientSide)
		{
			if(getPassengers().isEmpty() || level.isEmptyBlock(source))
			{
				BlockPos pos = blockPosition();

				remove(false);
				level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).getBlock());
			}
		}
	}

	@Override
	public double getPassengersRidingOffset()
	{
		return 0D;
	}

	@Override
	protected boolean canRide(Entity entity)
	{
		return true;
	}

	@Override
	protected void defineSynchedData()
	{
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT tagCompound)
	{
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT tagCompound)
	{
	}

	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static boolean create(World level, BlockPos pos, ISeatBlock seatBlock, PlayerEntity player)
	{
		if(level.isClientSide)
			return true;

		BlockState blockState = level.getBlockState(pos);
		pos = seatBlock.getSeatSitPos(blockState, pos);
		blockState = level.getBlockState(pos);

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		SeatEntity seat = Registrations.SEAT_ENTITY.create(level);

		if(seat != null)
		{
			double seatYOffset = seatBlock.getSeatYOffset(blockState);

			seat.source = pos;
			seat.setPos(x + .5D, y + seatYOffset, z + .5D);

			level.addFreshEntity(seat);
			player.startRiding(seat, false);

			return true;
		}

		return false;
	}
}
