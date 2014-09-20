package bart.oilcraft.enchants;

import bart.oilcraft.lib.handler.ConfigurationHandler;

/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantRegistry {
    public static EnchantSlippery SlipperyEnchant;

    public static void registerEnchants() {
        SlipperyEnchant = new EnchantSlippery(ConfigurationHandler.slipperyEnchant, 0);
    }

}
