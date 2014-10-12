package bart.oilcraft.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Bart on 20-7-2014.
 */
public class ModItems {
    public static Item EnergyExceptor;
    public static Item OilBucket;

    public static void init() {
        EnergyExceptor = new EnergyAcceptor();
        GameRegistry.registerItem(EnergyExceptor, "EnergyExceptor");
    }
}
