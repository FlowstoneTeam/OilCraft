package bart.oilcraft.recipes;

import net.minecraft.item.Item;

/**
 * Created by Bart on 9-7-2015.
 */
public class HeatedFurnaceRecipe {
    public Item input;
    public Item output;
    public int energy;
    public int time;
    public int oil;
    public int metaInput;
    public int metaOutput;

    public HeatedFurnaceRecipe(Item input, Item output, int energy, int time, int oil, int metaInput, int metaOutput){
        this.input = input;
        this.output = output;
        this.energy = energy;
        this.time = time;
        this.oil = oil;
        this.metaInput = metaInput;
        this.metaOutput = metaOutput;
        RecipeList.HeatedFurnaceRecipes.add(this);
    }

}
