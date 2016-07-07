package imdutch21.oilcraft.compat;

import imdutch21.oilcraft.compat.jei.CompatibilityJEI;
import net.minecraftforge.fml.common.Loader;

public class CompatibilityRegistry {

    public static void init(){
        if (Loader.isModLoaded("JEI"))
            CompatibilityJEI.loadCompat();
    }
}
