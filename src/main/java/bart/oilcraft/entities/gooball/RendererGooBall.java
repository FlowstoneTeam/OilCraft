package bart.oilcraft.entities.gooball;

import bart.oilcraft.entities.EntityGooBall;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 27-10-2014.
 */
@SideOnly(Side.CLIENT)
public class RendererGooBall extends RenderLiving {
    private static final ResourceLocation slimeTextures = new ResourceLocation(References.MODID, "textures/mobs/gooball.png");

    public RendererGooBall(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return slimeTextures;
    }

    protected void scaleSlime(EntityGooBall par1EntitySlime, float par2) {
        float f1 = (float) par1EntitySlime.getSlimeSize();
        float f2 = (par1EntitySlime.prevSquishFactor + (par1EntitySlime.squishFactor - par1EntitySlime.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

}
