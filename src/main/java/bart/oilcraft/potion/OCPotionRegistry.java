package bart.oilcraft.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;

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
        GameData.getPotionRegistry().register(GameData.getPotionRegistry().getKeys().size(), location, potion);
    }
}
