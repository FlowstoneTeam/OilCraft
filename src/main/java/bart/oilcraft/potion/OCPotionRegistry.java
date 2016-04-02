package bart.oilcraft.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * Created by Bart on 13/02/2016.
 */
public class OCPotionRegistry {
    public static Potion slippery;

    public static void init() {
        slippery = new PotionSlippery();
        registerPotion(new ResourceLocation("oilcraft:slippery"), slippery);
    }

    private static void registerPotion(ResourceLocation location, Potion potion) {
        Potion.potionRegistry.register(Potion.potionRegistry.getKeys().size(), location, potion);
    }

}
