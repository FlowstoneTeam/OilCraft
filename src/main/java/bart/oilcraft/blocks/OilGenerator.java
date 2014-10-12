package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.items.OilBucket;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Created by Bart on 11-10-2014.
 */
public class OilGenerator extends OilCraftBlock implements ITileEntityProvider {

    public IIcon[] icons;
    public OilGenerator() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(4f);
        icons = new IIcon[6];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side <= 5)
            return icons[side];
        else
            return icons[0];
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        for(int i = 0; i < icons.length; i++){

            if(i == 0 ){
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "general_machine");
            }

            if (i == 1) {
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_top");
            }
            else if(i == 2){
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + getName() + "_front");
            }
            else{
                icons[i] = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_side");
            }
        }
    }

    @Override
    public String getName() {
        return "OilGenerator";
    }


    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new OilGeneratorEntity();
    }

    /*@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
        if (entityPlayer.isSneaking()) return false;
        else {
            if (world.isRemote) {
                TileEntity te = world.getTileEntity(x, y, z);
                ItemStack equippedItem = entityPlayer.getCurrentEquippedItem();
                if (te instanceof IFluidHandler) {
                    int sending = 1000;
                    int filling = ((IFluidHandler) te).fill(ForgeDirection.UP, new FluidStack(ModFluids.Oil, sending), false);
                    if (filling <= sending && filling > 0) {
                        if (equippedItem.getItem() instanceof OilBucket) {
                            ItemStack stack2 = equippedItem.getItem().getContainerItem(equippedItem);
                            entityPlayer.inventory.addItemStackToInventory(stack2);
                            ((IFluidHandler) te).fill(ForgeDirection.UP, new FluidStack(ModFluids.Oil, sending), true);
                        }
                        return true;
                    }
                }
            }
            return true;
        }
    }
    */
}
