package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilCompressor;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilCompressorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 7-6-2014.
 */
public class OilCompressorGUI extends GuiContainer {
    public static final ResourceLocation gui = new ResourceLocation(References.MODID, "textures/gui/oilcompressor.png");
    @SuppressWarnings("unused")
    private ContainerOilCompressor container;
    private OilCompressorEntity te;

    public OilCompressorGUI(EntityPlayer player, OilCompressorEntity tile) {
        super(new ContainerOilCompressor(player, tile));
        this.container = (ContainerOilCompressor) this.inventorySlots;
        this.te = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.oil.compressor"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.oil.compressor")) / 2, 2, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 4, 0xffffff);
        int amount = getScaled();
        drawFluid(82, 14 + 58 - amount, ModFluids.Oil, 16, amount);
    }

    public int getScaled(){
        if(te.getTank().getCapacity() <= 0)
            return 58;
        return te.getTank().getFluidAmount()*58/te.getTank().getCapacity();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    public void drawFluid(int x, int y, Fluid fluid, int width, int height){
        if(fluid == null)
            return;
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/atlas/blocks.png"));
        int color = fluid.getColor();
        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

        drawTiledTexture(x, y, fluid.getIcon(), width, height);
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
}