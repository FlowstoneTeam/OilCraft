package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.EntityOilBoss;
import bart.oilcraft.entities.EntityOilBall;
import bart.oilcraft.tileentities.*;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityOilCompressor.class, "TileEntityOilCompressor");
        GameRegistry.registerTileEntity(TileEntityOilGenerator.class, "TileEntityOilGenerator");
        GameRegistry.registerTileEntity(TileEntityOilFurnace.class, "TileEntityOilFurnace");
        GameRegistry.registerTileEntity(TileEntitySummonTable.class, "TileEntitySummonTable");
        GameRegistry.registerTileEntity(TileEntityLiquidizer.class, "TileEntityLiquidizer");
    }

    public void registerRenderInformation() {
        EntityRegistry.registerGlobalEntityID(EntityGooBall.class, "GooBall", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x000050);
        EntityRegistry.registerGlobalEntityID(EntityOilBall.class, "ItemOilBall", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityOilBoss.class, "OilBoss", EntityRegistry.findGlobalUniqueEntityId());
    }
}