package bart.oilcraft.client.render;

import bart.oilcraft.client.model.ModelEntityGooBall;
import bart.oilcraft.entity.EntityGooBall;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Bart on 14/02/2016.
 */
public class RendererEntityGooBall extends RenderLiving<EntityGooBall> {
    private static final ResourceLocation slimeTextures = new ResourceLocation("oilcraft", "textures/mobs/gooball.png");

    public RendererEntityGooBall(RenderManager renderManager, ModelEntityGooBall model, float shadowSize) {
        super(renderManager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGooBall entity) {
        return slimeTextures;
    }
}