package imdutch21.oilcraft.compat.jei.oilcompressor;

import imdutch21.oilcraft.fluids.OCFluidRegistry;
import imdutch21.oilcraft.recipe.OilCompressorRecipe;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OilCompressorRecipeJEI extends BlankRecipeWrapper {
    @Nonnull
    private final ItemStack input;
    private FluidStack output;
    private int energyAmount;
    private int time;

    public OilCompressorRecipeJEI(OilCompressorRecipe recipe) {
        input = recipe.input;
        output = new FluidStack(OCFluidRegistry.OIL, recipe.oilAmount);
        energyAmount = recipe.energyAmount;
        time = recipe.time;
    }

    @Nonnull
    @Override
    public List getInputs() {
        return Collections.singletonList(input);
    }

    @Nonnull
    @Override
    public List<FluidStack> getFluidOutputs() {
        return Collections.singletonList(output);
    }


    public int getEnergyAmount() {
        return energyAmount;
    }

    public int getTime() {
        return time;
    }


    @Nullable
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        ArrayList<String> toolTips = new ArrayList<String>();
        if (mouseX >= 22 && mouseX <= 40 && mouseY >= 28 && mouseY <= 49) {
            toolTips.add(I18n.translateToLocal("gui.time") + ": " + time + " " + I18n.translateToLocal("gui.ticks"));
            return toolTips;
        }

        if (mouseX >= 0 && mouseX <= 18 && mouseY >= 0 && mouseY <= 73) {
            toolTips.add(String.format("Energie: %s RF", energyAmount, 8000));
            return toolTips;
        }
        return null;
    }

    private int getScaled(int whole, int part, int height) {
        if (whole <= 0)
            return height;
        return part * height / whole;
    }
}
