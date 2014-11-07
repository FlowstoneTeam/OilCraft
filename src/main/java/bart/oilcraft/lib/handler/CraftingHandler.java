package bart.oilcraft.lib.handler;

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
    //machines
    public static boolean oilCompressor;
    public static boolean oilGenerator;
    public static boolean oilFurnace;
    public static boolean slipperyRemover;

    //blocks
    public static boolean oilLayer;

    //items
    public static boolean energyAcceptor;
    public static boolean energyDistributeUpgrade;

    //misc
    public static boolean thermalExpansionItems;


    public static void registerRecipes() {
        //machines
        if(oilCompressor)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', ModItems.EnergyAcceptor);
        if(oilGenerator)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', ModItems.EnergyAcceptor);
        if(oilFurnace)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', ModItems.EnergyAcceptor, 'F', Blocks.furnace);

        //blocks
        if(oilLayer)CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModBlocks.OilLayer, 2),  new Object[] {ModItems.OilBall, ModItems.OilBall, ModItems.OilBall});

        //items
        if(energyAcceptor)CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EnergyAcceptor), "RRR", "RGR", "RRR", 'R', Items.redstone, 'G', Items.gold_nugget);
        if(energyDistributeUpgrade)CraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EnergyDistributeUpgrade), " G ", "GAG", " G ", 'a', ModItems.EnergyAcceptor, 'G', Items.gold_nugget);
        //misc
        if(thermalExpansionItems && GameRegistry.findItem("thermalexpansion", "powerCoilGold") != null){
            if(oilCompressor)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilCompressor), "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', Items.iron_ingot, 'A', GameRegistry.findItem("thermalexpansion", "powerCoilGold"));
            if(oilGenerator)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilGenerator), "IGI", "I I", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("thermalexpansion", "powerCoilGold"));
            if(oilFurnace)CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.OilFurnace), "III", "IFI", "IAI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', GameRegistry.findItem("thermalexpansion", "powerCoilGold"), 'F', Blocks.furnace);
        }
        CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.ShaleOilOre), "C  ","   ", "   ", 'C', ModBlocks.CrudeOilOre);
    }

}