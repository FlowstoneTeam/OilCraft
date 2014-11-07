package bart.oilcraft.entities.entitytrowable.oilball;

import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Bart on 6-11-2014.
 */
@SideOnly(Side.CLIENT)
public class RendererOilBall extends RenderSnowball {
    private static final ResourceLocation ballTextures = new ResourceLocation(References.MODID, "textures/items/oilball.png");

    public RendererOilBall(Item par1) {
        super(par1, 1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return ballTextures;
    }
}
