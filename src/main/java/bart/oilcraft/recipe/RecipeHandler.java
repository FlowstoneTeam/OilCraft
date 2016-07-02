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
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.MACHINE_FRAME)), "III", "I I", "III", 'I', new ItemStack(Items.IRON_INGOT));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.OIL_COMPRESSOR)), " P ", "GMG", " R ", 'P', new ItemStack(Item.getItemFromBlock(Blocks.PISTON)), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.MACHINE_FRAME)), 'R', new ItemStack(Items.REDSTONE), 'G', new ItemStack(Items.GOLD_INGOT));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.OIL_GENERATOR)), " B ", "GMG", " R ", 'B', new ItemStack(Items.BUCKET), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.MACHINE_FRAME)), 'R', new ItemStack(Items.REDSTONE), 'G', new ItemStack(Items.GOLD_INGOT));
        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(OCBlockRegistry.OIL_FURNACE)), " F ", "GMG", " R ", 'F', new ItemStack(Item.getItemFromBlock(Blocks.FURNACE)), 'M' , new ItemStack(Item.getItemFromBlock(OCBlockRegistry.MACHINE_FRAME)), 'R', new ItemStack(Items.REDSTONE), 'G', new ItemStack(Items.GOLD_INGOT));
    }

    private static void initOilCompressorRecipes(){
        OilCompressorRecipe.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)), 1, 75, 200);
        OilCompressorRecipe.addRecipe(new ItemStack(Items.DIAMOND), 100, 500, 400);
    }
}
