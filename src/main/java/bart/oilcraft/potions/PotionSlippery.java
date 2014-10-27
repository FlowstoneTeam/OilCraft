package bart.oilcraft.potions;

import net.minecraft.potion.Potion;

/**
 * Created by Bart on 27-10-2014.
 */
public class PotionSlippery extends Potion {
    public PotionSlippery(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }
    @Override
    public Potion setIconIndex(int par1, int par2)
    {
        super.setIconIndex(par1, par2);
        return this;
    }
}
