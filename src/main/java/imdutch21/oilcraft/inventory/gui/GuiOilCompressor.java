package imdutch21.oilcraft.inventory.gui;

import imdutch21.oilcraft.inventory.container.ContainerOilCompressor;
import imdutch21.oilcraft.recipe.OilCompressorRecipe;
import imdutch21.oilcraft.tileentity.TileEntityOilCompressor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiOilCompressor extends OCGui {
    private static ResourceLocation oilCompressorGui = new ResourceLocation("oilcraft:textures/gui/oil_compressor.png");
    private TileEntityOilCompressor tileOilCompressor;
    private int tick = 0;
    private int frame = 0;

    public GuiOilCompressor(EntityPlayer player, TileEntityOilCompressor tile) {
        super(new ContainerOilCompressor(player, tile));
        this.tileOilCompressor = tile;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileOilCompressor.hasCustomName() ? this.tileOilCompressor.getName() : I18n.format(this.tileOilCompressor.getName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int amount = getScaled(tileOilCompressor.tank.getCapacity(), tileOilCompressor.tank.getFluidAmount(), 58);
        if (tileOilCompressor.tank.getFluid() != null) {
            mc.getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/blocks/oil_still.png"));
            drawScaledCustomSizeModalRect(82, 72 - amount, 0, frame % 200, 8, amount, 16, amount, 16, 320);
        }
        tick++;
        if (tick % 10 == 0)
            frame += 8;

        if (tileOilCompressor.items[0] != null && OilCompressorRecipe.getRecipeFromItem(tileOilCompressor.items[0]) != null) {
            amount = getScaled(OilCompressorRecipe.getRecipeFromItem(tileOilCompressor.items[0]).time, tileOilCompressor.progress, 22);
            mc.getTextureManager().bindTexture(oilCompressorGui);
            drawTexturedModalRect(31, 34, 181, 0, 19, amount);
        }

        amount = getScaled(tileOilCompressor.energyStorage.getMaxEnergyStored(), tileOilCompressor.energyStorage.getEnergyStored(), 71);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("oilcraft:textures/gui/energy_bar.png"));
        drawModalRectWithCustomSizedTexture(10, 78 - amount, 16, 71 - amount, 16, amount, 16, 71);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        if (mouseX >= 82 + k && mouseX <= 98 + k && mouseY >= 14 + l && mouseY <= 72 + l) {
            List<String> list = new ArrayList<String>();
            list.add("Fluid: Oil");
            list.add(String.format("Tank: %s mB / %s mB", tileOilCompressor.tank.getFluidAmount(), tileOilCompressor.tank.getCapacity()));
            drawHoveringText(list, mouseX - k, mouseY - l);
        }

        if (mouseX >= 10 + k && mouseX <= 26 + k && mouseY >= 7 + l && mouseY <= 78 + l) {
            List<String> list = new ArrayList<String>();
            list.add(String.format("Energie: %s RF / %s RF", tileOilCompressor.energyStorage.getEnergyStored(), tileOilCompressor.energyStorage.getMaxEnergyStored()));
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
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(oilCompressorGui);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }


}
