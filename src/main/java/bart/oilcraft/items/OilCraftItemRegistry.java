package bart.oilcraft.items;

import bart.oilcraft.fluids.OilCraftFluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bart on 20-7-2014.
 */
public class OilCraftItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static Item energyAcceptor = new ItemEnergyAcceptor();
    public static Item oilBucket = new ItemOilBucket(OilCraftFluidRegistry.oilBlock);
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
                if (obj instanceof Item)
                    registerOredictItem((Item) obj);
                else if (obj instanceof Item[])
                    for (Item item : (Item[]) obj)
                        registerOredictItem(item);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerOredictItem(Item item) {
        if (item instanceof ItemOredict) {
            if (OreDictionary.getOres(((ItemOredict) item).getOreDictName()).size() > 0)
                return;
            registerItem(item);
            OreDictionary.registerOre(((ItemOredict) item).getOreDictName(), item);
        } else
            registerItem(item);
    }

    private static void registerItem(Item item) {
        ITEMS.add(item);
        String name = item.getUnlocalizedName();
        String[] strings = name.split("\\.");
        GameRegistry.registerItem(item, strings[strings.length - 1]);
    }
}
