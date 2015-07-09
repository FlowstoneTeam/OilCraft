package bart.oilcraft;

import bart.oilcraft.blocks.OilCraftBlockRegistry;
import bart.oilcraft.client.gui.GuiHandler;
import bart.oilcraft.core.proxy.CommonProxy;
import bart.oilcraft.creativetab.OilCraftTab;
import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.EntityOilBoss;
import bart.oilcraft.entities.EntityOilBall;
import bart.oilcraft.fluids.BucketRegistry;
import bart.oilcraft.fluids.OilCraftFluidRegistry;
import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.References;
import bart.oilcraft.lib.handler.*;
import bart.oilcraft.potions.ModPotions;
import bart.oilcraft.recipes.CraftingHandler;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilFurnaceRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;


@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS, dependencies = References.DEPENDENCIES)
public class OilCraftMain {

    @Instance
    public static OilCraftMain instance;

    @SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
    public static CommonProxy proxy;
    public static boolean thaumcraftLoaded = false;
    public static boolean thermalExpansionLoaded = false;

    private static CreativeTabs oilCraftTab = new OilCraftTab(CreativeTabs.getNextID(), References.MODID);

    public static CreativeTabs getCreativeTab() {
        return oilCraftTab;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Oilcraft PreInitialization");

        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
        thermalExpansionLoaded = Loader.isModLoaded("ThermalExpansion");
        System.out.println("Is Thaumcraft loaded: " + thaumcraftLoaded);
        System.out.println("Is Thermal Expansion loaded: " + thermalExpansionLoaded);

        ConfigurationHandler.Init(event.getSuggestedConfigurationFile());
        OilCraftBlockRegistry.init();
        OilCraftFluidRegistry.init();
        OilCraftItemRegistry.init();
        ModPotions.init();
        BucketRegistry.registerBucket();

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        NetworkRegistry.INSTANCE.registerGuiHandler(OilCraftMain.instance, new GuiHandler());

        GameRegistry.registerWorldGenerator(new WorldGenerationHandler(), 3);

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Oilcraft Initialization");
        CraftingHandler.init();
        proxy.registerTileEntities();

        proxy.registerRenderInformation();
        EntityRegistry.registerModEntity(EntityGooBall.class, "gooball", 1, this, 20, 3, true);
        if (ConfigurationHandler.slimeSpawn) {
            EntityRegistry.addSpawn(EntityGooBall.class, 5, 1, 3, EnumCreatureType.monster, BiomeGenBase.plains);
        }
        EntityRegistry.registerModEntity(EntityOilBall.class, "oilball", 2, this, 20, 3, true);
        EntityRegistry.registerModEntity(EntityOilBoss.class, "oilboss", 3, this, 50, 3, true);

        if (ConfigurationHandler.thaumAspects && thaumcraftLoaded)
            ThaumcraftHandler.ThaumcraftAspectHandler();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        OilCompressorRegistry.processBuffer();
        OilFurnaceRegistry.processBuffer();
    }

}
