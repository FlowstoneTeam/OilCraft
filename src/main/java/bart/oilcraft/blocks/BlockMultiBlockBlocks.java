package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.items.block.ItemBlockGeneric;
import bart.oilcraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by Bart on 9-7-2015.
 */
public class BlockMultiblockBlocks extends Block implements OilCraftBlockRegistry.ISubBlocksBlock {
    public static final String[] iconPaths = new String[]{"oilRefinery"};

    protected BlockMultiblockBlocks() {
        super(Material.iron);
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setBlockName("oilcraft.blockMultiBlockBlocks");
        this.setBlockTextureName(References.RESOURCESPREFIX + "invisable");
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list) {
        for (int i = 0; i < iconPaths.length; i++)
            list.add(new ItemStack(id, 1, i));
    }


    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }


    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockGeneric.class;
    }
}
