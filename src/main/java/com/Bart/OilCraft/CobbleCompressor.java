package com.Bart.OilCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class CobbleCompressor extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon front, bottom, back;

    protected CobbleCompressor(Material material) {
        super(material);
        // TODO Auto-generated constructor stub
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_) {
        return side == 1 ? this.top : (side == 2 ? this.front : side == 0 ? this.bottom : side == 3 ? this.back : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(OilCraft.ModId + ":" + "CobbleCompressor_side1");
        this.top = p_149651_1_.registerIcon(OilCraft.ModId + ":" + "CobbleCompressor_top");
        this.front = p_149651_1_.registerIcon(OilCraft.ModId + ":" + "CobbleCompressor_front");
        this.bottom = p_149651_1_.registerIcon(OilCraft.ModId + ":" + "CobbleCompressor_bottom");
        this.back = p_149651_1_.registerIcon(OilCraft.ModId + ":" + "CobbleCompressor_back");
    }

    //TODO make oil
    //public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer Player, int side, float hitX, float hiYy, float hitZ){
    //if(!world.isRemote){
    //	FMLNetworkHandler.openGui(Player, OilCraft.instance, modGuiId, world, x, y, z);
    //System.out.print("lol");
    //}
    //return false;
    //}

}
