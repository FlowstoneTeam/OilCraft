package bart.oilcraft.util;

import bart.oilcraft.recipes.HeatedFurnaceRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by bart on 20-10-2014.
 */
public class OilFurnaceRegistry {

    public static String[] buffer;

    public static void processBuffer() {
        for (String aBuffer : buffer) {
            String[] split = aBuffer.split(":");
            if (split.length > 6 && split.length < 10) {
                int oilV, energyV, timeV, metaOut, metaIn;
                String modid1, input, modid2, output;
                // modid:input:oil:energy:time:modid:output[:metadata output][:metadata input]

                oilV = Integer.parseInt(split[2]);
                energyV = Integer.parseInt(split[3]);
                timeV = Integer.parseInt(split[4]);

                modid1 = split[0];
                input = split[1];

                modid2 = split[5];
                output = split[6];

                metaIn = (split.length == 9 ? Integer.parseInt(split[8]) : 0);
                metaOut = (split.length == 8 ? Integer.parseInt(split[7]) : 0);

                if (oilV > 0 && energyV > 0 && timeV > 0 && GameRegistry.findItem(modid1, output) != null && GameRegistry.findItem(modid2, input) != null && metaOut >= 0 && metaIn >= 0) {
                    Item item1 = GameRegistry.findItem(modid1, input);
                    Item item2 = GameRegistry.findItem(modid2, output);

                    new HeatedFurnaceRecipe(item1, item2, energyV, timeV, oilV, metaIn, metaOut);
                }
            }
        }
    }
}
