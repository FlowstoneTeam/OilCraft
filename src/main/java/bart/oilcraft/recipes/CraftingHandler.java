package bart.oilcraft.recipes;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.blocks.OilCraftBlockRegistry;
import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingHandler {
    public static void init() {
        registerRecipes();
    }


    public static void registerRecipes() {
        //machines
        if (ConfigurationHandler.oilCompressor)
            CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', OilCraftItemRegistry.energyAcceptor);
        if (ConfigurationHandler.oilGenerator)
            CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', OilCraftItemRegistry.energyAcceptor);
        if (ConfigurationHandler.oilFurnace)
            CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', OilCraftItemRegistry.energyAcceptor, 'F', Blocks.furnace);

        //blocks
        if (ConfigurationHandler.oilLayer)
            CraftingManager.getInstance().addShapelessRecipe(new ItemStack(OilCraftBlockRegistry.oilLayer, 2), OilCraftItemRegistry.oilBall, OilCraftItemRegistry.oilBall, OilCraftItemRegistry.oilBall);

        //items
        if (ConfigurationHandler.energyAcceptor)
            CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftItemRegistry.energyAcceptor), "RRR", "RGR", "RRR", 'R', Items.redstone, 'G', Items.gold_nugget);
        if (ConfigurationHandler.energyDistributeUpgrade)
            CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftItemRegistry.energyDistributeUpgrade), " G ", "GAG", " G ", 'A', OilCraftItemRegistry.energyAcceptor, 'G', Items.gold_nugget);
        CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftItemRegistry.note), "C  ", "   ", "   ", 'C', Blocks.cobblestone);

        //misc
        if (ConfigurationHandler.thermalExpansionItems && OilCraftMain.thermalExpansionLoaded && GameRegistry.findItem("ThermalExpansion", "powerCoilGold") != null) {
            if (ConfigurationHandler.oilCompressor)
                CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"));
            if (ConfigurationHandler.oilGenerator)
                CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"));
            if (ConfigurationHandler.oilFurnace)
                CraftingManager.getInstance().addRecipe(new ItemStack(OilCraftBlockRegistry.oilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"), 'F', Blocks.furnace);
        }
    }

}