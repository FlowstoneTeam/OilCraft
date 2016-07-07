package imdutch21.oilcraft.compat.jei.oilcompressor;

import imdutch21.oilcraft.compat.jei.OilCraftPlugin;
import imdutch21.oilcraft.lib.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

public class OilCompressorRecipeCategory implements IRecipeCategory<OilCompressorRecipeJEI> {
    @Nonnull
    protected final IDrawableAnimated compressor;
    private final ResourceLocation backgroundLocation = new ResourceLocation(ModInfo.ID + ":textures/gui/jei/oil_compressor.png");
    @Nonnull
    private final IDrawable background = OilCraftPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 0, 0, 90, 73);

    public OilCompressorRecipeCategory(IGuiHelper guiHelper) {
        IDrawableStatic compressorDrawable = guiHelper.createDrawable(backgroundLocation, 111, 0, 19, 22);
        compressor = guiHelper.createAnimatedDrawable(compressorDrawable, 200, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Nonnull
    @Override
    public String getUid() {
        return ModInfo.ID + ":oil_compressor";
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.translateToLocal("jei.oil_craft.recipe.oil_compressor");
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft) {
        compressor.draw(minecraft, 22, 28);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull OilCompressorRecipeJEI recipeWrapper) {
        recipeLayout.getItemStacks().init(0, true, 47, 29);
        recipeLayout.getFluidStacks().init(0, false, 73, 8, 16, 58, 10000, false, null);
        recipeLayout.getItemStacks().setFromRecipe(0, recipeWrapper.getInputs());
        recipeLayout.getFluidStacks().set(0, recipeWrapper.getFluidOutputs());
    }
}
