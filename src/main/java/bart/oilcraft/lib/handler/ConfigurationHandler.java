package bart.oilcraft.lib.handler;

import bart.oilcraft.blocks.BlockMachineFrame;
import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import bart.oilcraft.util.ConnectedTextureHelper;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.OilFurnaceRegistry;
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

        Property propValues = configuration.get("Customization", "CustomOilValues", new String[]{"1000:1000:120:minecraft:diamond", "1:10:40:minecraft:cobblestone", "500:500:80:oilcraft:ShaleOilOre", "4000:2000:140:oodmod:Kroostyl"});
        propValues.comment = "Custom oil compressor 'recipes'. Syntax: output:energy:time:modid:name[:metadata]";
        OilCompressorRegistry.buffer = propValues.getStringList();

        Property infuserValues = configuration.get("Customization", "CustomInfusion", new String[]{"oilcraft:OilyDiamond:500:1000:200:minecraft:diamond"});
        infuserValues.comment = "Custom oil infuser 'recipes'. Syntax: modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]";
        OilInfuserRegistry.buffer = infuserValues.getStringList();

        Property customSmelting = configuration.get("Customization", "CustomInfusion", new String[]{"minecraft:dirt:500:1000:200:minecraft:diamond"});
        customSmelting.comment = "Custom oil smelting 'recipes'. Syntax: modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]";
        OilFurnaceRegistry.buffer = customSmelting.getStringList();

        Property oilGeneratorValue = configuration.get("Customization", "CustomGenerator", 80);
        oilGeneratorValue.comment = "Custom oil generator rf amount per tick";
        OilGeneratorEntity.RfForOil = oilGeneratorValue.getInt();

        configuration.save();
    }
}
