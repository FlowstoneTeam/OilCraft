package bart.oilcraft.potions;

import net.minecraft.potion.Potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Bart on 13/02/2016.
 */
public class OCPotionRegistry {
    public static Potion slippery;

    public static void init() {
        if (Potion.potionTypes.length < 256)
            extendPotionArray();
        slippery = new PotionSlippery();
        System.out.println("slippery id: " + slippery.getId());
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
                e.printStackTrace();
            }
        }
    }
}
