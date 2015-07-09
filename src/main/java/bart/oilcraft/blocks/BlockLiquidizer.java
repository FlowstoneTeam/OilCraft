package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.items.ItemNote;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityLiquidizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
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
 * Created by Bart on 30-11-2014.
 */
public class BlockLiquidizer extends Block implements ITileEntityProvider {

    public IIcon top;
    public IIcon bottom;
    public IIcon front;
    public IIcon side;

    public BlockLiquidizer() {
        super(Material.iron);
        this.setBlockName("oilcraft.blockliquidizer");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLiquidizer();
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
        TileEntityLiquidizer tile = (TileEntityLiquidizer) world.getTileEntity(x, y, z);

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
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
        TileEntityLiquidizer tile = (TileEntityLiquidizer) access.getTileEntity(x, y, z);
        if (side == 0) {
            return this.bottom;
        } else if (side == 1) {
            return this.top;
        } else if (side != tile.facing) {
            return this.side;
        } else {
            return this.front;
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0) return this.bottom;
        else if (side == 1) return this.top;
        else if (side == 3) return this.front;
        else return this.side;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntity te = world.getTileEntity(x, y, z);
        ItemStack par1ItemStack = player.getHeldItem();
        if (te instanceof TileEntityLiquidizer && player.getHeldItem() == null) {
            ((TileEntityLiquidizer) te).Teleport(player);
            return true;
        } else if (te instanceof TileEntityLiquidizer && player.getHeldItem().getItem() instanceof ItemNote && par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("xCoord") && par1ItemStack.stackTagCompound.hasKey("yCoord") && par1ItemStack.stackTagCompound.hasKey("zCoord")) {
            world.markBlockForUpdate(x, y, z);
            ((TileEntityLiquidizer) te).xCoordTel = par1ItemStack.stackTagCompound.getInteger("xCoord");
            ((TileEntityLiquidizer) te).yCoordTel = par1ItemStack.stackTagCompound.getInteger("yCoord");
            ((TileEntityLiquidizer) te).zCoordTel = par1ItemStack.stackTagCompound.getInteger("zCoord");
            return true;
        }
        return false;
    }
}