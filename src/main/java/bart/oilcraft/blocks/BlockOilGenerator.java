package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityOilGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Bart on 11-10-2014.
 */
public class BlockOilGenerator extends IOilContainer implements ITileEntityProvider {

    public IIcon top;
    public IIcon bottom;
    public IIcon front;
    public IIcon side;

    public BlockOilGenerator() {
        super(Material.iron);
        this.setBlockName("oilcraft.oilgenerator");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityOilGenerator();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.front = iconRegister.registerIcon(References.RESOURCESPREFIX + "oilgenerator_front");
        this.top = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_top");
        this.bottom = iconRegister.registerIcon(References.RESOURCESPREFIX + "general_machine");
        this.side = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_side");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityOilGenerator tile = (TileEntityOilGenerator) world.getTileEntity(x, y, z);

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
        TileEntityOilGenerator tile = (TileEntityOilGenerator) access.getTileEntity(x, y, z);
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
}
