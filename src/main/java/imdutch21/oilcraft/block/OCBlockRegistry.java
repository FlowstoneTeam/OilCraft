package imdutch21.oilcraft.block;

import imdutch21.oilcraft.fluids.OCFluidBlock;
import imdutch21.oilcraft.fluids.OCFluidRegistry;
import imdutch21.oilcraft.lib.ModInfo;
import imdutch21.oilcraft.recipe.OCMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class OCBlockRegistry {
    public static final List<Block> BLOCKS = new LinkedList<Block>();

    public static OCBlock OIL_COMPRESSOR = new BlockOilCompressor();
    public static OCBlock OIL_GENERATOR = new BlockOilGenerator();
    public static OCBlock OIL_FURNACE = new BlockOilFurnace();
    public static OCBlock MACHINE_FRAME = new OCBlock(Material.IRON, MapColor.GRAY).setSoundType(SoundType.METAL);
    public static BlockFluidClassic OIL = new OCFluidBlock(OCFluidRegistry.OIL, OCMaterials.WATER);
    public static BlockFluidClassic FUEL = new OCFluidBlock(OCFluidRegistry.FUEL, OCMaterials.WATER);

    public static void init() {
        registerBlocks();
    }

    private static void registerBlocks() {
        try {
            for (Field field : OCBlockRegistry.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    Block block = (Block) obj;
                    String name = field.getName().toLowerCase(Locale.ENGLISH);
                    registerBlock(block, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerBlock(Block block, String name) {
        BLOCKS.add(block);
        GameRegistry.register(block.setRegistryName(ModInfo.ID, name).setUnlocalizedName(ModInfo.NAME_PREFIX + name));
        ItemBlock item;
        if (block instanceof IHasCustomItem)
            item = ((IHasCustomItem) block).getItemBlock();
        else
            item = new ItemBlock(block);

        GameRegistry.register((ItemBlock) item.setRegistryName(ModInfo.ID, name).setUnlocalizedName(ModInfo.NAME_PREFIX + name));
    }


    public interface IHasCustomItem {
        ItemBlock getItemBlock();
    }
}
