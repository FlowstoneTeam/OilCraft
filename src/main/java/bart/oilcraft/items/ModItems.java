package bart.oilcraft.items;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.fluids.ModFluids;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Bart on 20-7-2014.
 */
public class ModItems {
    public static Item EnergyConverter;
    public static Item OilBucket;

    public static void Init() {
        OilBucket = new OilBucket(ModFluids.OilBlock);
        GameRegistry.registerItem(OilBucket, OilBucket.getUnlocalizedName());

        EnergyConverter = new EnergyConverter();
        GameRegistry.registerItem(EnergyConverter, EnergyConverter.getUnlocalizedName());
    }
}
