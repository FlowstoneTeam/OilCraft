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
    public static final String SHALE_OIL_ORE_STRING = "oreShaleOil";
    public static final ArrayList<ItemStack> SHALE_OIL_ORE = getOres(SHALE_OIL_ORE_STRING);

    //items
    public static final String OIL_BUCKET_STRING = "bucketOil";
    public static final ArrayList<ItemStack> OIL_BUCKET = getOres(OIL_BUCKET_STRING);

    //fluids
    public static final String OIL_STRING = "oil";
    public static final Fluid OIL = getFluid(OIL_STRING);
    public static final String RESIDUE_STRING = "residue";
    public static final Fluid RESIDUE = getFluid(RESIDUE_STRING);
    public static final String FUEL_STRING = "fuel";
    public static final Fluid FUEL = getFluid(FUEL_STRING);
    public static final String DIESEL_STRING = "diesel";
    public static final Fluid DIESEL = getFluid(DIESEL_STRING);
    public static final String KEROSENE_STRING = "kerosene";
    public static final Fluid KEROSENE = getFluid(KEROSENE_STRING);
    public static final String PETROL_STRING = "petrol";
    public static final Fluid PETROL = getFluid(PETROL_STRING);
    public static final String GAS_STRING = "gas";
    public static final Fluid GAS = getFluid(GAS_STRING);

    public static Fluid getFluid(String string) {
        return FluidRegistry.getFluid(string);
    }

    public static ArrayList<ItemStack> getOres(String string) {
        return OreDictionary.getOres(string);
    }
}
