package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.GooBall.ModelGooBall;
import bart.oilcraft.entities.GooBall.RendererGooBall;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public void registerRenderInformation(){
        super.registerRenderInformation();
        RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RendererGooBall(new ModelGooBall(), new ModelGooBall(), 0.5F));

    }
}