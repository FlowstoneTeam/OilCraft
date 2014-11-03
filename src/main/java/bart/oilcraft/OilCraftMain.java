package bart.oilcraft;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.client.gui.GuiHandler;
import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import bart.oilcraft.lib.handler.CraftingHandler;
import bart.oilcraft.core.proxy.CommonProxy;
import bart.oilcraft.creativetab.OilCraftTab;
import bart.oilcraft.fluids.BucketRegistry;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.References;
import bart.oilcraft.lib.handler.BucketHandler;
import bart.oilcraft.lib.handler.WorldGenerationHandler;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilFurnaceRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
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
import org.lwjgl.Sys;


@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class OilCraftMain {

    @Instance
    public static OilCraftMain instance;

    @SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
    public static CommonProxy proxy;

    public static boolean slimeSpawn;

    private static CreativeTabs oilCraftTab = new OilCraftTab(CreativeTabs.getNextID(), References.MODID);
    public static CreativeTabs getCreativeTab() {
        return oilCraftTab;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Oilcraft PreInitialization");
        ConfigurationHandler.Init(event.getSuggestedConfigurationFile());
        ModBlocks.init();
        ModFluids.init();
        ModItems.init();
        BucketRegistry.registerBucket();

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        NetworkRegistry.INSTANCE.registerGuiHandler(OilCraftMain.instance, new GuiHandler());

        GameRegistry.registerWorldGenerator(new WorldGenerationHandler(), 2);

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Oilcraft Initialization");
        CraftingHandler.init();
        proxy.registerTileEntities();
        EnchantRegistry.registerEnchants();

        proxy.registerRenderInformation();
        EntityRegistry.registerModEntity(EntityGooBall.class, "GooBall", 2, this, 20, 3, true);
        if(slimeSpawn) {
            EntityRegistry.addSpawn(EntityGooBall.class, 5, 2, 3, EnumCreatureType.monster, BiomeGenBase.plains);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        OilCompressorRegistry.processBuffer();
        //OilFurnaceRegistry.processBuffer();
    }

}
