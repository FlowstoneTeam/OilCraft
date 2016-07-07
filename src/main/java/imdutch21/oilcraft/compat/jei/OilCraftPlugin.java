package imdutch21.oilcraft.compat.jei;

import imdutch21.oilcraft.block.OCBlockRegistry;
import imdutch21.oilcraft.compat.jei.oilcompressor.OilCompressorRecipeCategory;
import imdutch21.oilcraft.compat.jei.oilcompressor.OilCompressorRecipeHandler;
import imdutch21.oilcraft.compat.jei.oilcompressor.OilCompressorRecipeMaker;
import imdutch21.oilcraft.inventory.gui.GuiOilCompressor;
import imdutch21.oilcraft.lib.ModInfo;
import mezz.jei.api.*;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class OilCraftPlugin extends BlankModPlugin {
    public static IJeiHelpers jeiHelper;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelper.getGuiHelper();

        registry.addRecipeCategories(new OilCompressorRecipeCategory(guiHelper));

        registry.addRecipeHandlers(new OilCompressorRecipeHandler());

        registry.addRecipeCategoryCraftingItem(new ItemStack(OCBlockRegistry.OIL_COMPRESSOR), ModInfo.ID + ":oil_compressor");
        registry.addRecipeCategoryCraftingItem(new ItemStack(OCBlockRegistry.OIL_FURNACE),  VanillaRecipeCategoryUid.SMELTING);

        registry.addRecipes(OilCompressorRecipeMaker.getRecipes());


        registry.addRecipeClickArea(GuiOilCompressor.class, 31, 34, 18, 21, ModInfo.ID + ":oil_compressor");

    }
}
