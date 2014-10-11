package bart.oilcraft.core.proxy;

import bart.oilcraft.blocks.OilInfuser;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilInfuserEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(OilCompressorEntity.class, "OilCompressorEntity");
        GameRegistry.registerTileEntity(OilInfuserEntity.class, "OilInfuserEntity");
    }
}