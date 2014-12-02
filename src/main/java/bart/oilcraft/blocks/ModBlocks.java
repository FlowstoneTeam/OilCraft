package bart.oilcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block OilCompressor;
    public static Block CrudeOilOre;
    public static Block MachineFrame;
    public static Block OilGenerator;
    public static Block OilFurnace;
    public static Block ShaleOilOre;
    public static Block OilLayer;
    public static Block SummonTable;
    public static Block speedDecro;
    public static Block Decorative;
    public static Block OilyStairs;
    public static Block liquidizer;
    public static Block bodyReconstructer;

    public static void init() {
        OilCompressor = new OilCompressor();
            GameRegistry.registerBlock(OilCompressor, "OilCompressor");
        CrudeOilOre = new CrudeOilOre();
            GameRegistry.registerBlock(CrudeOilOre, "CrudeOilOre");
        MachineFrame = new BlockMachineFrame();
            GameRegistry.registerBlock(MachineFrame, "MachineFrame");
        OilGenerator = new OilGenerator();
            GameRegistry.registerBlock(OilGenerator, "OilGenerator");
        OilFurnace = new BlockOilFurnace();
            GameRegistry.registerBlock(OilFurnace, "OilFurnace");
        ShaleOilOre = new ShaleOilOre();
            GameRegistry.registerBlock(ShaleOilOre, "ShaleOilOre");
        OilLayer = new BlockOilLayer();
            GameRegistry.registerBlock(OilLayer, "OilLayer");
        SummonTable = new BlockSummonTable();
            GameRegistry.registerBlock(SummonTable, "SummonTable");
        speedDecro = new BlockSpeedDecorative().setBlockName("speeddecro");
            GameRegistry.registerBlock(speedDecro, BlockSpeedDecorativeMeta.class, speedDecro.getUnlocalizedName());
        Decorative = new BlockDecorative().setBlockName("Decorative");
            GameRegistry.registerBlock(Decorative, BlockDecorativeMeta.class, Decorative.getUnlocalizedName());
        OilyStairs = new OilCraftStairs(speedDecro, 0);
            GameRegistry.registerBlock(OilyStairs, "OilyStairs");
        liquidizer = new BlockLiquidizer();
            GameRegistry.registerBlock(liquidizer, "liquidizer");
        bodyReconstructer = new BlockBodyReconstructor();
            GameRegistry.registerBlock(bodyReconstructer, "bodyReconstructer");
    }
}