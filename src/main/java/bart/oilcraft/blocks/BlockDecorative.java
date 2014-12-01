package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Random;

/**
 * Created by Bart on 29-11-2014.
 */
public class BlockDecorative extends OilCraftBlock {

    public IIcon[] icon = new IIcon[16];

    public BlockDecorative(){
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(References.RESOURCESPREFIX + "missingtexture");

        icon[0] = ir.registerIcon(References.RESOURCESPREFIX + "sturdystonebrick");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icon[meta];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        for (int i = 0; i < 1; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int quantity, Random rand, int meta) {
        switch (meta) {
            case 0: return new ItemStack(ModBlocks.speedDecro, 1, 0).getItem();
        }
        return null;
    }

    @Override
    public int damageDropped(int meta){
        return meta;
    }
}
