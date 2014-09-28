package bart.oilcraft.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Bart on 28-9-2014.
 */
public class OilCompressorRegistry {

    public static String[] buffer;
    public static ItemStack[] allowedItems;
    public static int[] output;
    public static int[] energy;
    public static int[] time;

    public static void processBuffer()
    {
        allowedItems = new ItemStack[buffer.length];
        output = new int[buffer.length];
        energy = new int[buffer.length];
        time = new int[buffer.length];

        for (int i = 0; i < buffer.length; i++)
        {
            String[] split = buffer[i].split(":");
            if (split.length == 5 || split.length == 6)
            {
                int outputV, energyV, timeV, meta;
                String modid, name;

                outputV = Integer.parseInt(split[0]);
                energyV = Integer.parseInt(split[1]);
                timeV = Integer.parseInt(split[2]);

                modid = split[3];
                name = split[4];

                meta = (split.length == 6 ? Integer.parseInt(split[5]) : 0);

                if (outputV > 0 && energyV > 0 && timeV > 0 && GameRegistry.findItem(modid, name) != null && meta >= 0)
                {
                    Item temp = GameRegistry.findItem(modid, name);
                    ItemStack stack = new ItemStack(temp, 1, meta);

                    allowedItems[i] = stack;
                    output[i] = outputV;
                    energy[i] = energyV;
                    time[i] = timeV;
                }
            }
        }
    }

    public static int getItemIndex(ItemStack item)
    {
        for (int i = 0; i < allowedItems.length; i++)
            if (allowedItems[i] != null && allowedItems[i].getItem() == item.getItem() && allowedItems[i].getItemDamage() == item.getItemDamage()) return i;

        return -1;
    }
}
