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
        EntityRegistry.registerModEntity(EntityGooBall.class, "gooBall", getUniqueEntityId(), OilCraftMain.instance, 80, 3, true);

        registerEntityEgg(EntityGooBall.class, 0x212121, 0x141414);
    }

    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
        int id = getUniqueEntityId();
        EntityList.idToClassMapping.put(Integer.valueOf(id), entity);
        EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, primaryColor, secondaryColor));
    }

    public static int getUniqueEntityId() {
        int startEntityId = 400;
        do {
            ++startEntityId;
        } while (EntityList.getStringFromID(startEntityId) != null);
        return startEntityId;
    }
}
