package bart.oilcraft.fluids;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;

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
        oil = new OilCraftFluids("oil");
        oilBlock = new BlockFluid(oil, "oil");
        oil.setFlowingIcon(oilBlock.flowingIcon).setStillIcon(oilBlock.stillIcon);

        residue = new OilCraftFluids("residue");
        residueBlock = new BlockFluid(residue, "residue");
        residue.setFlowingIcon(residueBlock.flowingIcon).setStillIcon(residueBlock.stillIcon);

        diesel = new OilCraftFluids("diesel");
        dieselBlock = new BlockFluid(diesel, "diesel");
        diesel.setFlowingIcon(dieselBlock.flowingIcon).setStillIcon(dieselBlock.stillIcon);

        fuel = new OilCraftFluids("fuel");
        fuelBlock = new BlockFluid(fuel, "fuel");
        fuel.setFlowingIcon(fuelBlock.flowingIcon).setStillIcon(fuelBlock.stillIcon);

        kerosene = new OilCraftFluids("kerosene");
        keroseneBlock = new BlockFluid(kerosene, "kerosene");
        kerosene.setFlowingIcon(keroseneBlock.flowingIcon).setStillIcon(keroseneBlock.stillIcon);

        petrol = new OilCraftFluids("petrol");
        petrolBlock = new BlockFluid(petrol, "petrol");
        petrol.setFlowingIcon(petrolBlock.flowingIcon).setStillIcon(petrolBlock.stillIcon);

        gas = new OilCraftFluids("gas").setGaseous(true);
        gasBlock = new BlockFluid(gas, "gas");
        gas.setFlowingIcon(gasBlock.flowingIcon).setStillIcon(gasBlock.stillIcon);
    }
}

