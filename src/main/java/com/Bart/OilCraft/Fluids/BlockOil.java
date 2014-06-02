package com.Bart.OilCraft.Fluids;

import com.Bart.OilCraft.OilCraft;
import net.minecraftforge.fluids.Fluid;

public class BlockOil extends BaseFluid {

    public BlockOil(Fluid fluid) {
        super(fluid);
        this.setBlockName("Oil");
        ModFluids.registerFluid(this);


        this.setCreativeTab(OilCraft.OilTab);
    }

}
