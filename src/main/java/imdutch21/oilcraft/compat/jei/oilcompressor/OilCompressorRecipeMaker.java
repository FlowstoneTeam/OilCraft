package imdutch21.oilcraft.compat.jei.oilcompressor;

import imdutch21.oilcraft.recipe.OilCompressorRecipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class OilCompressorRecipeMaker {
    @Nonnull
    public static List<OilCompressorRecipeJEI> getRecipes(){
        ArrayList<OilCompressorRecipeJEI> recipes = new ArrayList<OilCompressorRecipeJEI>();
        for (OilCompressorRecipe recipe:OilCompressorRecipe.oilCompressorRecipes)
            recipes.add(new OilCompressorRecipeJEI(recipe));
        return recipes;
    }
}
