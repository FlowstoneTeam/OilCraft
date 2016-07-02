package imdutch21.oilcraft.client.render;

import imdutch21.oilcraft.client.model.ModelEntityGooBall;
import imdutch21.oilcraft.entity.EntityGooBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by Bart on 14/02/2016.
 */
public class RenderFactoryEntityGooBall implements IRenderFactory<EntityGooBall> {
    @Override
    public Render<? super EntityGooBall> createRenderFor(RenderManager manager) {
        return new RendererEntityGooBall(manager, new ModelEntityGooBall(), 0.5f);
    }
}
