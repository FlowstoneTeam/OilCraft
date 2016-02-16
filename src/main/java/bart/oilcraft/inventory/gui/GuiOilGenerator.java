package bart.oilcraft.inventory.gui;

import bart.oilcraft.inventory.container.ContainerOilGenerator;
import bart.oilcraft.tileentity.TileEntityOilGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
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
        buttonList.add(new GuiButton(0, k + 30, l + 13, 36, 16, "Up"));
        buttonList.add(new GuiButton(1, k + 102, l + 13, 36, 16, "Down"));
        buttonList.add(new GuiButton(2, k + 66, l + 29, 36, 16, "North"));
        buttonList.add(new GuiButton(3, k + 102, l + 45, 36, 16, "East"));
        buttonList.add(new GuiButton(4, k + 66, l + 61, 36, 16, "South"));
        buttonList.add(new GuiButton(5, k + 30, l + 45, 36, 16, "West"));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.oilGenerator"), this.xSize / 2 - this.fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.oilGenerator")) / 2, 4, 4210752);
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
            List<String> list = new ArrayList<>();
            list.add("Fluid: Oil");
            list.add(String.format("Tank: %s mB / %s mB", tileOilGenerator.tank.getFluidAmount(), tileOilGenerator.tank.getCapacity()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }

        if (mouseX >= 10 + k && mouseX <= 26 + k && mouseY >= 7 + l && mouseY <= 78 + l) {
            List<String> list = new ArrayList<>();
            list.add(String.format("Energie: %s RF / %s RF", tileOilGenerator.energyStorage.getEnergyStored(), tileOilGenerator.energyStorage.getMaxEnergyStored()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }


        for (GuiButton button : buttonList) {
            if (mouseX >= button.xPosition && mouseX <= button.xPosition + button.width && mouseY >= button.yPosition && mouseY <= button.yPosition + button.height) {
                List<String> list = new ArrayList<>();
                list.add(String.format("Active: %s", active(button.id)));
                drawHoveringText(list, mouseX - k, mouseY - l);
            }
        }
    }


    private boolean active(int id) {
        switch (id) {
            case 0:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.UP.getIndex());
            case 1:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.DOWN.getIndex());
            case 2:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.NORTH.getIndex());
            case 3:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.EAST.getIndex());
            case 4:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.SOUTH.getIndex());
            case 5:
                return tileOilGenerator.energyOutputs.contains(EnumFacing.WEST.getIndex());
        }
        return false;
    }


    private String getOutputs() {
        String s = "";
        for (int i : tileOilGenerator.energyOutputs) {
            switch (i) {
                case 0:
                    s += "Down, ";
                    break;
                case 1:
                    s += "Up, ";
                    break;
                case 2:
                    s += "North, ";
                    break;
                case 3:
                    s += "South, ";
                    break;
                case 4:
                    s += "West, ";
                    break;
                case 5:
                    s += "East, ";
                    break;
            }
        }
        return s.substring(0, s.length() > 1 ? s.length() - 2 : 0);
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
        super.actionPerformed(button);
        int packet = -1;
        switch (button.id) {
            case 0:
                packet = EnumFacing.UP.getIndex();
                break;
            case 1:
                packet = EnumFacing.DOWN.getIndex();
                break;
            case 2:
                packet = EnumFacing.NORTH.getIndex();
                break;
            case 3:
                packet = EnumFacing.EAST.getIndex();
                break;
            case 4:
                packet = EnumFacing.SOUTH.getIndex();
                break;
            case 5:
                packet = EnumFacing.WEST.getIndex();
                break;
        }
        if (packet > -1)
            this.mc.playerController.sendEnchantPacket(this.containerOilGenerator.windowId, packet);
    }


}
