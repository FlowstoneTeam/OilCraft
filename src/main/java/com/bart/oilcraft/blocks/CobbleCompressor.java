package com.bart.oilcraft.blocks;

import com.bart.oilcraft.OilCraftMain;
import com.bart.oilcraft.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class CobbleCompressor extends OilCraftBlock {
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public CobbleCompressor() {
        this.setBlockName(Strings.CobbleCompressorName);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeWood);
        ModBlocks.register(this);
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
            String name;
            switch (i) {
                case 0:
                    name = "0";
                    break;
                case 1:
                    name = "1";
                    break;
                case 2:
                    name = "2";
                    break;
                case 3:
                    name = "3";
                    break;
                case 4:
                    name = "4";
                    break;
                case 5:
                    name = "5";
                    break;
                default:
                    name = "0";
            }
            icons[i] = iconRegister.registerIcon(getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + name);
        }
    }
}