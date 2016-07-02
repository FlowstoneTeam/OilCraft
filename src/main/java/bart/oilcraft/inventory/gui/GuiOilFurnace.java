package bart.oilcraft.inventory.gui;

import bart.oilcraft.inventory.container.ContainerOilFurnace;
import bart.oilcraft.tileentity.TileEntityOilFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiOilFurnace extends OCGui {
    private static ResourceLocation oilFurnaceGui = new ResourceLocation("oilcraft:textures/gui/oil_furnace.png");
    private TileEntityOilFurnace tileOilFurnace;
    private int tick = 0;
    private int frame = 0;

    public GuiOilFurnace(EntityPlayer player, TileEntityOilFurnace tile) {
        super(new ContainerOilFurnace(player, tile));
        this.tileOilFurnace = tile;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileOilFurnace.hasCustomName() ? this.tileOilFurnace.getName() : I18n.format(this.tileOilFurnace.getName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int amount = getScaled(tileOilFurnace.tank.getCapacity(), tileOilFurnace.tank.getFluidAmount(), 58);
        if (tileOilFurnace.tank.getFluid() != null) {
            mc.getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/blocks/oil_still.png"));
            drawScaledCustomSizeModalRect(143, 72 - amount, 0, frame % 200, 8, amount, 16, amount, 16, 320);
        }
        tick++;
        if (tick % 10 == 0)
            frame += 8;

        amount = getScaled(tileOilFurnace.energyStorage.getMaxEnergyStored(), tileOilFurnace.energyStorage.getEnergyStored(), 71);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/gui/energy_bar.png"));
        drawModalRectWithCustomSizedTexture(10, 78 - amount, 16, 71 - amount, 16, amount, 16, 71);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        Minecraft.getMinecraft().getTextureManager().bindTexture(oilFurnaceGui);
        amount = getScaled((int) (tileOilFurnace.timeToProcess * (tileOilFurnace.timesLeft > 0 ? 0.6f : 1)), tileOilFurnace.progress , 22);
        drawTexturedModalRect(75, 36, 177, 3, amount, 16);

        if (mouseX >= 143 + k && mouseX <= 159 + k && mouseY >= 14 + l && mouseY <= 72 + l) {
            List<String> list = new ArrayList<String>();
            list.add("Fluid: Oil");
            list.add(String.format("Tank: %s mB / %s mB", tileOilFurnace.tank.getFluidAmount(), tileOilFurnace.tank.getCapacity()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }

        if (mouseX >= 10 + k && mouseX <= 26 + k && mouseY >= 7 + l && mouseY <= 78 + l) {
            List<String> list = new ArrayList<String>();
            list.add(String.format("Energie: %s RF / %s RF", tileOilFurnace.energyStorage.getEnergyStored(), tileOilFurnace.energyStorage.getMaxEnergyStored()));
            drawHoveringText(list, mouseX - k, mouseY - l);
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
        mc.getTextureManager().bindTexture(oilFurnaceGui);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
