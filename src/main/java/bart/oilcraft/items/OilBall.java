package bart.oilcraft.items;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.entities.entitytrowable.EntityOilBall;
import bart.oilcraft.lib.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Bart on 27-10-2014.
 */
public class OilBall extends Item {
    public OilBall(){
        this.setTextureName(References.RESOURCESPREFIX + getName());
        this.setUnlocalizedName(References.MODID + ".oilball");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
    }
    public String getName() {
        return "oilball";
    }

    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        if (!par3.capabilities.isCreativeMode)
        {
            --par1.stackSize;
        }

        par2.playSoundAtEntity(par3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2.isRemote)
        {
            par2.spawnEntityInWorld(new EntityOilBall(par2, par3));
        }

        return par1;
    }
}
