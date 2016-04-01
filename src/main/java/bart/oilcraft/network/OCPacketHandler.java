package bart.oilcraft.network;

import bart.oilcraft.lib.ModInfo;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Bart on 29/03/2016.
 */
public class OCPacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(ModInfo.ID);

    public static void init(){
        INSTANCE.registerMessage(DirectionButtonPacketProcessor.class, DirectionButtonPacketProcessor.class, 0, Side.SERVER);
    }
}
