package imdutch21.oilcraft.util;

import imdutch21.oilcraft.lib.ModInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ConfigGUI
        extends GuiConfig
{
	public ConfigGUI(GuiScreen parent) {
		super(parent, getElements(), ModInfo.ID, ModInfo.ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.INSTANCE.config.toString()));
	}

	@SuppressWarnings({ "rawtypes" })
	private static List<IConfigElement> getElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>(ConfigHandler.CATEGORIES.length);
		for( String category : ConfigHandler.CATEGORIES ) {
            list.add(new ConfigElement(ConfigHandler.INSTANCE.config.getCategory(category.toLowerCase())));
        }

		return list;
	}
}