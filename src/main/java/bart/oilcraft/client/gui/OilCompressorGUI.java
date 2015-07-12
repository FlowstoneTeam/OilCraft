package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilCompressor;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.TileEntityOilCompressor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bart on 7-6-2014.
 */
public class OilCompressorGUI extends OilCraftGUI {
    public static final ResourceLocation gui = new ResourceLocation(References.MODID, "textures/gui/oilcompressor.png");
    @SuppressWarnings("unused")
    private ContainerOilCompressor container;
    private TileEntityOilCompressor te;

    public OilCompressorGUI(EntityPlayer player, TileEntityOilCompressor tile) {
        super(new ContainerOilCompressor(player, tile));
        this.container = (ContainerOilCompressor) this.inventorySlots;
        this.te = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.oil.compressor"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.oil.compressor")) / 2, 2, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 4, 0xffffff);
        int amount = getScaled();
        if (te.getTank().getFluid() != null)
            drawFluid(82, 14 + 58 - amount, te.getTank().getFluid().getFluid(), 16, amount);
    }

    public int getScaled() {
        if (te.getTank().getCapacity() <= 0)
            return 58;
        return te.getTank().getFluidAmount() * 58 / te.getTank().getCapacity();
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
        int p = (int) ((double) te.energy.getEnergyStored() * (double) w / te.energy.getMaxEnergyStored());
        this.drawTexturedModalRect(xStart + 10, yStart + 7 + w - p, 16, w - p, 16, p, 256, 256);

    }


}