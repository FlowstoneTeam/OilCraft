package bart.oilcraft.entity;

import bart.oilcraft.OilCraftMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Bart on 14/02/2016.
 */
public class OCEntityRegistry {
    public static void init() {
        EntityRegistry.registerModEntity(EntityGooBall.class, "gooBall", getUniqueEntityId(), OilCraftMain.instance, 80, 3, true, 0x212121, 0x141414);

    }

    public static int getUniqueEntityId() {
        int startEntityId = 400;
        do {
            ++startEntityId;
        } while (EntityList.getClassFromID(startEntityId) != null);
        return startEntityId;
    }
}
