package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntitySummonTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Bart on 12-11-2014.
 */
public class BlockSummonTable extends Block implements ITileEntityProvider {

    public IIcon[] icons;

    public BlockSummonTable() {
        super(Material.rock);
        this.setBlockName("oilcraft.summontable");

        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(923098741233F);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
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
        for (int i = 0; i < icons.length; i++) {
            if (i == 0) {
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "summontable_bottom");
            } else if (i == 1) {
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "summontable_top");
            } else icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "summontable_side");
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return (new TileEntitySummonTable());
    }


}
