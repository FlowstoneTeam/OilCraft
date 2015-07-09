package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.tileentities.TileEntityOilGenerator;
import bart.oilcraft.tileentities.TileEntityOilRefinery;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Bart on 9-7-2015.
 */
public class BlockOilRefinery extends IOilContainer implements ITileEntityProvider {
    public BlockOilRefinery() {
        super(Material.iron);
        this.setBlockName("oilcraft.oilrefinery");
        this.guiID = 1;
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityOilRefinery();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        System.out.println("something");
        if (!world.isRemote) {
            entityPlayer.openGui(OilCraftMain.instance, guiID, world, x, y, z);
            return true;
        }
        return false;
    }
}
