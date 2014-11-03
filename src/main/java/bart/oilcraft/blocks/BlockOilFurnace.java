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

    public IIcon top;
    public IIcon bottom;
    public IIcon front;
    public IIcon side;

    public BlockOilFurnace() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
    }

    @Override
    public String getName() {
        return ("blockoilfurnace");
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
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.front = iconRegister.registerIcon(References.RESOURCESPREFIX + "oilfurnace_front");
        this.top = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_top");
        this.bottom = iconRegister.registerIcon(References.RESOURCESPREFIX + "general_machine");
        this.side = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_side");
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

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side){
        TileEntityOilFurnace tile = (TileEntityOilFurnace) access.getTileEntity(x, y, z);
        if(side == 0 ){
            return this.bottom;
        } else if (side == 1) {
            return this.top;
        }
        else if(side != tile.facing){
            return this.side;
        }
        else{
            return this.front;
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 0)return this.bottom;
        else if(side == 1)return this.top;
        else if(side == 3)return this.front;
        else return this.side;
    }

}
