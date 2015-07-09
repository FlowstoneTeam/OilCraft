package bart.oilcraft.potions;

import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Bart on 6-11-2014.
 */
public class ModPotions extends Potion {
    private static final ResourceLocation resource = new ResourceLocation(References.RESOURCESPREFIX + "textures/gui/potions.png");
    public static Potion slippery;

    public ModPotions(String name, boolean badEffect, int color, int iconIndex) {
        super(findFreeID(), badEffect, color);
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

    public static void init() {
        if (Potion.potionTypes.length < 256)
            extendPotionArray();
        slippery = new PotionSlippery();
    }

    public static void extendPotionArray() {
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("OilCraft did something wrong but if you ask me it was totally not my fault:");
                System.err.println(e);
            }
        }
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
