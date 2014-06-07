package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import net.minecraftforge.fluids.Fluid;

public class BlockOil extends BaseFluid {
    public BlockOil(Fluid fluid) {
        super(fluid);
        this.setBlockName("Oil");
        ModFluids.registerFluid(this);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }
}