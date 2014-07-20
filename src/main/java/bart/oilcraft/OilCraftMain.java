package bart.oilcraft;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.core.handler.CraftingHandler;
import bart.oilcraft.core.handler.GUIHandler;
import bart.oilcraft.core.proxy.CommonProxy;
import bart.oilcraft.creativetab.OilCraftTab;
import bart.oilcraft.fluids.BucketRegistry;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.References;
import bart.oilcraft.util.BucketHandler;
import bart.oilcraft.util.WorldGenerationHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION)
public class OilCraftMain {

    @Mod.Instance
    public static OilCraftMain instance;

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
        ModItems.Init();
        BucketRegistry.registerBucket();

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        GameRegistry.registerWorldGenerator(new WorldGenerationHandler(), 2);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        CraftingHandler.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
        proxy.registerTileEntities();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
    }
}