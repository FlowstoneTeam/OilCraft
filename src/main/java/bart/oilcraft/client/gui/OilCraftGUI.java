package bart.oilcraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 11-7-2015.
 */
public class OilCraftGUI extends GuiContainer {
    public OilCraftGUI(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

    }


    public void drawFluid(int x, int y, Fluid fluid, int width, int height) {
        if (fluid == null)
            return;
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/atlas/blocks.png"));
        int color = fluid.getColor();
        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
        drawTiledTexture(x, y, fluid.getIcon(), width, height);
    }


    public void drawTiledTexture(int x, int y, IIcon icon, int width, int height) {
        for (int I = 0; I < width; I += 16) {
            for (int V = 0; V < height; V += 16) {
                int W = Math.min(width - I, 16);
                int H = Math.min(height - V, 16);
                drawScaledTexturedModelRectFromIcon(x + I, y + V, icon, W, H);
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {
        if (icon == null)
            return;
        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator Tes = Tessellator.instance;
        Tes.startDrawingQuads();
        Tes.addVertexWithUV(x, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        Tes.addVertexWithUV(x, y, this.zLevel, minU, minV);
        Tes.draw();
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH) {
        float texU = 1 / texW;
        float texV = 1 / texH;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0, (u) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y + height, 0, (u + width) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y, 0, (u + width) * texU, (v) * texV);
        tessellator.addVertexWithUV(x, y, 0, (u) * texU, (v) * texV);
        tessellator.draw();
    }
}
