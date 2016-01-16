package com.qkninja.greenthumb.block;

import com.qkninja.greenthumb.creativetab.CreativeTabGreenThumb;
import com.qkninja.greenthumb.reference.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Generic block in the Green Thumb mod.
 * implemented by all other Green Thumb blocks.
 *
 * @author QKninja
 */
public class BlockGreenThumb extends Block
{
    public BlockGreenThumb(Material material)
    {
        super(material);
        setCreativeTab(CreativeTabGreenThumb.GREENTHUMB_TAB);
        setStepSound(soundTypePiston);
    }

    public BlockGreenThumb()
    {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, unwrapName(super.getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public String getUnwrappedUnlocalizedName()
    {
        return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1);
    }



    private static String unwrapName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        dropInventory(world, pos);
        super.breakBlock(world, pos, state);
    }

    protected void dropInventory(World world, BlockPos pos) // TODO: Review and clean up.
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (!(tileEntity instanceof IInventory))
        {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0)
            {
                Random rand = new Random();
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ, itemStack.copy());
                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
