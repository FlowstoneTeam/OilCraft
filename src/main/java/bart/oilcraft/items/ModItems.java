package bart.oilcraft.items;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.fluids.BlockOil;
import bart.oilcraft.fluids.ModFluids;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Bart on 20-7-2014.
 */
public class ModItems {
    public static Item EnergyAcceptor;
    public static Item OilBucket;
    public static Item OilBall;
    public static Item EnergyDistributeUpgrade;
    public static Item AdvancedKnowledge;

    public static void init() {
        EnergyAcceptor = new EnergyAcceptor();
        GameRegistry.registerItem(EnergyAcceptor, "EnergyAcceptor");
        OilBucket = new OilBucket(ModFluids.OilBlock);
        GameRegistry.registerItem(OilBucket, "OilBucket");
        OilBall = new OilBall();
        GameRegistry.registerItem(OilBall, "OilBall");
        EnergyDistributeUpgrade = new EnergyDistributeUpgrade();
        GameRegistry.registerItem(EnergyDistributeUpgrade, "EnergyDistributeUpgrade");
        AdvancedKnowledge = new ItemAdvancedKnowledge();
        GameRegistry.registerItem(AdvancedKnowledge, "ItemsAdvancedKnowledge");
    }
}
