package bart.oilcraft.util;

import bart.oilcraft.recipes.OilCompressorRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Bart on 28-9-2014.
 */
public class OilCompressorRegistry {

    public static String[] buffer;

    public static void processBuffer() {

        for (String aBuffer : buffer) {
            String[] split = aBuffer.split(":");
            if (split.length == 5 || split.length == 6) {
                int outputV, energyV, timeV, meta;
                String modid, name;

                outputV = Integer.parseInt(split[0]);
                energyV = Integer.parseInt(split[1]);
                timeV = Integer.parseInt(split[2]);

                modid = split[3];
                name = split[4];

                meta = (split.length == 6 ? Integer.parseInt(split[5]) : 0);

                if (outputV > 0 && energyV > 0 && timeV > 0 && GameRegistry.findItem(modid, name) != null && meta >= 0) {
                    Item item = GameRegistry.findItem(modid, name);

                    new OilCompressorRecipe(item, outputV, timeV, energyV, meta);
                }
            }
        }
    }

}
