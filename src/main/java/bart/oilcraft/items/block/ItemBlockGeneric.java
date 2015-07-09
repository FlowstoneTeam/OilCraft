package bart.oilcraft.items.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 9-7-2015.
 */
public class ItemBlockGeneric extends ItemBlock {
    public ItemBlockGeneric(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return this.getUnlocalizedName() + "_" + is.getItemDamage();
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    public Block getBlock() {
        return field_150939_a;
    }
}
