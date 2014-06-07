package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.fluids.Fluid;

public class BlockOil extends BaseFluid {
    public BlockOil(Fluid fluid) {
        super(fluid);
        this.setBlockName("Oil");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        GameRegistry.registerBlock(this, getName());
    }

    @Override
    public String getName(){
        return "BlockOil";
    }
}