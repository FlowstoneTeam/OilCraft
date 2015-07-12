package bart.oilcraft.recipes;

import net.minecraft.item.Item;

/**
 * Created by Bart on 9-7-2015.
 */
public class OilCompressorRecipe {

    public Item input;
    public int oil;
    public int time;
    public int energy;
    public int meta;

    public OilCompressorRecipe(Item input, int oil, int time, int energy, int meta) {
        this.input = input;
        this.oil = oil;
        this.time = time;
        this.energy = energy;
        this.meta = meta;
        RecipeList.OilCompressorRecipes.add(this);
    }
}
