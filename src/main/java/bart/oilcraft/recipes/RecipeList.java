package bart.oilcraft.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Bart on 9-7-2015.
 */
public class RecipeList {

    public static ArrayList<HeatedFurnaceRecipe> HeatedFurnaceRecipes = new ArrayList<HeatedFurnaceRecipe>();
    public static ArrayList<OilCompressorRecipe> OilCompressorRecipes = new ArrayList<OilCompressorRecipe>();

    public static HeatedFurnaceRecipe getHeatedFurnaceRecipe(Item input, int meta){
        for(HeatedFurnaceRecipe recipe:HeatedFurnaceRecipes)
            if(recipe.input == input && recipe.metaInput == meta)
                return recipe;
        return null;
    }

    public static OilCompressorRecipe getOilCompressorRecipe(Item input, int meta){
        for(OilCompressorRecipe recipe:OilCompressorRecipes)
            if(recipe.input == input && recipe.meta == meta)
                return recipe;
        return null;
    }

    public static ItemStack[] getOilCompressorItems(){
        ItemStack[] items = new ItemStack[OilCompressorRecipes.size()];
        int i = 0;
        for(OilCompressorRecipe recipe:OilCompressorRecipes) {
            items[i] = new ItemStack(recipe.input, 1, recipe.meta);
            i++;
        }
        return items;
    }
}
