package bart.oilcraft.lib.handler;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import bart.oilcraft.tileentities.TileEntitySlipperyRemover;
import bart.oilcraft.util.ConnectedTextureHelper;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilFurnaceRegistry;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import bart.oilcraft.lib.References;
import net.minecraftforge.common.config.Property;
import java.io.File;

/**
 * Created by Bart on 8-9-2014.
 */
public class ConfigurationHandler {


    public static Configuration configuration;



    public static void Init(File configFile){
        if(configuration == null ){
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(References.MODID)){
            loadConfiguration();
        }

    }
    private static void loadConfiguration(){

        Property slipperyEnchant = configuration.get("EnchantmentId", "Slippery enchantment", 80);
        slipperyEnchant.comment = "Enchantment ids";
        EnchantRegistry.slipperyEnchantId = slipperyEnchant.getInt();

        Property connectedTextures = configuration.get("Connected textures", "Connected textures", true);
        connectedTextures.comment = "Enable connected textures";
        ConnectedTextureHelper.EnableConnectedTextures = connectedTextures.getBoolean();

        configuration.addCustomCategoryComment("CustomizationCompressor", "Custom oil compressor 'recipes'. Syntax: output:energy:time:modid:name[:metadata]");
        Property propValuesComp = configuration.get("CustomizationCompressor", "CustomOilValues", new String[]{"1000:1000:120:minecraft:diamond", "1:10:40:minecraft:cobblestone", "500:500:80:oilcraft:ShaleOilOre", "4000:2000:140:oodmod:Kroostyl"});
        OilCompressorRegistry.buffer = propValuesComp.getStringList();

        /*configuration.addCustomCategoryComment("CustomizationHeated", "Custom extra heated 'recipes'. Syntax: modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]");
        Property propValuesHeated = configuration.get("CustomizationHeated", "CustomOilValues", new String[]{"minecraft:dirt:1000:1000:120:minecraft:diamond"});
        OilFurnaceRegistry.buffer = propValuesHeated.getStringList();*/

        configuration.addCustomCategoryComment("CustomizationGenerator", "Custom oil generator");
        Property oilGeneratorRF = configuration.get("CustomizationGenerator", "RF/tick", 80);
        OilGeneratorEntity.RfForOil = oilGeneratorRF.getInt();
        Property oilGeneratorOil = configuration.get("CustomizationGenerator", "Oil/tick", 30);
        OilGeneratorEntity.OilUsage = oilGeneratorOil.getInt();

        configuration.addCustomCategoryComment("CustomizationRemover", "Custom Slippery Remover");
        Property slipperyRemoverRF = configuration.get("CustomizationRemover", "RF/run", 800);
        TileEntitySlipperyRemover.RfForOil = slipperyRemoverRF.getInt();
        Property slipperyRemoverTime = configuration.get("CustomizationRemover", "Time/run", 400);
        TileEntitySlipperyRemover.ProcessTime = slipperyRemoverTime.getInt();

        configuration.addCustomCategoryComment("CustomizationFurnace", "Custom Oil Furnace");
        Property oilFurnaceRF = configuration.get("CustomizationFurnace", "RF/run", 500);
        oilFurnaceRF.comment = "Custom Oil Furnace";
        TileEntityOilFurnace.RfForOil = oilFurnaceRF.getInt();
        Property oilFurnaceTime = configuration.get("CustomizationFurnace", "Time/run", 150);
        TileEntityOilFurnace.ProcessTime = oilFurnaceTime.getInt();
        Property oilFurnaceCycle = configuration.get("CustomizationFurnace", "Cycles for oilConsumption", 10);
        TileEntityOilFurnace.cyclesAmount = oilFurnaceCycle.getInt();
        Property oilFurnaceOil = configuration.get("CustomizationFurnace", "Oil/cycleConsumption", 30);
        TileEntityOilFurnace.OilUsage = oilFurnaceOil.getInt();

        configuration.addCustomCategoryComment("GooBall", "Here you can set what the GooBall mob does");
        Property gooBallSpawn = configuration.get("GooBall", "Can spawn", true);
        OilCraftMain.slimeSpawn = gooBallSpawn.getBoolean();
        Property slimeLeaveTrail = configuration.get("GooBall", "Leave trail", false);
        EntityGooBall.leaveTrail = slimeLeaveTrail.getBoolean();
        Property regenInOil = configuration.get("GooBall", "Regen in oil", true);
        EntityGooBall.regenOil = regenInOil.getBoolean();
        Property slipperyGive = configuration.get("GooBall", "Enchants your items", true);
        EntityGooBall.slipperyGive = slipperyGive.getBoolean();

        configuration.addCustomCategoryComment("Crafting", "You can enable/disable the crafting recipes in this mod");
        Property craftingCompressor = configuration.get("Crafting", "OilCompressor", true);
        CraftingHandler.oilCompressor = craftingCompressor.getBoolean();
        Property craftingGenerator = configuration.get("Crafting", "OilGenerator", true);
        CraftingHandler.oilGenerator = craftingGenerator.getBoolean();
        Property craftingFurnace = configuration.get("Crafting", "OilFurnace", true);
        CraftingHandler.oilFurnace = craftingFurnace.getBoolean();
        Property craftingRemover = configuration.get("Crafting", "SlipperyRemover", true);
        CraftingHandler.slipperyRemover = craftingRemover.getBoolean();

        Property craftingOilLayer = configuration.get("Crafting", "OilLayer", true);
        CraftingHandler.oilLayer = craftingOilLayer.getBoolean();

        Property craftingAcceptor = configuration.get("Crafting", "EnergyAcceptor", true);
        CraftingHandler.energyAcceptor = craftingAcceptor.getBoolean();

        Property craftingThermalE = configuration.get("Crafting", "Thermal Expansion items in recipes", true);
        CraftingHandler.thermalExpansionItems = craftingThermalE.getBoolean();

        configuration.save();
    }
}
