package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerSlipperyRemover;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntitySlipperyRemover;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 29-10-2014.
 */
public class SlipperyRemoverGUI extends GuiContainer {
    public static final ResourceLocation gui = new ResourceLocation(References.MODID, "textures/gui/slipperyremover.png");
    @SuppressWarnings("unused")
    private ContainerSlipperyRemover container;
    private TileEntitySlipperyRemover te;



    public SlipperyRemoverGUI(EntityPlayer player, TileEntitySlipperyRemover tile) {
        super(new ContainerSlipperyRemover(player, tile));
        this.container = (ContainerSlipperyRemover) this.inventorySlots;
        this.te = tile;
    }



    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        fontRendererObj.drawString(StatCollector.translateToLocal("gui.slippery.remover"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.slippery.remover")) / 2, 2, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 4, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize + 19, ySize);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/energybar.png"));
        int w = 71;
        int p = (int)((double) te.energy.getEnergyStored()*(double)w/te.energy.getMaxEnergyStored());

        this.drawTexturedModalRect(xStart+10, yStart+7+w-p, 16, w-p, 16, p, 256, 256);
    }


    public void drawTiledTexture(int x, int y, IIcon icon, int width, int height){
        for (int I = 0; I < width; I += 16){
            for (int V = 0; V < height; V += 16) {
                int W = Math.min(width - I, 16);
                int H = Math.min(height - V, 16);
                drawScaledTexturedModelRectFromIcon(x + I, y + V, icon, W, H);
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height){
        if (icon == null )
            return;
        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator Tes = Tessellator.instance;
        Tes.startDrawingQuads();
        Tes.addVertexWithUV(x + 0, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y + 0, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        Tes.addVertexWithUV(x + 0, y + 0, this.zLevel, minU, minV);
        Tes.draw();
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH)
    {
        float texU = 1 / texW;
        float texV = 1 / texH;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, 0, (u + 0) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y + height, 0, (u + width) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y + 0, 0, (u + width) * texU, (v + 0) * texV);
        tessellator.addVertexWithUV(x + 0, y + 0, 0, (u + 0) * texU, (v + 0) * texV);
        tessellator.draw();
    }
}
