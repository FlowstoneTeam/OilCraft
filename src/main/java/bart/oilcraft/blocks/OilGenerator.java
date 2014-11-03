package bart.oilcraft.blocks;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.items.OilBucket;
import bart.oilcraft.lib.References;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.OilGeneratorEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Created by Bart on 11-10-2014.
 */
public class OilGenerator extends OilCraftBlock implements ITileEntityProvider {

    public IIcon top;
    public IIcon bottom;
    public IIcon front;
    public IIcon side;

    public OilGenerator() {
        this.setBlockName(getName());
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);
    }


    @Override
    public String getName() {
        return "oilgenerator";
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.front = iconRegister.registerIcon(References.RESOURCESPREFIX + "oilgenerator_front");
        this.top = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_top");
        this.bottom = iconRegister.registerIcon(References.RESOURCESPREFIX + "general_machine");
        this.side = iconRegister.registerIcon(References.RESOURCESPREFIX + "machine_side");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        OilGeneratorEntity tile = (OilGeneratorEntity) world.getTileEntity(x, y, z);

        if (facing == 0)
            tile.facing = 2;
        else if (facing == 1)
            tile.facing = 5;
        else if (facing == 2)
            tile.facing = 3;
        else if (facing == 3)
            tile.facing = 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side){
        OilGeneratorEntity tile = (OilGeneratorEntity) access.getTileEntity(x, y, z);
        if(side == 0 ){
            return this.bottom;
        } else if (side == 1) {
            return this.top;
        }
        else if(side != tile.facing){
            return this.side;
        }
        else{
            return this.front;
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 0)return this.bottom;
        else if(side == 1)return this.top;
        else if(side == 3)return this.front;
        else return this.side;
    }
}
