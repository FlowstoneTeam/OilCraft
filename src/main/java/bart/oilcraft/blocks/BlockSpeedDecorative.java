package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.items.block.ItemBlockGeneric;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Bart on 14-11-2014.
 */
public class BlockSpeedDecorative extends Block implements OilCraftBlockRegistry.ISubBlocksBlock {

    public static final String[] iconPaths = new String[]{"oilystonebrick", "crackedoilystonebrick"};
    public IIcon[] icons;

    public BlockSpeedDecorative() {
        super(Material.iron);
        this.setBlockName("oilcraft.blockSpeedDecorative");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
        this.slipperiness = 1.00F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons = new IIcon[iconPaths.length];
        int i = 0;
        for (String path : iconPaths)
            this.icons[i++] = iconRegister.registerIcon(References.RESOURCESPREFIX + path);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= this.icons.length)
            return null;
        return icons[meta];
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        for (int i = 0; i < this.icons.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }


    @Override
    public int damageDropped(int meta) {
        return meta;
    }


    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockGeneric.class;
    }
}
