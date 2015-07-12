package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilRefinery;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityOilRefinery;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 9-7-2015.
 */
public class OilRefineryGUI extends OilCraftGUI {
    public static final ResourceLocation gui = new ResourceLocation(References.MODID, "textures/gui/oilrefinery.png");
    private TileEntityOilRefinery te;

    public OilRefineryGUI(EntityPlayer player, TileEntityOilRefinery te) {
        super(new ContainerOilRefinery(player, te));
        this.te = te;
    }

    public int getScaled(int tankNumber) {
        if (te.getFluidStack(tankNumber).amount <= 0)
            return 0;
        return te.getFluidStack(tankNumber).amount * 58 / te.maxFluid;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.oil.oilrefinery"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.oil.furnace")) / 2, 2, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 4, 0xffffff);
        drawFluid(44, 13 + 58 - getScaled(0), te.getFluidStack(0).getFluid(), 16, getScaled(0));
        drawFluid(62, 13 + 58 - getScaled(1), te.getFluidStack(1).getFluid(), 16, getScaled(1));
        drawFluid(80, 13 + 58 - getScaled(2), te.getFluidStack(2).getFluid(), 16, getScaled(2));
        drawFluid(98, 13 + 58 - getScaled(3), te.getFluidStack(3).getFluid(), 16, getScaled(3));
        drawFluid(116, 13 + 58 - getScaled(4), te.getFluidStack(4).getFluid(), 16, getScaled(4));
        drawFluid(134, 13 + 58 - getScaled(5), te.getFluidStack(5).getFluid(), 16, getScaled(5));
        drawFluid(8, 13 + 58 - getScaled(6), te.getFluidStack(6).getFluid(), 16, getScaled(6));
    }

    @Override
    public void drawFluid(int x, int y, Fluid fluid, int width, int height) {
        if (fluid == null)
            return;
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/atlas/blocks.png"));
        int color = fluid.getColor();
        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
        drawTiledTexture(x, y, fluid.getIcon(), width, height);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize + 19, ySize);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
