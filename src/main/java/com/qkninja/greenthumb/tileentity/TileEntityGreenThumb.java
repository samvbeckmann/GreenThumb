package com.qkninja.greenthumb.tileentity;

import com.qkninja.greenthumb.reference.Names;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Generic {@link TileEntity} for the GreenThumb mod.
 * Implemented by all custom GreenThumb TileEntities.
 *
 * @author QKninja
 */
public class TileEntityGreenThumb extends TileEntity
{
    protected String customName;

    public TileEntityGreenThumb()
    {
        customName = "";
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getCustomName()
    {
        return customName;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }
    }

//    @Override // TODO: Decide if Network stuff is needed here.
//    public Packet getDescriptionPacket()
//    {
//        ByteBuf buf = Unpooled.buffer();
//        buf.writeInt(xCoord);
//        buf.writeInt(yCoord);
//        buf.writeInt(zCoord);
//        writeToPacket(buf);
//
//        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
//    }
//
//    public void writeToPacket(ByteBuf buf)
//    {
//
//    }
//
//    public void readFromPacket(ByteBuf buf)
//    {
//
//    }
}
