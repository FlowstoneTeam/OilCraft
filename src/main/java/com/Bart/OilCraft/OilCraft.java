package com.Bart.OilCraft;

import com.Bart.OilCraft.Fluids.ModFluids;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


@Mod(modid = OilCraft.ModId, version = OilCraft.version)


public class OilCraft {
    public static final String ModId = "OilCraft";
    public static final String version = "Beta 1.0";

   //need help here
    @SidedProxy(clientSide = ModId + ".core.proxy.ClientProxy", serverSide = ModId + ".core.proxy.CommonProxy")
        public static CommonProxy proxy;

    public static CreativeTabs OilTab = new CreativeTabs("OilCraft") {
        public Item getTabIconItem() {
            return Items.cookie;
        }
    };

    public static Block CobbleCompressor;


    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {


        ModFluids.init();
        ModBlocks.init();
        CobbleCompressor = new CobbleCompressor(Material.iron).setBlockName("CobbleCompressor").setCreativeTab(OilTab);
        GameRegistry.registerBlock(CobbleCompressor, "CobbleCompressor");
        GameRegistry.addRecipe(new ItemStack(CobbleCompressor), new Object[]
                {
                        "CPC",
                        "I I",
                        "CCC",
                        'C', Blocks.cobblestone, 'P', Blocks.piston, 'I', Items.iron_ingot
                });
    }


    @EventHandler
    public void init(FMLInitializationEvent e) {
        System.out.println("lol");

    }

}
