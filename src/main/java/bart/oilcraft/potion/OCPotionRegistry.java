package bart.oilcraft.potion;

import bart.oilcraft.lib.ModInfo;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bart on 13/02/2016.
 */
public class OCPotionRegistry {
    private static final List<Potion> POTIONS = new ArrayList<Potion>();
    public static Potion SLIPPERY = new PotionSlippery();

    public static void init() {
        try {
            for (Field field : OCPotionRegistry.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Potion) {
                    Potion potion = (Potion) obj;
                    String name = field.getName().toLowerCase(Locale.ENGLISH);
                    registerPotion(name, potion);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerPotion(String name, Potion potion) {
        GameRegistry.register(potion.setRegistryName(ModInfo.ID, name).setPotionName(ModInfo.NAME_PREFIX + "potion." + name));
        POTIONS.add(potion);
    }
}
