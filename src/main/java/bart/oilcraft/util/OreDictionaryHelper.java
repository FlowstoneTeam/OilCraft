package bart.oilcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Created by Bart on 12-7-2015.
 */
public class OreDictionaryHelper {
    //blocks
    public static final String SHALE_OIL_ORE = "oreShaleOil";

    //items
    public static final String OIL_BUCKET = "bucketOil";

    //fluids
    public static final String OIL = "oil";
    public static final String RESIDUE = "residue";
    public static final String FUEL = "fuel";
    public static final String DIESEL = "diesel";
    public static final String KEROSENE = "kerosene";
    public static final String PETROL = "petrol";
    public static final String GAS = "gas";

    public static Fluid getFluid(String string) {
        return FluidRegistry.getFluid(string);
    }

    public static ArrayList<ItemStack> getOres(String string) {
        return OreDictionary.getOres(string);
    }
}
