package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
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
        this.setUnlocalizedName("oilcraft." + name.toLowerCase());
        GameRegistry.registerBlock(this, name);
        OilCraftMain.proxy.registerFluidBlockRendering(this, name);
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos) {
        return !world.getBlockState(pos).getBlock().getMaterial(world.getBlockState(pos)).isLiquid() && super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos) {
        return !world.getBlockState(pos).getBlock().getMaterial(world.getBlockState(pos)).isLiquid() && super.displaceIfPossible(world, pos);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return NULL_AABB;
    }
}
