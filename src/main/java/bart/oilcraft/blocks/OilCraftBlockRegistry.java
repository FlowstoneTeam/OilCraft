package bart.oilcraft.blocks;

import bart.oilcraft.items.ItemOredict;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class OilCraftBlockRegistry {
    public static final List<Block> BLOCKS = new LinkedList<Block>();

    public static Block oilCompressor = new BlockOilCompressor();
    public static Block machineFrame = new BlockMachineFrame();
    public static Block oilGenerator = new BlockOilGenerator();
    public static Block oilRefinery = new BlockOilRefinery();
    public static Block oilFurnace = new BlockOilFurnace();
    public static Block shaleOilOre = new BlockShaleOilOre();
    public static Block oilLayer = new BlockOilLayer();
    public static Block summonTable = new BlockSummonTable();
    public static Block speedDecro = new BlockSpeedDecorative();
    public static Block decorative = new BlockDecorative();
    public static Block oilyStairs = new BlockOilCraftStairs(speedDecro, "oilyStoneBrick", 0);
    public static Block crackedOilyStairs = new BlockOilCraftStairs(speedDecro, "crackedOilyStairs", 1);
    public static Block liquidizer = new BlockLiquidizer();
    public static Block bodyReconstructer = new BlockBodyReconstructor();

    public static void init() {
        registerBlocks();
    }

    private static void registerBlocks() {
        try {
            for (Field f : OilCraftBlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block)
                    registerOredictBlock((Block) obj);
                else if (obj instanceof Block[])
                    for (Block block : (Block[]) obj)
                        registerOredictBlock(block);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerOredictBlock(Block block) {
        if (block instanceof ItemOredict) {
            if (OreDictionary.getOres(((ItemOredict) block).getOreDictName()).size() > 0)
                return;
            registerBlock(block);
            OreDictionary.registerOre(((ItemOredict) block).getOreDictName(), block);
        } else {
            registerBlock(block);
        }
    }

    private static void registerBlock(Block block) {
        BLOCKS.add(block);
        String name = block.getUnlocalizedName();
        String[] strings = name.split("\\.");

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
        else
            GameRegistry.registerBlock(block, strings[strings.length - 1]);
    }

    public interface ISubBlocksBlock {
        Class<? extends ItemBlock> getItemBlockClass();
    }


}
