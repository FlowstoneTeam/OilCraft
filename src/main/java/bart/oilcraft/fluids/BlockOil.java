package bart.oilcraft.fluids;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.lib.References;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.fluids.Fluid;

public class BlockOil extends BaseFluid {
    public BlockOil(Fluid fluid) {
        super(fluid);
        this.setBlockName("Oil");
        this.setCreativeTab(OilCraftMain.getCreativeTab());
        GameRegistry.registerBlock(this, getName());
    }

    @Override
    public void registerBlockIcons(IIconRegister Register){
        super.registerBlockIcons(Register);
        ModFluids.Oil.setStillIcon(Register.registerIcon(References.MODID + ":BlockOilStillBlock"));
        ModFluids.Oil.setFlowingIcon(Register.registerIcon(References.MODID + ":BlockOilFlowingBlock"));
    }

    @Override
    public String getName(){
        return "BlockOil";
    }
}