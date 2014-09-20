package bart.oilcraft.enchants;

import bart.oilcraft.lib.handler.ConfigurationHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantRegistry {
    public static EnchantSlippery SlipperyEnchant;

    public static void registerEnchants() {
        SlipperyEnchant = new EnchantSlippery(ConfigurationHandler.slipperyEnchantID, 0);
        MinecraftForge.EVENT_BUS.register(new EnchantSlipperyHandler());
    }

}
