package bart.oilcraft.blocks;

import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;


public abstract class OilCraftBlock extends Block {

    public OilCraftBlock() {
        super(Material.rock);
    }
    public OilCraftBlock(Material material) {
        super(material);
    }

    public abstract String getName();

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + getName());
    }
}