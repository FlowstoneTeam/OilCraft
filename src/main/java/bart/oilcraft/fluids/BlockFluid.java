package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluid extends BlockFluidClassic {
    public String name;
    public IIcon stillIcon;
    public IIcon flowingIcon;


    public BlockFluid(Fluid fluid, String name) {
        super(fluid, Material.water);
        this.name = name;
        this.setTickRandomly(true);
        this.setBlockName("oilcraft." + name.toLowerCase());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        GameRegistry.registerBlock(this, "oilcraft." + name);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        stillIcon = par1IconRegister.registerIcon(References.RESOURCESPREFIX + "fluids/" + name + "StillBlock");
        flowingIcon = par1IconRegister.registerIcon(References.RESOURCESPREFIX + "fluids/" + name + "FlowingBlock");
        try {
            Object field = OilCraftFluidRegistry.class.getDeclaredField(name).get(null);
            if (field instanceof Fluid) {
                ((Fluid) field).setIcons(stillIcon, flowingIcon);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z).getMaterial().isLiquid() || super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        return super.displaceIfPossible(world, x, y, z);
    }


}