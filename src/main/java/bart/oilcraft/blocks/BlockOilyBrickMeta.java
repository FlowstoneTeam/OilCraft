package bart.oilcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 17-11-2014.
 */
public class BlockOilyBrickMeta extends ItemBlock {
    public BlockOilyBrickMeta(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
            case 0:{
                name = "oilystonebrick";
                break;
            }
            case 1:{
                name = "crackedoilystonebrick";
                break;
            }
            default:
                name = "nothing";
        }
        return getUnlocalizedName() + "." + name;
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}
