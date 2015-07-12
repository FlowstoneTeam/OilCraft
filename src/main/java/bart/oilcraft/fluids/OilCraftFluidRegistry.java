package bart.oilcraft.fluids;

import net.minecraftforge.fluids.Fluid;

import static bart.oilcraft.util.OreDictionaryHelper.*;

public class OilCraftFluidRegistry {
    public static Fluid oil;
    public static BlockFluid oilBlock;
    public static Fluid residue;
    public static BlockFluid residueBlock;
    public static Fluid diesel;
    public static BlockFluid dieselBlock;
    public static Fluid fuel;
    public static BlockFluid fuelBlock;
    public static Fluid kerosene;
    public static BlockFluid keroseneBlock;
    public static Fluid petrol;
    public static BlockFluid petrolBlock;
    public static Fluid gas;
    public static BlockFluid gasBlock;


    public static void init() {
        if (OIL != null) {
            oil = new OilCraftFluids(OIL_STRING);
            oilBlock = new BlockFluid(oil, OIL_STRING);
        }
        if (RESIDUE != null) {
            residue = new OilCraftFluids(RESIDUE_STRING);
            residueBlock = new BlockFluid(residue, RESIDUE_STRING);
        }
        if (FUEL != null) {
            fuel = new OilCraftFluids(FUEL_STRING);
            fuelBlock = new BlockFluid(fuel, FUEL_STRING);
        }
        if (DIESEL != null) {
            diesel = new OilCraftFluids(DIESEL_STRING);
            dieselBlock = new BlockFluid(diesel, DIESEL_STRING);
        }
        if (KEROSENE != null) {
            kerosene = new OilCraftFluids(KEROSENE_STRING);
            keroseneBlock = new BlockFluid(kerosene, KEROSENE_STRING);
        }
        if (PETROL != null) {
            petrol = new OilCraftFluids(PETROL_STRING);
            petrolBlock = new BlockFluid(petrol, PETROL_STRING);
        }
        if (GAS != null) {
            gas = new OilCraftFluids(GAS_STRING).setGaseous(true);
            gasBlock = new BlockFluid(gas, GAS_STRING);
        }
    }

}

