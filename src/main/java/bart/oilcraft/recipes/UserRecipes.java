package bart.oilcraft.recipes;

import com.google.gson.stream.JsonReader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.io.IOException;

/**
 * Created by Bart on 13/02/2016.
 */
public class UserRecipes {
    public static void readJson(JsonReader jsonReader) throws IOException {
        while (jsonReader.hasNext()) {
            String recipeName = jsonReader.nextName();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.contains("recipe")) {
                    jsonReader.beginObject();
                    String inputType = "";
                    String inputItem = "";
                    int compressTime = 0;
                    int oilAmount = 0;
                    int energyAmount = 0;
                    String outputType = "";
                    String outputItem = "";
                    int metaInput = 0;
                    int metaOutput = 0;
                    int inputAmount = 1;
                    int outputAmount = 1;
                    while (jsonReader.hasNext()) {
                        switch (recipeName) {
                            case "compressor": {
                                String n = jsonReader.nextName();
                                switch (n) {
                                    case "inputType":
                                        inputType = jsonReader.nextString();
                                        break;
                                    case "inputItem":
                                        inputItem = jsonReader.nextString();
                                        break;
                                    case "compressTime":
                                        compressTime = jsonReader.nextInt();
                                        break;
                                    case "oilAmount":
                                        oilAmount = jsonReader.nextInt();
                                        break;
                                    case "energyAmount":
                                        energyAmount = jsonReader.nextInt();
                                        break;
                                    case "meta":
                                    case "metaInput":
                                        metaInput = jsonReader.nextInt();
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    switch (recipeName) {
                        case "compressor":
                            oilCompressorRecipeBuffer(inputType, inputItem, oilAmount, compressTime, energyAmount, metaInput);
                            break;
                    }
                    jsonReader.endObject();
                }
            }
            jsonReader.endObject();
        }
    }

    public static void oilCompressorRecipeBuffer(String inputType, String inputItem, int oilAmount, int compressTime, int energyAmount, int metaInput) {
        if (inputType.toLowerCase().equals("oredictionary")) {
            if (OreDictionary.getOres(inputItem).size() > 0 && compressTime > 0 && oilAmount > 0 && energyAmount >= 0)
                OilCompressorRecipe.addRecipe(OreDictionary.getOres(inputItem), oilAmount, energyAmount, compressTime);
        } else if (inputType.toLowerCase().equals("id")) {
            if (Item.getItemById(Integer.getInteger(inputItem)) != null && compressTime >= 0 && oilAmount > 0 && energyAmount >= 0 && metaInput >= 0)
                OilCompressorRecipe.addRecipe(new ItemStack(Item.getItemById(Integer.getInteger(inputItem)), 1, metaInput), oilAmount, energyAmount, compressTime);
        } else if (GameRegistry.findItem(inputType, inputItem) != null && compressTime >= 0 && oilAmount > 0 && energyAmount >= 0 && metaInput >= 0) {
            OilCompressorRecipe.addRecipe(new ItemStack(GameRegistry.findItem(inputType, inputItem), 1, metaInput), oilAmount, energyAmount, compressTime);
        } else if (GameRegistry.findBlock(inputType, inputItem) != null && compressTime >= 0 && oilAmount > 0 && energyAmount >= 0 && metaInput >= 0)
            OilCompressorRecipe.addRecipe(new ItemStack(Item.getItemFromBlock(GameRegistry.findBlock(inputType, inputItem)), 1, metaInput), oilAmount, energyAmount, compressTime);
    }
}
