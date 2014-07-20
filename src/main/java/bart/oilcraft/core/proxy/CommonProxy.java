package bart.oilcraft.core.proxy;

import bart.oilcraft.tileentities.OilCompressorEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(OilCompressorEntity.class, "OilCompressorEntity");
    }
}