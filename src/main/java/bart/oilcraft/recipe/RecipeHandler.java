package bart.oilcraft.recipe;

import bart.oilcraft.block.OCBlockRegistry;
import bart.oilcraft.util.ConfigHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {


    public static void init(){
        initRecipes();
        initOilCompressorRecipes();
        ConfigHandler.userRecipes();
    }


    private static void initRecipes(){
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.machineFrame)), "III", "I I", "III", 'I', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.oilCompressor)), " P ", "GMG", " R ", 'P', new ItemStack(Item.getItemFromBlock(Blocks.piston)), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.machineFrame)), 'R', new ItemStack(Items.redstone), 'G', new ItemStack(Items.gold_ingot));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.oilGenerator)), " B ", "GMG", " R ", 'B', new ItemStack(Items.bucket), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.machineFrame)), 'R', new ItemStack(Items.redstone), 'G', new ItemStack(Items.gold_ingot));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.oilFurnace)), " F ", "GMG", " R ", 'B', new ItemStack(Item.getItemFromBlock(Blocks.furnace)), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.machineFrame)), 'R', new ItemStack(Items.redstone), 'G', new ItemStack(Items.gold_ingot));
    }

    private static void initOilCompressorRecipes(){
        OilCompressorRecipe.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.cobblestone)), 1, 75, 200);
        OilCompressorRecipe.addRecipe(new ItemStack(Items.diamond), 100, 500, 400);
    }
}
