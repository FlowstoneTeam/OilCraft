package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Bart on 28-10-2014.
 */
public class BlockOilLayer extends OilCraftBlock {
    public BlockOilLayer() {
        super(Material.sand);
        this.setBlockName(getName());
        this.setStepSound(Block.soundTypeSnow);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.func_150154_b(0);
        this.setBlockTextureName(References.RESOURCESPREFIX + getName());
    }

    public String getName() {
        return "oillayer";
    }


    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    @Override
    public void setBlockBoundsForItemRender(){
        this.func_150154_b(0);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return (world.getBlock(x, y, z).isReplaceable(world, x, y, z) && world.getBlock(x, y-1, z).renderAsNormalBlock() && world.getBlock(x, y-1, z) != Blocks.air);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
        Random rand = new Random();
        int random = rand.nextInt(20);
        if(random == 1)return ModItems.OilBall;
        else return null;
    }

    protected void func_150154_b(int p_150154_1_)
    {
        int j = p_150154_1_ & 7;
        float f = (float)(2 * (1 + j)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

}
