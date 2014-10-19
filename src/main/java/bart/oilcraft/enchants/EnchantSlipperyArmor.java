package bart.oilcraft.enchants;

import bart.oilcraft.lib.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

/**
 * Created by bart on 18-10-2014.
 */
public class EnchantSlipperyArmor extends Enchantment {
    public EnchantSlipperyArmor(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.armor);
        this.setName(References.MODID + ".Slippery");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int level) {
        return 25;
    }

    public int getWeight()
    {
        return 6;
    }

    public static void addToBookList(Enchantment enchantment) {
        com.google.common.collect.ObjectArrays.concat(enchantmentsBookList, enchantment);
    }
}
