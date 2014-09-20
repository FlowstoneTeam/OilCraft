package bart.oilcraft.enchants;

import bart.oilcraft.lib.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantSlippery extends Enchantment {
    public EnchantSlippery(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.all);
        this.setName(References.MODID + ".Slippery");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int level) {
        return 20;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return canApply(stack);
    }

    @Override
    public boolean canApply(ItemStack itemStack) {
        return this.type.canEnchantItem(itemStack.getItem());
    }


    public static void addToBookList(Enchantment enchantment) {
        com.google.common.collect.ObjectArrays.concat(enchantmentsBookList, enchantment);
    }
}
