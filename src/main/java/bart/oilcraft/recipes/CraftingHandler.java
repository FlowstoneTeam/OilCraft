package bart.oilcraft.recipes;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.blocks.OilCraftBlockRegistry;
import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingHandler {
    public static void init() {
        registerRecipes();
    }


    public static void registerRecipes() {
        //machines
        if (ConfigurationHandler.oilCompressor)
            GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilCompressor, "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', "ingotIron", 'A', OilCraftItemRegistry.energyAcceptor));
        if (ConfigurationHandler.oilGenerator)
            GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilGenerator, "IGI", "I I", "IAI", 'I', "ingotIron", 'G', "ingotGold", 'A', OilCraftItemRegistry.energyAcceptor));
        if (ConfigurationHandler.oilFurnace)
            GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilFurnace, "III", "IFI", "IAI", 'I', "ingotIron", 'G', "ingotGold", 'A', OilCraftItemRegistry.energyAcceptor, 'F', Blocks.furnace));

        //blocks
        if (ConfigurationHandler.oilLayer)
            GameRegistry.addShapelessRecipe(new ItemStack(OilCraftBlockRegistry.oilLayer, 2), OilCraftItemRegistry.oilBall, OilCraftItemRegistry.oilBall, OilCraftItemRegistry.oilBall);

        //items
        if (ConfigurationHandler.energyAcceptor)
            GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftItemRegistry.energyAcceptor, "RRR", "RGR", "RRR", 'R', Items.redstone, 'G', "nuggetGold"));
        if (ConfigurationHandler.energyDistributeUpgrade)
            GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftItemRegistry.energyDistributeUpgrade, " G ", "GAG", " G ", 'A', OilCraftItemRegistry.energyAcceptor, 'G', "nuggetGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftItemRegistry.note, "C  ", "   ", "   ", 'C', Blocks.cobblestone));

        //misc
        if (ConfigurationHandler.thermalExpansionItems && OilCraftMain.thermalExpansionLoaded && GameRegistry.findItem("ThermalExpansion", "powerCoilGold") != null) {
            if (ConfigurationHandler.oilCompressor)
                GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilCompressor, "IPI", "I I", "IAI", 'P', Blocks.piston, 'I', "ingotIron", 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold")));
            if (ConfigurationHandler.oilGenerator)
                GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilGenerator, "IGI", "I I", "IAI", 'I', "ingotIron", 'G', "ingotGold", 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold")));
            if (ConfigurationHandler.oilFurnace)
                GameRegistry.addRecipe(new ShapedOreRecipe(OilCraftBlockRegistry.oilFurnace, "III", "IFI", "IAI", 'I', "ingotIron", 'G', "ingotGold", 'A', GameRegistry.findItem("ThermalExpansion", "powerCoilGold"), 'F', Blocks.furnace));
        }
    }

}