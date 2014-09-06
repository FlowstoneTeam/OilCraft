package bart.oilcraft.lib.handler;

import bart.oilcraft.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingHandler {
    public static void init() {
        registerRecipes();
    }

    public static void registerRecipes() {

        CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "CPC", "I I", "CCC", 'C', Blocks.cobblestone, 'P', Blocks.piston, 'I', Items.iron_ingot);
    }
}