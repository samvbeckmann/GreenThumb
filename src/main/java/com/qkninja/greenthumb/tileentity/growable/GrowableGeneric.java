package com.qkninja.greenthumb.tileentity.growable;

import com.qkninja.greenthumb.reference.Names;
import com.qkninja.greenthumb.tileentity.TileEntityGreenThumb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

/**
 * Abstract Growable to simplify implementations.
 * Inventory methods and basic Growable interactions handled here.
 *
 * @author QKninja
 */
public abstract class GrowableGeneric extends TileEntityGreenThumb implements GreenThumbGrowable, ITickable
{
    /**
     * Current contents of the Growable's inventory. These are harvestable.
     */
    private ItemStack inventory;

    /**
     * This is added to the inventory every time a growth cycle is complete.
     */
    private ItemStack yield;

    /**
     * Number of ticks required for growable to produce yield.
     */
    private int cycleLength;

    /**
     * Ticks elapsed since last yield.
     */
    private int currentCycle;

    public GrowableGeneric(ItemStack yield, int cycleLength)
    {
        super();
        setYield(yield);
        setCycleLength(cycleLength);
        currentCycle = 0;
    }

    @Override
    public ItemStack getYield()
    {
        return yield;
    }

    public int getCycleLength()
    {
        return cycleLength;
    }

    protected void setYield(ItemStack newYield)
    {
        this.yield = newYield;
    }

    protected void setCycleLength(int cycleLength)
    {
        this.cycleLength = cycleLength;
    }


    @Override
    public void update()
    {
        if (!getWorld().isRemote) // Logic is all server-side
        {
            if (++currentCycle >= cycleLength) // attempts to produce yield
            {
                if (canGrow() && getStackInSlot(0) == null)
                {
                    setInventorySlotContents(0, new ItemStack(yield.getItem(), yield.stackSize));
                    markDirty();
                    getWorld().markBlockForUpdate(getPos());
                }
                currentCycle = 0;
            }
        }
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventory;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.inventory != null)
        {
            if (inventory.stackSize <= count)
            {
                ItemStack itemStack = inventory;
                inventory = null;
                return itemStack;
            } else
                return inventory.splitStack(count);
        } else
            return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = inventory;
        inventory = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inventory = stack;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(getPos()) == this &&
                player.getDistanceSq(getPos().add(0.5D, 0.5D, 0.5D)) <= 64.0D;    }

    @Override
    public void openInventory(EntityPlayer player)
    {
        /* NOOP */
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        /* NOOP*/
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
        /* NOOP */
    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        inventory = null;
    }

    @Override
    public String getName()
    {
        return hasCustomName() ? getCustomName() : "container.plant";
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return new ChatComponentText("If you see this, there's a bug in GreenThumb. Let me know about it."); // yeah...
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncableData = writeSyncableData(new NBTTagCompound());
        return new S35PacketUpdateTileEntity(getPos(), 1, syncableData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        this.readSyncableData(packet.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if (compound.hasKey(Names.NBT.YIELD))
            setYield(ItemStack.loadItemStackFromNBT(compound.getCompoundTag(Names.NBT.YIELD)));
        else
            setYield(null);

        setCycleLength(compound.getInteger(Names.NBT.CYCLE_LENGTH));
        currentCycle = compound.getInteger(Names.NBT.CYCLE_CURRENT);

        readSyncableData(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (yield != null)
        {
            NBTTagCompound yieldTag = new NBTTagCompound();
            yield.writeToNBT(yieldTag);
            compound.setTag(Names.NBT.YIELD, yieldTag);
        }

        compound.setInteger(Names.NBT.CYCLE_LENGTH, cycleLength);
        compound.setInteger(Names.NBT.CYCLE_CURRENT, currentCycle);

        writeSyncableData(compound);
    }

    private NBTTagCompound writeSyncableData(NBTTagCompound tag)
    {
        if (inventory != null)
        {
            NBTTagCompound inventoryTag = new NBTTagCompound();
            inventory.writeToNBT(inventoryTag);
            tag.setTag(Names.NBT.INVENTORY, inventoryTag);
        }

        return tag;
    }

    private void readSyncableData(NBTTagCompound tag)
    {
        NBTTagCompound coreCompound = (NBTTagCompound) tag.getTag(Names.NBT.INVENTORY);
        if (coreCompound != null)
            inventory = ItemStack.loadItemStackFromNBT(coreCompound);
        else
            inventory = null;
    }
}
