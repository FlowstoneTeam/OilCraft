package bart.oilcraft.inventory.gui;

import bart.oilcraft.inventory.container.ContainerOilGenerator;
import bart.oilcraft.network.DirectionButtonPacketProcessor;
import bart.oilcraft.network.OCPacketHandler;
import bart.oilcraft.tileentity.TileEntityOilGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiOilGenerator extends OCGui {
    private static ResourceLocation oilGeneratorGui = new ResourceLocation("oilcraft:textures/gui/oilGenerator.png");
    private TileEntityOilGenerator tileOilGenerator;
    private ContainerOilGenerator containerOilGenerator;
    private EntityPlayer player;
    private int tick = 0;
    private int frame = 0;

    private GuiButton upButton, downButton, northButton, eastButton, southButton, westButton;

    public GuiOilGenerator(EntityPlayer player, TileEntityOilGenerator tile) {
        super(new ContainerOilGenerator(player, tile));
        tileOilGenerator = tile;
        containerOilGenerator = (ContainerOilGenerator) this.inventorySlots;
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonList.add(this.upButton = new GuiButton(EnumFacing.UP.getIndex(), k + 58, l + 13, 18, 16, "U"));
        buttonList.add(this.downButton = new GuiButton(EnumFacing.DOWN.getIndex(), k + 94, l + 13, 18, 16, "D"));
        buttonList.add(this.northButton = new GuiButton(EnumFacing.NORTH.getIndex(), k + 76, l + 29, 18, 16, "N"));
        buttonList.add(this.eastButton = new GuiButton(EnumFacing.EAST.getIndex(), k + 94, l + 45, 18, 16, "E"));
        buttonList.add(this.southButton = new GuiButton(EnumFacing.SOUTH.getIndex(), k + 76, l + 61, 18, 16, "S"));
        buttonList.add(this.westButton = new GuiButton(EnumFacing.WEST.getIndex(), k + 58, l + 45, 18, 16, "W"));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(I18n.translateToLocal("container.oilGenerator"), this.xSize / 2 - this.fontRendererObj.getStringWidth(I18n.translateToLocal("container.oilGenerator")) / 2, 4, 4210752);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int amount = getScaled(tileOilGenerator.tank.getCapacity(), tileOilGenerator.tank.getFluidAmount(), 58);
        if (tileOilGenerator.tank.getFluid() != null) {
            mc.getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/blocks/oilStill.png"));
            drawScaledCustomSizeModalRect(143, 72 - amount, 0, frame % 200, 8, amount, 16, amount, 16, 320);
        }
        tick++;
        if (tick % 10 == 0)
            frame += 8;

        amount = getScaled(tileOilGenerator.energyStorage.getMaxEnergyStored(), tileOilGenerator.energyStorage.getEnergyStored(), 71);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/gui/energybar.png"));
        drawModalRectWithCustomSizedTexture(10, 78 - amount, 16, 71 - amount, 16, amount, 16, 71);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        if (mouseX >= 143 + k && mouseX <= 159 + k && mouseY >= 14 + l && mouseY <= 72 + l) {
            List<String> list = new ArrayList<String>();
            list.add("Fluid: Oil");
            list.add(String.format("Tank: %s mB / %s mB", tileOilGenerator.tank.getFluidAmount(), tileOilGenerator.tank.getCapacity()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }

        if (mouseX >= 10 + k && mouseX <= 26 + k && mouseY >= 7 + l && mouseY <= 78 + l) {
            List<String> list = new ArrayList<String>();
            list.add(String.format("Energie: %s RF / %s RF", tileOilGenerator.energyStorage.getEnergyStored(), tileOilGenerator.energyStorage.getMaxEnergyStored()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }


        for (GuiButton button : buttonList) {
            if (mouseX >= button.xPosition && mouseX <= button.xPosition + button.width && mouseY >= button.yPosition && mouseY <= button.yPosition + button.height) {
                List<String> list = new ArrayList<String>();
                list.add(String.format("Active: %s", tileOilGenerator.outputEnabled(button.id)));
                drawHoveringText(list, mouseX - k, mouseY - l);
            }
        }
    }


    private int getScaled(int whole, int part, int height) {
        if (whole <= 0)
            return height;
        return part * height / whole;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(oilGeneratorGui);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id > -1 && button.id < 7)
                OCPacketHandler.INSTANCE.sendToServer(new DirectionButtonPacketProcessor(button.id, tileOilGenerator.getPos(), player.dimension));

        }
    }


}
