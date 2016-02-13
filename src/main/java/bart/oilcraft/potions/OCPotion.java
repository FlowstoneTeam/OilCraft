package bart.oilcraft.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Bart on 13/02/2016.
 */
public class OCPotion extends Potion {
    private static final ResourceLocation resource = new ResourceLocation("oilcraft:textures/gui/potions.png");

    public OCPotion(String name, boolean badEffect, int color, int iconIndex) {
        super(new ResourceLocation("oilcraft:" + name), badEffect, color);
        setPotionName("oilcraft.potion." + name);
        setIconIndex(iconIndex % 8, iconIndex / 8);
        getLiquidColor(color);
    }

    static int findFreeID() {
        for (int i = 0; i < potionTypes.length; i++)
            if (potionTypes[i] == null)
                return i;

        return -1;
    }

    public int getLiquidColor(int liquidColour) {
        return liquidColour;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);

        return super.getStatusIconIndex();
    }

}