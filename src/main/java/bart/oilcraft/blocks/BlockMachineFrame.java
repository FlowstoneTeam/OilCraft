package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import bart.oilcraft.util.ConnectedTextureHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Bart on 26-9-2014.
 */
public class BlockMachineFrame extends OilCraftBlock {


    protected IIcon[] icons = new IIcon[16];


    public BlockMachineFrame() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(4f);
    }

    @Override
    public String getName() {
        return "BlockMachineFrame";
    }


    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        Block b1 = par1IBlockAccess.getBlock(par2, par3, par4);
        if (b1 == this || b1 == ModBlocks.MachineFrame) {
            return false;
        }
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 15 ? icons[0] : ConnectedTextureHelper.getConnectedBlockTexture(par1IBlockAccess, par2, par3, par4, par5, icons, this);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon(References.MODID + ":machineFrame/General_machine_0");
        icons[0] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/General_machine_0");
        icons[1] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_1");
        icons[2] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_2");
        icons[3] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_3");
        icons[4] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_4");
        icons[5] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_5");
        icons[6] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_6");
        icons[7] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_7");
        icons[8] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_8");
        icons[9] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_9");
        icons[10] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_10");
        icons[11] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_11");
        icons[12] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_12");
        icons[13] = par1IconRegister.registerIcon(References.MODID+ ":machinesFrame/machine_Side_13");
        icons[14] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_14");
        icons[15] = par1IconRegister.registerIcon(References.MODID + ":machinesFrame/machine_Side_15");
    }
}


