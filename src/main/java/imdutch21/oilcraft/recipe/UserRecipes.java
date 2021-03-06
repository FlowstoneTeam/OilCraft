package imdutch21.oilcraft.recipe;

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
                        if (recipeName.equals("compressor")) {
                            String n = jsonReader.nextName();
                            if (n.equals("inputType")) {
                                inputType = jsonReader.nextString();

                            } else if (n.equals("inputItem")) {
                                inputItem = jsonReader.nextString();

                            } else if (n.equals("compressTime")) {
                                compressTime = jsonReader.nextInt();

                            } else if (n.equals("oilAmount")) {
                                oilAmount = jsonReader.nextInt();

                            } else if (n.equals("energyAmount")) {
                                energyAmount = jsonReader.nextInt();

                            } else if (n.equals("meta") || n.equals("metaInput")) {
                                metaInput = jsonReader.nextInt();

                            }
                        }
                    }
                    if (recipeName.equals("compressor")) {
                        oilCompressorRecipeBuffer(inputType, inputItem, oilAmount, compressTime, energyAmount, metaInput);

                    }
                    jsonReader.endObject();
                }
            }
            jsonReader.endObject();
        }
    }

    private static void oilCompressorRecipeBuffer(String inputType, String inputItem, int oilAmount, int compressTime, int energyAmount, int metaInput) {
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
