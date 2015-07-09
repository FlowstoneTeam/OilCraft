package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.EntityOilBoss;
import bart.oilcraft.entities.EntityOilBall;
import bart.oilcraft.client.render.entities.RendererOilBall;
import bart.oilcraft.client.render.model.ModelGooBall;
import bart.oilcraft.client.render.entities.RendererGooBall;
import bart.oilcraft.items.OilCraftItemRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public void registerRenderInformation() {
        super.registerRenderInformation();
        RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RendererGooBall(new ModelGooBall(), new ModelGooBall(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityOilBall.class, new RendererOilBall());
        RenderingRegistry.registerEntityRenderingHandler(EntityOilBoss.class, new RendererGooBall(new ModelGooBall(), new ModelGooBall(), 0.5F));
    }
}