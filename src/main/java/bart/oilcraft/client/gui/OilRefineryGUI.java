package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilRefinery;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityOilRefinery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 9-7-2015.
 */
public class OilRefineryGUI extends GuiContainer {
    public static final ResourceLocation gui = new ResourceLocation(References.MODID, "textures/gui/oilrefinery.png");
    private TileEntityOilRefinery te;

    public OilRefineryGUI(EntityPlayer player, TileEntityOilRefinery te) {
        super(new ContainerOilRefinery(player, te));
        this.te = te;
    }

    public int getScaled(int tankNumber) {
        if (te.getTank(tankNumber).getCapacity() <= 0)
            return 58;
        return te.getTank(tankNumber).getFluidAmount() * 58 / te.getTank(tankNumber).getCapacity();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.oil.oilrefinery"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.oil.furnace")) / 2, 2, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 4, 0xffffff);
        drawFluid(44, 13 + 58 - getScaled(7), FluidRegistry.getFluid("oil"), 16, getScaled(0));
        drawFluid(62, 13 + 58 - getScaled(7), FluidRegistry.getFluid("residue"), 16, getScaled(1));
        drawFluid(80, 13 + 58 - getScaled(7), FluidRegistry.getFluid("fuel"), 16, getScaled(2));
        drawFluid(98, 13 + 58 - getScaled(7), FluidRegistry.getFluid("diesel"), 16, getScaled(3));
        drawFluid(116, 13 + 58 - getScaled(7), FluidRegistry.getFluid("kerosene"), 16, getScaled(4));
        drawFluid(134, 13 + 58 - getScaled(7), FluidRegistry.getFluid("petrol"), 16, getScaled(5));
        drawFluid(8, 13 + 58 - getScaled(7), FluidRegistry.getFluid("gas"), 16, getScaled(6));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize + 19, ySize);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
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
        Tes.addVertexWithUV(x , y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        Tes.addVertexWithUV(x + width, y , this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        Tes.addVertexWithUV(x , y , this.zLevel, minU, minV);
        Tes.draw();
    }
}
