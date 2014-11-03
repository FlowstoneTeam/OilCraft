package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by bart on 18-10-2014.
 */
public class BlockOilFurnace extends OilCraftBlock implements ITileEntityProvider {

    public IIcon[] icons;

    public BlockOilFurnace() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
        icons = new IIcon[6];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side <= 5)
            return icons[side];
        else
            return icons[0];
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        for(int i = 0; i < icons.length; i++){

            if(i == 0 ){
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "general_machine");
            }

            if (i == 1) {
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_top");
            }
            else if(i == 2){
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + getName() + "_front");
            }
            else{
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_side");
            }
        }
    }





    @Override
    public String getName() {
        return ("BlockOilFurnace");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityOilFurnace();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
        if (entityPlayer.isSneaking()) return false;
        else
        {
            if (!world.isRemote)
            {
                entityPlayer.openGui(OilCraftMain.instance, 2, world, x, y, z);
            }
            return true;
        }
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityOilFurnace tile = (TileEntityOilFurnace) world.getTileEntity(x, y, z);

        if (facing == 0)
            tile.facing = 2;
        else if (facing == 1)
            tile.facing = 5;
        else if (facing == 2)
            tile.facing = 3;
        else if (facing == 3)
            tile.facing = 4;
    }


}
