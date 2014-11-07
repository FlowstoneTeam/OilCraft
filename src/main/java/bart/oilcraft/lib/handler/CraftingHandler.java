package bart.oilcraft.lib.handler;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
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
        //machines
        if(ConfigurationHandler.oilCompressor)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', ModItems.EnergyAcceptor);
        if(ConfigurationHandler.oilGenerator)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', ModItems.EnergyAcceptor);
        if(ConfigurationHandler.oilFurnace)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', ModItems.EnergyAcceptor, 'F', Blocks.furnace);

        //blocks
        if(ConfigurationHandler.oilLayer)CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModBlocks.OilLayer, 2),  new Object[] {ModItems.OilBall, ModItems.OilBall, ModItems.OilBall});

        //items
        if(ConfigurationHandler.energyAcceptor)CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EnergyAcceptor), "RRR", "RGR", "RRR", 'R', Items.redstone, 'G', Items.gold_nugget);
        if(ConfigurationHandler.energyDistributeUpgrade)CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EnergyDistributeUpgrade), " G ", "GAG", " G ", 'A', ModItems.EnergyAcceptor, 'G', Items.gold_nugget);

        //misc
        if(ConfigurationHandler.thermalExpansionItems && OilCraftMain.thermalExpansionLoaded && GameRegistry.findItem("ThermalExpansion", "powerCoilGold") != null ){
            if(ConfigurationHandler.oilCompressor)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"));
            if(ConfigurationHandler.oilGenerator)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"));
            if(ConfigurationHandler.oilFurnace)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"), 'F', Blocks.furnace);
        }
        CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.ShaleOilOre), "C  ","   ", "   ", 'C', ModBlocks.CrudeOilOre);
    }

}