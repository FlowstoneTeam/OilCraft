package bart.oilcraft.items;


import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityLiquidizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Bart on 29-11-2014.
 */
public class ItemNote extends Item {

    //ToDo make first time kayla uses block extract here essence
    public ItemNote() {
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".notes");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }

    public String getName() {
        return "notes";
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setInteger("xCoord", 0);
        itemStack.stackTagCompound.setInteger("yCoord", 0);
        itemStack.stackTagCompound.setInteger("zCoord", 0);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (!par3World.isRemote && !(par3World.getTileEntity(par4, par5, par6) instanceof TileEntityLiquidizer)) {
            par1ItemStack.stackTagCompound = new NBTTagCompound();
            par1ItemStack.stackTagCompound.setInteger("xCoord", par4);
            par1ItemStack.stackTagCompound.setInteger("yCoord", par5);
            par1ItemStack.stackTagCompound.setInteger("zCoord", par6);

            //int xCoords = par1ItemStack.stackTagCompound.getInteger("xCoord");
            //int yCoords = par1ItemStack.stackTagCompound.getInteger("yCoord");
            //int zCoords = par1ItemStack.stackTagCompound.getInteger("zCoord");

            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add("bound to: ");
        if (itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("xCoord") && itemStack.stackTagCompound.hasKey("yCoord") && itemStack.stackTagCompound.hasKey("zCoord")) {
            int xCoords = itemStack.stackTagCompound.getInteger("xCoord");
            int yCoords = itemStack.stackTagCompound.getInteger("yCoord");
            int zCoords = itemStack.stackTagCompound.getInteger("zCoord");

            list.add("x:" + xCoords + ", y:" + yCoords + ", z:" + zCoords);
        }
    }


}
