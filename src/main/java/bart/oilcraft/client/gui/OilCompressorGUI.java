package bart.oilcraft.client.gui;

import bart.oilcraft.lib.References;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Bart on 7-6-2014.
 */
public class OilCompressorGUI extends GuiScreen {
    int xSize = 174;
    int ySize = 165;
    private static final ResourceLocation backgroundimage = new ResourceLocation(References.RESOURCESPREFIX + "textures/gui/oilcompressor.png");

    public OilCompressorGUI() {
    }


    @Override
    public void drawScreen(int par1, int par2, float par3) {
        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize - 30) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize, ySize);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}