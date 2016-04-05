package bart.oilcraft.block;


import bart.oilcraft.creativetab.OCCreativeTabs;
import bart.oilcraft.item.OCItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class OCBlock extends Block {

    private String name;
    public OCBlock(Material material, MapColor mapColor, String name) {
        super(material, mapColor);
        this.setUnlocalizedName("oilcraft." + name);
        this.setCreativeTab(OCCreativeTabs.main);
        this.name = name;
    }

    public String blockName(int meta) {
        return name;
    }

    public int[] modelMetas() {
        return new int[]{0};
    }

    public boolean checkForBucketClick(World worldIn, BlockPos pos, EnumHand hand, EntityPlayer playerIn, EnumFacing side) {
        if (worldIn.isRemote)
            return false;
        if (worldIn.getTileEntity(pos) instanceof IFluidHandler && playerIn.getHeldItem(hand) != null && playerIn.getHeldItem(hand).getItem() == OCItemRegistry.oilBucket) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (((IFluidHandler)tile).fill(side, new FluidStack(FluidRegistry.getFluid("oil"), 1000), false) == 1000){
                worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
                ((IFluidHandler)tile).fill(side, new FluidStack(FluidRegistry.getFluid("oil"), 1000), true);
                playerIn.setHeldItem(hand, null);
                playerIn.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                playerIn.inventoryContainer.detectAndSendChanges();
                return true;
            }
        }
        return false;
    }

    public OCBlock setStepSound(SoundType sound) {
        return (OCBlock)super.setStepSound(sound);
    }

}
