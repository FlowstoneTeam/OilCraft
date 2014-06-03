package com.bart.oilcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;


public class ModBlocks {
    public static Block basicTest;
    public static Block cobbleCompressor;

    public static void init() {
        basicTest = new BasicTest();
        cobbleCompressor = new CobbleCompressor();
    }


    public static void register(OilCraftBlock block) {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName(block.getUnlocalizedName()));
    }
}