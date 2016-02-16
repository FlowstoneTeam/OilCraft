package bart.oilcraft.block;

import bart.oilcraft.OilCraftMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class OCBlockRegistry {
    public static final List<Block> BLOCKS = new LinkedList<>();

    public static OCBlock oilCompressor = new BlockOilCompressor();
    public static OCBlock oilGenerator = new BlockOilGenerator();
    public static OCBlock oilFurnace = new BlockOilFurnace();
    public static OCBlock machineFrame = new OCBlock(Material.iron, MapColor.grayColor, "machineFrame").setStepSound(Block.soundTypeMetal);

    public static void init() {
        registerBlocks();
    }

    private static void registerBlocks() {
        try {
            for (Field f : OCBlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block)
                    registerBlock((Block) obj);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerBlock(Block block) {
        BLOCKS.add(block);
        String name = block.getUnlocalizedName();
        String[] strings = name.split("\\.");
        GameRegistry.registerBlock(block, strings[strings.length - 1]);

        if (!StatCollector.canTranslate(block.getUnlocalizedName() + ".name")) {
            OilCraftMain.unlocalizedNames.add(block.getUnlocalizedName() + ".name");
        }
    }
}
