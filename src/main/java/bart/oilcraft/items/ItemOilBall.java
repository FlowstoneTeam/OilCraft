package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.entities.EntityOilBall;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Bart on 27-10-2014.
 */
public class ItemOilBall extends Item {
    public IIcon icon;

    public ItemOilBall() {
        this.setTextureName(References.RESOURCESPREFIX + "oilball");
        this.setUnlocalizedName(References.MODID + ".oilball");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icon = iconRegister.registerIcon(References.RESOURCESPREFIX + "oilball");
    }


    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        if (!par3.capabilities.isCreativeMode) {
            --par1.stackSize;
        }

        par2.playSoundAtEntity(par3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2.isRemote) {
            par2.spawnEntityInWorld(new EntityOilBall(par2, par3));
        }

        return par1;
    }
}
