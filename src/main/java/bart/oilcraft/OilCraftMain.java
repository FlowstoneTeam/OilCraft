package bart.oilcraft;

import bart.oilcraft.block.OCBlockRegistry;
import bart.oilcraft.entity.OCEntityRegistry;
import bart.oilcraft.fluids.OCFluidRegistry;
import bart.oilcraft.item.OCItemRegistry;
import bart.oilcraft.lib.ModInfo;
import bart.oilcraft.potion.OCPotionRegistry;
import bart.oilcraft.proxy.CommonProxy;
import bart.oilcraft.recipe.RecipeHandler;
import bart.oilcraft.util.BucketHandler;
import bart.oilcraft.util.ConfigHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.util.ArrayList;


@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUI_FACTORY, dependencies = ModInfo.DEPENDENCIES)
public class OilCraftMain {

    @Instance(ModInfo.ID)
    public static OilCraftMain instance;
    public static SimpleNetworkWrapper networkWrapper;

    @SidedProxy(clientSide = ModInfo.CLIENTPROXY_LOCATION, serverSide = ModInfo.COMMONPROXY_LOCATION)
    public static CommonProxy proxy;
    public static boolean thaumcraftLoaded = false;
    public static boolean thermalExpansionLoaded = false;

    public static ArrayList<String> unlocalizedNames = new ArrayList<String>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Oilcraft PreInitialization");

        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
        thermalExpansionLoaded = Loader.isModLoaded("ThermalExpansion");
        ConfigHandler.INSTANCE.loadConfig(event);
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        OCBlockRegistry.init();
        OCFluidRegistry.init();
        OCItemRegistry.init();
        proxy.registerTileEntities();
        OCPotionRegistry.init();
        OCEntityRegistry.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        RecipeHandler.init();
        proxy.initModels();
        proxy.initRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Oilcraft Initialization");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
