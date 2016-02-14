package bart.oilcraft.fluids;


import bart.oilcraft.recipe.OCMaterials;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Bart on 12/02/2016.
 */
public class OCFluidRegistry {
    public static Fluid oil;
    public static BlockFluidClassic oilBlock;

    public static void init(){
        oil = new OCFluid("oil");
        oilBlock = new OCFluidBlock(oil, OCMaterials.oil, "oil");
    }
}
