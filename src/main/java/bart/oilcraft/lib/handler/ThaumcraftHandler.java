package bart.oilcraft.lib.handler;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Created by Bart on 7-11-2014.
 */
public class ThaumcraftHandler extends ThaumcraftApi {
    public static void ThaumcraftAspectHandler(){
        //aspect to thaumcraft
        registerObjectTag(new ItemStack(ModItems.OilBucket), (new AspectList()).add(Aspect.VOID, 1).add(Aspect.METAL, 8).add(Aspect.DARKNESS, 2).add(Aspect.WATER, 4).add(Aspect.DEATH, 3));
        registerObjectTag(new ItemStack(ModItems.OilBall), (new AspectList()).add(Aspect.DARKNESS, 1).add(Aspect.SLIME, 1).add(Aspect.WATER, 3).add(Aspect.DEATH, 1));
        registerObjectTag(new ItemStack(ModBlocks.ShaleOilOre), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.DARKNESS, 2).add(Aspect.DEATH, 3));
        registerObjectTag(new ItemStack(ModFluids.OilBlock), (new AspectList()).add(Aspect.DARKNESS, 2).add(Aspect.WATER, 4).add(Aspect.DEATH, 3));
    }

}
