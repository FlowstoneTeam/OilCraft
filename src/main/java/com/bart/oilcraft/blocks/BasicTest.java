package com.bart.oilcraft.blocks;

import com.bart.oilcraft.OilCraftMain;
import com.bart.oilcraft.lib.Strings;
import net.minecraft.block.Block;


public class BasicTest extends OilCraftBlock {
    public BasicTest() {
        this.setBlockName(Strings.BasicTestName);
        this.setHardness(1f);
        this.setResistance(3f);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeWood);
        ModBlocks.register(this);
    }
}