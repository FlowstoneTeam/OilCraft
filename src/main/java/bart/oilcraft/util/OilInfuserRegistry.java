package bart.oilcraft.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 28-9-2014.
 */
public class OilInfuserRegistry {


    public static String[] buffer;
    public static ItemStack[] allowedItemsIn;
    public static ItemStack[] allowedItemsOut;
    public static int[] energy;
    public static int[] time;
    public static int[] oil;

    public static void processBuffer()
    {
        allowedItemsIn = new ItemStack[buffer.length];
        allowedItemsOut = new ItemStack[buffer.length];
        energy = new int[buffer.length];
        time = new int[buffer.length];
        oil = new int[buffer.length];

        for (int i = 0; i < buffer.length; i++)
        {
            String[] split = buffer[i].split(":");
            if (split.length > 7 && split.length < 10)
            {
                int oilV, energyV, timeV, metaOut, metaIn;
                String modid1, input, modid2, output;

                // modid:output:oil:energy:time:modid:input[:metadata output][:metadata input]

                oilV = Integer.parseInt(split[2]);
                energyV = Integer.parseInt(split[3]);
                timeV = Integer.parseInt(split[4]);

                modid1 = split[0];
                output = split[1];

                modid2 = split[5];
                input = split[6];

                metaIn = (split.length == 9 ? Integer.parseInt(split[8]) : 0);
                metaOut = (split.length == 8 ? Integer.parseInt(split[7]) : 0);

                if (oilV > 0 && energyV > 0 && timeV > 0 && GameRegistry.findItem(modid1, output) != null && GameRegistry.findItem(modid2, input) != null && metaOut >= 0 && metaIn >= 0)
                {
                    Item temp1 = GameRegistry.findItem(modid1, output);
                    ItemStack stack1 = new ItemStack(temp1, 1, metaOut);

                    Item temp2 = GameRegistry.findItem(modid2, input);
                    ItemStack stack2 = new ItemStack(temp2, 1, metaIn);


                    allowedItemsOut[i] = stack1;
                    allowedItemsIn[i] = stack2;

                    oil[i] = oilV;
                    energy[i] = energyV;
                    time[i] = timeV;
                }
            }
        }
    }
    public static int getItemIndex(ItemStack item)
    {
        for (int i = 0; i < allowedItemsIn.length; i++)
            //if (allowedItemsIn[i] != null && allowedItemsIn[i].getItem() == item.getItem() && allowedItemsIn[i].getItemDamage() == item.getItemDamage()) {
                if(allowedItemsIn[i] != null){
                    System.out.println("1");
                    if(allowedItemsIn[i].getItem() == item.getItem()){
                        System.out.println("2");
                        if(allowedItemsIn[i].getItemDamage() == item.getItemDamage()){
                            System.out.println("3");
                        }
                    }
                }
               // return i;
           // }
        return -1;
    }

}