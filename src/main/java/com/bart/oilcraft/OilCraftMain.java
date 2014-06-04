package com.bart.oilcraft;

import com.bart.oilcraft.Fluids.ModFluids;
import com.bart.oilcraft.blocks.ModBlocks;
import com.bart.oilcraft.core.handler.CraftingHandler;
import com.bart.oilcraft.core.Proxy.CommonProxy;
import com.bart.oilcraft.creativetab.OilCraftTab;
import com.bart.oilcraft.lib.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION)

public class OilCraftMain {
    @SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
    public static CommonProxy proxy;

    private static CreativeTabs oilCraftTab = new OilCraftTab(CreativeTabs.getNextID(), References.MODID);

    public static CreativeTabs getCreativeTab() {
        return oilCraftTab;
    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModFluids.init();
        CraftingHandler.init();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }
}