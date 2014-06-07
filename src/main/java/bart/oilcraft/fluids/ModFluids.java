package bart.oilcraft.fluids;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
    public static Fluid Oil;
    public static Block OilBlock;

    public static void init() {
        Oil = new OilCraftFluids("Oil");
        FluidRegistry.registerFluid(Oil);
        OilBlock = new BlockOil(Oil);
    }

    public static void registerFluid(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }
}
