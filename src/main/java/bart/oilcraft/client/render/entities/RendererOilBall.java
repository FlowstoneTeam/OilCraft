package bart.oilcraft.client.render.entities;

import bart.oilcraft.items.OilCraftItemRegistry;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by Bart on 6-11-2014.
 */
@SideOnly(Side.CLIENT)
public class RendererOilBall extends RenderSnowball {
    private static final ResourceLocation ballTextures = new ResourceLocation(References.MODID, "textures/items/oilball.png");

    public RendererOilBall() {
        super(OilCraftItemRegistry.oilBall, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return ballTextures;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        IIcon iicon = OilCraftItemRegistry.oilBall.icon;

        if (iicon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(entity);
            Tessellator tessellator = Tessellator.instance;

            if (iicon == ItemPotion.func_94589_d("bottle_splash")) {
                int i = PotionHelper.func_77915_a(((EntityPotion) entity).getPotionDamage(), false);
                float f2 = (float) (i >> 16 & 255) / 255.0F;
                float f3 = (float) (i >> 8 & 255) / 255.0F;
                float f4 = (float) (i & 255) / 255.0F;
                GL11.glColor3f(f2, f3, f4);
                GL11.glPushMatrix();
                this.func_77026_a(tessellator, ItemPotion.func_94589_d("overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }

            this.func_77026_a(tessellator, iicon);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }

    private void func_77026_a(Tessellator tessellator, IIcon icon) {
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
        tessellator.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
        tessellator.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
        tessellator.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
        tessellator.draw();
    }
}
