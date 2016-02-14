package bart.oilcraft.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bart on 12/02/2016.
 */
public class OilCompressorRecipe {
    public static ArrayList<OilCompressorRecipe> oilCompressorRecipes = new ArrayList<OilCompressorRecipe>();
    public ItemStack input;
    public int oilAmount;
    public int energyAmount;
    public int time;

    public OilCompressorRecipe(ItemStack input, int oilAmount, int energyAmount, int time) {
        this.input = input;
        this.oilAmount = oilAmount;
        this.energyAmount = energyAmount;
        this.time = time;
    }

    public static void addRecipe(ItemStack input, int oilAmount, int energyAmount, int time) {
        oilCompressorRecipes.add(new OilCompressorRecipe(input, oilAmount, energyAmount, time));
    }

    public static void addRecipe(List<ItemStack> input, int oilAmount, int energyAmount, int time) {
        for (ItemStack stack : input)
            oilCompressorRecipes.add(new OilCompressorRecipe(stack, oilAmount, energyAmount, time));
    }

    public static OilCompressorRecipe getRecipeFromItem(ItemStack input) {
        for (OilCompressorRecipe recipe : oilCompressorRecipes)
            if (recipe.input != null && matches(recipe.input, input))
                return recipe;
        return null;
    }

    private static boolean matches(ItemStack itemStack1, ItemStack itemStack2) {
        return itemStack2.getItem() == itemStack1.getItem() && itemStack2.getItemDamage() == itemStack1.getItemDamage();
    }

    public int getOilAmount() {
        return oilAmount;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }
}
