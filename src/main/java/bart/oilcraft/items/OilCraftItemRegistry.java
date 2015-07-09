package bart.oilcraft.items;

import bart.oilcraft.fluids.ModFluids;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bart on 20-7-2014.
 */
public class OilCraftItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static Item energyAcceptor = new ItemEnergyAcceptor();
    public static Item oilBucket = new ItemOilBucket(ModFluids.OilBlock);
    public static ItemOilBall oilBall = new ItemOilBall();
    public static Item energyDistributeUpgrade = new ItemEnergyDistributeUpgrade();
    public static Item advancedKnowledge = new ItemAdvancedKnowledge();
    public static Item note = new ItemNote();

    public static void init() {
        registerItems();
    }

    private static void registerItems() {
        try {
            for (Field f : OilCraftItemRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) registerItem((Item) obj);
                else if (obj instanceof Item[])
                    for (Item item : (Item[]) obj)
                        registerItem(item);
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
    }
}
