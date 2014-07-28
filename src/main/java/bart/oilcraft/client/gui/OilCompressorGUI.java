package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilCompressor;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilCompressorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
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
}