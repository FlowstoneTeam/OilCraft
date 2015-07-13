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
        oil = new OilCraftFluids(OIL);
        oilBlock = new BlockFluid(oil, OIL);
        residue = new OilCraftFluids(RESIDUE);
        residueBlock = new BlockFluid(residue, RESIDUE);
        fuel = new OilCraftFluids(FUEL);
        fuelBlock = new BlockFluid(fuel, FUEL);
        diesel = new OilCraftFluids(DIESEL);
        dieselBlock = new BlockFluid(diesel, DIESEL);
        kerosene = new OilCraftFluids(KEROSENE);
        keroseneBlock = new BlockFluid(kerosene, KEROSENE);
        petrol = new OilCraftFluids(PETROL);
        petrolBlock = new BlockFluid(petrol, PETROL);
        gas = new OilCraftFluids(GAS).setGaseous(true);
        gasBlock = new BlockFluid(gas, GAS);

    }

}

