package bart.oilcraft.item;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.fluids.OCFluidRegistry;
import bart.oilcraft.lib.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bart on 14/02/2016.
 */
public class OCItemRegistry {
    public static final List<Item> ITEMS = new LinkedList<Item>();



    public static void init() {
        registerItems();
    }


    private static void registerItems() {
        try {
            for (Field field : OCItemRegistry.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Item) {
                    Item item = (Item) obj;
                    String name = field.getName().toLowerCase(Locale.ENGLISH);
                    registerItem(item, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerItem(Item item, String name) {
        String itemName = name.toLowerCase(Locale.ENGLISH);
        GameRegistry.register(item.setRegistryName(ModInfo.ID, itemName).setUnlocalizedName(ModInfo.NAME_PREFIX + itemName));
        ITEMS.add(item);
        if (!I18n.canTranslate(item.getUnlocalizedName() + ".name")) {
            OilCraftMain.unlocalizedNames.add(item.getUnlocalizedName() + ".name");
        }
    }
}
