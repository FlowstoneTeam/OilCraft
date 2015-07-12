package bart.oilcraft.lib.handler;

import bart.oilcraft.fluids.OilCraftFluidRegistry;
import bart.oilcraft.items.OilCraftItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Created by Bart on 7-11-2014.
 */
public class ThaumcraftHandler extends ThaumcraftApi {
    public static void ThaumcraftAspectHandler() {
        //aspect to thaumcraft
        registerObjectTag(new ItemStack(OilCraftItemRegistry.oilBucket), (new AspectList()).add(Aspect.VOID, 1).add(Aspect.METAL, 8).add(Aspect.DARKNESS, 2).add(Aspect.WATER, 4).add(Aspect.DEATH, 3));
        registerObjectTag(new ItemStack(OilCraftItemRegistry.oilBall), (new AspectList()).add(Aspect.DARKNESS, 1).add(Aspect.SLIME, 1).add(Aspect.WATER, 3).add(Aspect.DEATH, 1));
        for (ItemStack stack : OreDictionary.getOres("oreShaleOil"))
            registerObjectTag(new ItemStack(stack.getItem()), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.DARKNESS, 2).add(Aspect.DEATH, 3));
        registerObjectTag(new ItemStack(OilCraftFluidRegistry.oilBlock), (new AspectList()).add(Aspect.DARKNESS, 2).add(Aspect.WATER, 4).add(Aspect.DEATH, 3));
    }

}
