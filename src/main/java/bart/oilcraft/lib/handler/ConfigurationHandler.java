package bart.oilcraft.lib.handler;

import bart.oilcraft.lib.References;
import bart.oilcraft.util.ConnectedTextureHelper;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilFurnaceRegistry;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

/**
 * Created by Bart on 8-9-2014.
 */
public class ConfigurationHandler {


    public static Configuration configuration;

    //oil generator
    public static int RfForOilGen;
    public static int OilUsageGen;

    //oil furnace
    public static int RfForOilFur;
    public static int ProcessTimeFur;
    public static int cyclesAmountFur;
    public static int OilUsageFur;

    //entity
    public static boolean slimeSpawn;
    public static boolean leaveTrail;
    public static boolean regenOil;
    public static boolean slipperyGive;

    //mod integration
    public static boolean thaumAspects;

    //crafting
    public static boolean oilCompressor;
    public static boolean oilGenerator;
    public static boolean oilFurnace;
    public static boolean slipperyRemover;
    public static boolean oilLayer;
    public static boolean energyAcceptor;
    public static boolean energyDistributeUpgrade;
    public static boolean thermalExpansionItems;


    public static void Init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    private static void loadConfiguration() {


        Property connectedTextures = configuration.get("Connected textures", "Connected textures", true);
        connectedTextures.comment = "Enable connected textures";
        ConnectedTextureHelper.EnableConnectedTextures = connectedTextures.getBoolean();

        configuration.addCustomCategoryComment("CustomizationCompressor", "Custom oil compressor 'recipes'. Syntax: output:energy:time:modid:name[:metadata]");
        Property propValuesComp = configuration.get("CustomizationCompressor", "CustomOilValues", new String[]{"1000:1000:120:minecraft:diamond", "1:10:40:minecraft:cobblestone", "500:500:80:oilcraft:shaleoilore", "5:15:40:oilcraft:oilball", "4000:2000:140:oodmod:Kroostyl"});
        OilCompressorRegistry.buffer = propValuesComp.getStringList();

        configuration.addCustomCategoryComment("CustomizationHeated", "Custom extra heated 'recipes'. Syntax: modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]");
        Property propValuesHeated = configuration.get("CustomizationHeated", "CustomOilValues", new String[]{"minecraft:dirt:1000:1000:120:minecraft:diamond"});
        OilFurnaceRegistry.buffer = propValuesHeated.getStringList();

        configuration.addCustomCategoryComment("CustomizationGenerator", "Custom oil generator");
        Property oilGeneratorRF = configuration.get("CustomizationGenerator", "RF/tick", 80);
        RfForOilGen = oilGeneratorRF.getInt();
        Property oilGeneratorOil = configuration.get("CustomizationGenerator", "Oil/tick", 30);
        OilUsageGen = oilGeneratorOil.getInt();

        configuration.addCustomCategoryComment("CustomizationFurnace", "Custom Oil Furnace");
        Property oilFurnaceRF = configuration.get("CustomizationFurnace", "RF/run", 500);
        oilFurnaceRF.comment = "Custom Oil Furnace";
        RfForOilFur = oilFurnaceRF.getInt();
        Property oilFurnaceTime = configuration.get("CustomizationFurnace", "Time/run", 150);
        ProcessTimeFur = oilFurnaceTime.getInt();
        Property oilFurnaceCycle = configuration.get("CustomizationFurnace", "Cycles for oilConsumption", 10);
        cyclesAmountFur = oilFurnaceCycle.getInt();
        Property oilFurnaceOil = configuration.get("CustomizationFurnace", "Oil/cycleConsumption", 30);
        OilUsageFur = oilFurnaceOil.getInt();

        configuration.addCustomCategoryComment("gooball", "Here you can set what the gooball mob does");
        Property gooBallSpawn = configuration.get("gooball", "Can spawn", true);
        slimeSpawn = gooBallSpawn.getBoolean();
        Property slimeLeaveTrail = configuration.get("gooball", "Leave trail", false);
        leaveTrail = slimeLeaveTrail.getBoolean();
        Property regenInOil = configuration.get("gooball", "Regen in oil", true);
        regenOil = regenInOil.getBoolean();
        Property slipperyGiveOnTouch = configuration.get("gooball", "Enchants your items", true);
        slipperyGive = slipperyGiveOnTouch.getBoolean();

        configuration.addCustomCategoryComment("Crafting", "You can enable/disable the crafting recipes in this mod");
        Property craftingCompressor = configuration.get("Crafting", "BlockOilCompressor", true);
        oilCompressor = craftingCompressor.getBoolean();
        Property craftingGenerator = configuration.get("Crafting", "BlockOilGenerator", true);
        oilGenerator = craftingGenerator.getBoolean();
        Property craftingFurnace = configuration.get("Crafting", "OilFurnace", true);
        oilFurnace = craftingFurnace.getBoolean();
        Property craftingRemover = configuration.get("Crafting", "SlipperyRemover", true);
        slipperyRemover = craftingRemover.getBoolean();

        Property craftingOilLayer = configuration.get("Crafting", "OilLayer", true);
        oilLayer = craftingOilLayer.getBoolean();

        Property craftingAcceptor = configuration.get("Crafting", "ItemEnergyAcceptor", true);
        energyAcceptor = craftingAcceptor.getBoolean();
        Property craftingDistributeUpgrade = configuration.get("Crafting", "ItemEnergyDistributeUpgrade", true);
        energyDistributeUpgrade = craftingDistributeUpgrade.getBoolean();

        configuration.addCustomCategoryComment("ModIntegration", "You can enable/disable the mod intergration in this mod");
        Property craftingThermalE = configuration.get("ModIntegration", "Thermal Expansion items in recipes", true);
        thermalExpansionItems = craftingThermalE.getBoolean();
        Property aspectsThaumcraft = configuration.get("ModIntergration", "Thaumcraftcraft recipes for items", true);
        thaumAspects = aspectsThaumcraft.getBoolean();

        configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(References.MODID)) {
            loadConfiguration();
        }

    }
}
