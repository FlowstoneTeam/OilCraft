package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import bart.oilcraft.util.ConnectedTextureHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Bart on 26-9-2014.
 */
public class BlockMachineFrame extends Block {


    protected IIcon[] icons = new IIcon[16];


    public BlockMachineFrame() {
        super(Material.iron);
        this.setBlockName("oilcraft.blockmachineframe");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
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
        return !(b1 == this || b1 == OilCraftBlockRegistry.machineFrame) && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        return ConnectedTextureHelper.getConnectedBlockTexture(iBlockAccess, x, y, z, side, icons, this);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_0");
        icons[0] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_0");
        icons[1] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_1");
        icons[2] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_2");
        icons[3] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_3");
        icons[4] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_4");
        icons[5] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_5");
        icons[6] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_6");
        icons[7] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_7");
        icons[8] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_8");
        icons[9] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_9");
        icons[10] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_10");
        icons[11] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_11");
        icons[12] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_12");
        icons[13] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_13");
        icons[14] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_14");
        icons[15] = iconRegister.registerIcon(References.MODID + ":machineframe/General_machine_15");
    }
}


