package bart.oilcraft.lib.handler;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.items.ModItems;
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
        CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', ModItems.EnergyExceptor);
        CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', ModItems.EnergyExceptor);
        CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EnergyExceptor), "RRR", "RGR", "RRR", 'R', Items.redstone, 'G', Items.gold_nugget);
    }

}