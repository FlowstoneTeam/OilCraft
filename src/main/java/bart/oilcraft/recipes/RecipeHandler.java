package bart.oilcraft.recipes;

import bart.oilcraft.utils.ConfigHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 12/02/2016.
 */
public class RecipeHandler {


    public static void init(){
        initOilCompressorRecipes();
        ConfigHandler.userRecipes();
    }

    private static void initOilCompressorRecipes(){
        OilCompressorRecipe.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.cobblestone)), 1, 100, 200);
    }
}
