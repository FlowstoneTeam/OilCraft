package imdutch21.oilcraft.compat.jei.oilcompressor;

import imdutch21.oilcraft.lib.ModInfo;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class OilCompressorRecipeHandler implements IRecipeHandler<OilCompressorRecipeJEI> {
    @Nonnull
    @Override
    public Class<OilCompressorRecipeJEI> getRecipeClass() {
        return OilCompressorRecipeJEI.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return ModInfo.ID + ":oil_compressor";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull OilCompressorRecipeJEI recipe) {
        return ModInfo.ID + ":oil_compressor";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull OilCompressorRecipeJEI recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull OilCompressorRecipeJEI recipe) {
        return recipe.getFluidOutputs().size() > 0 && recipe.getTime() > 0 && recipe.getEnergyAmount() > 0 && recipe.getInputs().size() > 0;
    }
}
