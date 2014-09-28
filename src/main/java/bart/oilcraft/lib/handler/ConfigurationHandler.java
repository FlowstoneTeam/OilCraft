package bart.oilcraft.lib.handler;

import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilInfuserRegistry;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import bart.oilcraft.lib.References;
import net.minecraftforge.common.config.Property;

import java.io.File;

/**
 * Created by Bart on 8-9-2014.
 */
public class ConfigurationHandler {


    public static Configuration configuration;
    public static int speedUpgradePercentage;
    public static int capacityUpgradePercentage;
    public static int energyEfficiencyUpgradePercentage;
    public static int stackUpgradeNumber;
    public static int stackUpgradeProses;
    public static int oilyDiamondOil;
    public static int oilyDiamondProses;
    public static int oilyDiamondRF;
    public static int slipperyEnchantID;
    public static boolean enableConnectedTextures;


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
        speedUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "SpeedUpgrade", 80).getInt(80);
        //capacityUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "CapacityUpgrade", 120).getInt(120);
        energyEfficiencyUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "EnergyEfficiencyUpgrade", 80).getInt(80);
        stackUpgradeNumber = configuration.get("You can set the percentage upgrades will upgrade the machine ", "StackUpgrade(change this to the number of items to consume)", 16).getInt(16);
        stackUpgradeProses = configuration.get("You can set the percentage upgrades will upgrade the machine ", "StackUpgrade(the amount of times the proses takes more))", 2).getInt(2);

        slipperyEnchantID = configuration.get("You can set the enchantment id here", "Slippery enchantment", 80).getInt(80);


        enableConnectedTextures = configuration.get("You can enable/disable the connected textures", "Enable connected textures", true).getBoolean(true);

        Property propValues = configuration.get("Customization", "CustomOilValues", new String[]{"1000:1000:120:minecraft:diamond", "1:10:40:minecraft:cobblestone", "500:500:80:oilcraft:CrudeOilOre", "4000:2000:140:oodmod:Kroostyl"});
        propValues.comment = "Custom oil compressor 'recipes'. Syntax: output:energy:time:modid:name[:metadata]";
        OilCompressorRegistry.buffer = propValues.getStringList();


        Property infuserValues = configuration.get("Customization", "CustomInfusion", new String[]{"oilcraft:OilyDiamond:500:1000:200:minecraft:diamond"});
        infuserValues.comment = "Custom oil infuser 'recipes'. Syntax: modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]";
        OilInfuserRegistry.buffer = infuserValues.getStringList();

        configuration.save();
    }
}
