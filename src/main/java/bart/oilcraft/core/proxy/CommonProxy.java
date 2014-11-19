package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.EntityOilBoss;
import bart.oilcraft.entities.entitytrowable.EntityOilBall;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import bart.oilcraft.tileentities.TileEntitySummonTable;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(OilCompressorEntity.class, "OilCompressorEntity");
        GameRegistry.registerTileEntity(OilGeneratorEntity.class, "OilGeneratorEntity");
        GameRegistry.registerTileEntity(TileEntityOilFurnace.class, "TileEntityOilFurnace");
        GameRegistry.registerTileEntity(TileEntitySummonTable.class, "TileEntitySummonTable");
    }
    public void registerRenderInformation(){
        EntityRegistry.registerGlobalEntityID(EntityGooBall.class, "GooBall", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x000050);
        EntityRegistry.registerGlobalEntityID(EntityOilBall.class, "OilBall", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityOilBoss.class, "OilBoss", EntityRegistry.findGlobalUniqueEntityId());
    }
}