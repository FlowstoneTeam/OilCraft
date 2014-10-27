package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import bart.oilcraft.tileentities.OilInfuserEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by bart on 2-6-2014.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(OilCompressorEntity.class, "OilCompressorEntity");
        GameRegistry.registerTileEntity(OilInfuserEntity.class, "OilInfuserEntity");
        GameRegistry.registerTileEntity(OilGeneratorEntity.class, "OilGeneratorEntity");
        GameRegistry.registerTileEntity(TileEntityOilFurnace.class, "TileEntityOilFurnace");
    }
    public void registerRenderInformation(){
        EntityRegistry.registerGlobalEntityID(EntityGooBall.class, "GooBall0", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x000050);
    }
}