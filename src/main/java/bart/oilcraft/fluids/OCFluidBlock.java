package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.creativetab.OCCreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidBlock extends BlockFluidClassic {
    public OCFluidBlock(Fluid fluid, Material material, String name) {
        super(fluid, material);
        this.setTickRandomly(true);
        this.setUnlocalizedName("oilcraft." + name.toLowerCase());
        this.setCreativeTab(OCCreativeTabs.main);
        GameRegistry.registerBlock(this, name);
        OilCraftMain.proxy.registerFluidBlockRendering(this, name);
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos) {
        return !world.getBlockState(pos).getBlock().getMaterial().isLiquid() && super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos) {
        return !world.getBlockState(pos).getBlock().getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
    }
}
