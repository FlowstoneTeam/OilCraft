package bart.oilcraft.inventory.container;

import bart.oilcraft.tileentity.TileEntityOilGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Bart on 14/02/2016.
 */
public class ContainerOilGenerator extends Container {
    TileEntityOilGenerator tileOilGenerator;

    public ContainerOilGenerator(EntityPlayer player, TileEntityOilGenerator tile) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
        tileOilGenerator = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
