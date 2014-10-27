package bart.oilcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block OilCompressor;
    public static Block CrudeOilOre;
    public static Block OilInfuser;
    public static Block BlockMachineFrame;
    public static Block OilGenerator;
    public static Block OilFurnace;
    public static Block ShaleOilOre;

    public static void init() {
        OilCompressor = new OilCompressor();
            GameRegistry.registerBlock(OilCompressor, "OilCompressor");
        CrudeOilOre = new CrudeOilOre();
            GameRegistry.registerBlock(CrudeOilOre, "CrudeOilOre");
        OilInfuser = new OilInfuser();
            GameRegistry.registerBlock(OilInfuser, "OilInfuser");
        BlockMachineFrame = new BlockMachineFrame();
            GameRegistry.registerBlock(BlockMachineFrame, "MachineFrame");
        OilGenerator = new OilGenerator();
            GameRegistry.registerBlock(OilGenerator, "OilGenerator");
        OilFurnace = new BlockOilFurnace();
            GameRegistry.registerBlock(OilFurnace, "OilFurnace");
        ShaleOilOre = new ShaleOilOre();
            GameRegistry.registerBlock(ShaleOilOre, "ShaleOilOre");
    }
}