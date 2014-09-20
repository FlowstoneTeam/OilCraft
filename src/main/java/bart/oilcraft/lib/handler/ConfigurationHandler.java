package bart.oilcraft.lib.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import bart.oilcraft.lib.References;

import java.io.File;

/**
 * Created by Bart on 8-9-2014.
 */
public class ConfigurationHandler {


    public static Configuration configuration;
    public static int cobbleRf;
    public static int crudeRf;
    public static int diamondRf;
    public static int kroostylRf;
    public static int cobbleOil;
    public static int crudeOil;
    public static int diamondOil;
    public static int kroostylOil;
    public static int cobbleProses;
    public static int crudeProses;
    public static int diamondProses;
    public static int kroostylProses;
    public static int speedUpgradePercentage;
    public static int capacityUpgradePercentage;
    public static int energyEfficiencyUpgradePercentage;
    public static int stackUpgradeNumber;
    public static int stackUpgradeProses;
    public static int oilyDiamondOil;
    public static int oilyDiamondProses;
    public static int oilyDiamondRF;
    public static int slipperyEnchant;

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

        cobbleRf = configuration.get("(Oil Compressor)You can set the amount of rf it takes to make oil form this block/item <= 8000", "CobbleStone", 10).getInt(10);
        crudeRf = configuration.get("(Oil Compressor)You can set the amount of rf it takes to make oil form this block/item <= 8000", "CrudeOil Ore", 500).getInt(500);
        diamondRf = configuration.get("(Oil Compressor)You can set the amount of rf it takes to make oil form this block/item <= 8000", "Diamond", 1000).getInt(1000);
        kroostylRf = configuration.get("(Oil Compressor)You can set the amount of rf it takes to make oil form this block/item <= 8000", "Kroostyl(only with oodsmod instaled)", 2000).getInt(2000);

        cobbleOil = configuration.get("(Oil Compressor)You can set the amount of oil the block/item makes <= 10 000", "CobbleStone", 1).getInt(1);
        crudeOil = configuration.get("(Oil Compressor)You can set the amount of oil the block/item makes <= 10 000", "CrudeOil Ore", 500).getInt(500);
        diamondOil = configuration.get("(Oil Compressor)You can set the amount of oil the block/item makes <= 10 000", "Diamond", 1000).getInt(1000);
        kroostylOil = configuration.get("(Oil Compressor)You can set the amount of oil the block/item makes <= 10 000", "Kroostyl(only with oodsmod instaled)", 4000).getInt(4000);


        cobbleProses = configuration.get("(Oil Compressor)You can set the amount of times it takes to proses the block/item (20 = 1 sec)", "CobbleStone", 40).getInt(40);
        crudeProses = configuration.get("(Oil Compressor)You can set the amount of times it takes to proses the block/item (20 = 1 sec)", "CrudeOilOre", 80).getInt(80);
        diamondProses = configuration.get("(Oil Compressor)You can set the amount of times it takes to proses the block/item (20 = 1 sec)", "Diamond", 120).getInt(120);
        kroostylProses = configuration.get("(Oil Compressor)You can set the amount of times it takes to proses the block/item (20 = 1 sec)", "Kroostyl(only with Ood's mod installed)", 140).getInt(140);

        oilyDiamondRF = configuration.get("(Oil Infuser)You can set the amount of rf it takes to make oil form this block/item <= 8000", "Oily Diamond", 1000).getInt(1000);

        oilyDiamondOil = configuration.get("(Oil Infuser)You can set the amount of oil this items needs to get infused", "Oily Diamond", 1000).getInt(1000);

        oilyDiamondProses = configuration.get("(Oil Infuser)You can set the amount of times it takes to proses the block/item (20 = 1 sec)", "Oily Diamond", 200).getInt(200);


        speedUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "SpeedUpgrade", 80).getInt(80);
        //capacityUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "CapacityUpgrade", 120).getInt(120);
        energyEfficiencyUpgradePercentage = configuration.get("You can set the percentage upgrades will upgrade the machine", "EnergyEfficiencyUpgrade", 80).getInt(80);
        stackUpgradeNumber = configuration.get("You can set the percentage upgrades will upgrade the machine ", "StackUpgrade(change this to the number of items to consume)", 16).getInt(16);
        stackUpgradeProses = configuration.get("You can set the percentage upgrades will upgrade the machine ", "StackUpgrade(the amount of times the proses takes more))", 2).getInt(2);

        slipperyEnchant = configuration.get("You can set the enchantment id here", "Slippery enchantmen", 49).getInt(49);
        if(configuration.hasChanged()){
            configuration.save();
        }
    }

}
