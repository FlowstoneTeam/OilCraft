package bart.oilcraft;

import bart.oilcraft.blocks.OCBlockRegistry;
import bart.oilcraft.fluids.OCFluidRegistry;
import bart.oilcraft.lib.ModInfo;
import bart.oilcraft.potions.OCPotionRegistry;
import bart.oilcraft.proxy.CommonProxy;
import bart.oilcraft.recipes.RecipeHandler;
import bart.oilcraft.utils.ConfigHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.ArrayList;


@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUI_FACTORY, dependencies = ModInfo.DEPENDENCIES)
public class OilCraftMain {

    @Instance(ModInfo.ID)
    public static OilCraftMain instance;

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
        OCBlockRegistry.init();
        OCFluidRegistry.init();
        proxy.registerTileEntities();
        OCPotionRegistry.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        RecipeHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Oilcraft Initialization");
        proxy.initModels();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
