package com.bart.oilcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;


public class ModBlocks {

    public static Block OilCompressor;

    public static void init() {

        OilCompressor = new OilCompressor();
    }


    public static void register(OilCraftBlock block) {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName(block.getUnlocalizedName()));
    }
}