package imdutch21.oilcraft.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class OCTickingTileEntity extends TileEntity implements ITickable {

    @Override
    public void update() {

    }

    public boolean matches(ItemStack itemStack1, ItemStack itemstack2) {
        return itemStack1 != null && itemstack2 != null && itemstack2.getItem() == itemStack1.getItem() && itemstack2.getItemDamage() == itemStack1.getItemDamage();
    }
}
