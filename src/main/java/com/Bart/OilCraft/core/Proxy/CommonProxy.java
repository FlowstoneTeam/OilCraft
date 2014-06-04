package com.bart.oilcraft.core.proxy;

import com.bart.oilcraft.tileentities.OilCompressorEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public class CommonProxy {
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(OilCompressorEntity.class, "OilCompressorEntity");
    }

}
