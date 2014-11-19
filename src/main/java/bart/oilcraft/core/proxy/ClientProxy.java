package bart.oilcraft.core.proxy;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.entities.EntityOilBoss;
import bart.oilcraft.entities.entitytrowable.EntityOilBall;
import bart.oilcraft.entities.entitytrowable.oilball.RendererOilBall;
import bart.oilcraft.entities.gooball.ModelGooBall;
import bart.oilcraft.entities.gooball.RendererGooBall;
import bart.oilcraft.items.ModItems;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public void registerRenderInformation(){
        super.registerRenderInformation();
        RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RendererGooBall(new ModelGooBall(), new ModelGooBall(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityOilBall.class, new RendererOilBall(ModItems.OilBall));
        RenderingRegistry.registerEntityRenderingHandler(EntityOilBoss.class, new RendererGooBall(new ModelGooBall(), new ModelGooBall(), 0.5F));
    }
}