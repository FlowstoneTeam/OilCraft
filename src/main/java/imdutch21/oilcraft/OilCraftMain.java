package imdutch21.oilcraft;

import imdutch21.oilcraft.block.OCBlockRegistry;
import imdutch21.oilcraft.compat.CompatibilityRegistry;
import imdutch21.oilcraft.entity.OCEntityRegistry;
import imdutch21.oilcraft.fluids.OCFluidRegistry;
import imdutch21.oilcraft.item.OCItemRegistry;
import imdutch21.oilcraft.lib.ModInfo;
import imdutch21.oilcraft.network.OCPacketHandler;
import imdutch21.oilcraft.potion.OCPotionRegistry;
import imdutch21.oilcraft.proxy.CommonProxy;
import imdutch21.oilcraft.recipe.RecipeHandler;
import imdutch21.oilcraft.tileentity.OCTileEntityRegistry;
import imdutch21.oilcraft.util.ConfigHandler;
import imdutch21.oilcraft.world.WorldGenOilPool;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;


@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, guiFactory = ModInfo.GUI_FACTORY, dependencies = ModInfo.DEPENDENCIES)
public class OilCraftMain {

    @Instance(ModInfo.ID)
    public static OilCraftMain instance;

    @SidedProxy(clientSide = ModInfo.CLIENTPROXY_LOCATION, serverSide = ModInfo.COMMONPROXY_LOCATION)
    public static CommonProxy proxy;


    public static ArrayList<String> unlocalizedNames = new ArrayList<String>();

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Oilcraft PreInitialization");

        ConfigHandler.INSTANCE.loadConfig(event);
        //MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        OCFluidRegistry.init();
        OCBlockRegistry.init();
        OCItemRegistry.init();
        OCTileEntityRegistry.init();
        OCPotionRegistry.init();
        OCEntityRegistry.init();
        CompatibilityRegistry.init();

        GameRegistry.registerWorldGenerator(new WorldGenOilPool(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        RecipeHandler.init();
        proxy.initModels();
        proxy.initRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Oilcraft Initialization");
        OCPacketHandler.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
