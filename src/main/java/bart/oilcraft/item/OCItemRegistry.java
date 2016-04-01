package bart.oilcraft.item;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.fluids.OCFluidRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bart on 14/02/2016.
 */
public class OCItemRegistry {
    public static final List<Item> ITEMS = new LinkedList<Item>();

    public static final OCItemBucket oilBucket = new OCItemBucket(OCFluidRegistry.oilBlock, "oil");

    public static void init() {
        registerItems();
    }


    private static void registerItems() {
        try {
            for (Field f : OCItemRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) registerItem((Item) obj);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerItem(Item item) {
        ITEMS.add(item);
        String name = item.getUnlocalizedName();
        String[] strings = name.split("\\.");
        GameRegistry.registerItem(item, strings[strings.length - 1]);
        if (!I18n.canTranslate(item.getUnlocalizedName() + ".name")) {
            OilCraftMain.unlocalizedNames.add(item.getUnlocalizedName() + ".name");
        }
    }
}
