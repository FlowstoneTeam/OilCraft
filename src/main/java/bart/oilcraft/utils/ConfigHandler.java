package bart.oilcraft.utils;

import bart.oilcraft.lib.ModInfo;
import bart.oilcraft.recipes.UserRecipes;
import com.google.gson.stream.JsonReader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigHandler {
    public static final ConfigHandler INSTANCE = new ConfigHandler();
    public static final String[] CATEGORIES = {"Blocks"};
    public Configuration config;
    public static String path = "";

    public void loadConfig(FMLPreInitializationEvent event) {
        path = event.getSuggestedConfigurationFile().getPath();
        config = new Configuration(new File(path.replace("oilcraft.cfg", "oilcraft/mainConfig.cfg")));
        config.load();
        syncConfigs();
    }

    private void syncConfigs() {
        save();
    }

    public void save() {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(ModInfo.ID)) {
            syncConfigs();
        }
    }

    public static void userRecipes() {
        File file = new File(path.replace("oilcraft.cfg", "oilcraft/recipes.json"));
        try {
            if(file.createNewFile()) {
                PrintWriter writer = new PrintWriter(file);
                writer.println("{");
                writer.println("}");
                writer.close();
                System.out.println("Created new file");
            }
            JsonReader jsonReader = new JsonReader(new FileReader(file));

            jsonReader.beginObject();
            UserRecipes.readJson(jsonReader);
            jsonReader.endObject();

            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}