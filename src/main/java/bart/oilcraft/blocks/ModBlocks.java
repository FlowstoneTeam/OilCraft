package bart.oilcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import bart.oilcraft.blocks.CrudeOilOre;

public class ModBlocks {
    public static Block OilCompressor;
    public static Block CrudeOilOre;
    public static Block OilInfuser;
    public static void init() {
        OilCompressor = new OilCompressor();
            GameRegistry.registerBlock(OilCompressor, "OilCompressor");
        CrudeOilOre = new CrudeOilOre();
            GameRegistry.registerBlock(CrudeOilOre, "CrudeOilOre");
        OilInfuser = new OilInfuser();
            GameRegistry.registerBlock(OilInfuser, "OilInfuser");
    }
}