package bart.oilcraft.block;


import bart.oilcraft.creativetab.OCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.UniversalBucket;

public class OCBlock extends Block {
    public OCBlock(Material material, MapColor mapColor) {
        super(material, mapColor);
        this.setCreativeTab(OCCreativeTabs.main);
    }

    public int[] modelMetas() {
        return new int[]{0};
    }

    public boolean checkForBucketClick(World worldIn, BlockPos pos, EnumHand hand, EntityPlayer playerIn, EnumFacing side) {
        if (worldIn.isRemote)
            return false;
        ItemStack itemStack = playerIn.getHeldItem(hand);
        if (worldIn.getTileEntity(pos) instanceof IFluidHandler && itemStack != null && itemStack.getItem() != null && itemStack.getItem() instanceof UniversalBucket) {
            TileEntity tile = worldIn.getTileEntity(pos);
            FluidStack fluidStack = ((UniversalBucket) itemStack.getItem()).getFluid(itemStack);
            if (fluidStack != null && fluidStack.getFluid() != null && ((IFluidHandler) tile).canFill(side, fluidStack.getFluid()) && ((IFluidHandler) tile).fill(side, fluidStack,false) == fluidStack.amount){
                worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
                ((IFluidHandler) tile).fill(side, fluidStack, true);
                ((UniversalBucket) itemStack.getItem()).drain(itemStack, fluidStack.amount, true);
                return true;
            }
        }
        return false;
    }

    public OCBlock setSoundType(SoundType sound) {
        return (OCBlock) super.setSoundType(sound);
    }

}
