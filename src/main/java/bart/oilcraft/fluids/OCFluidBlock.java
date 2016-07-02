package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
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
    public OCFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);
    }
}
