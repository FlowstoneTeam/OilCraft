package bart.oilcraft.proxy;

import bart.oilcraft.blocks.OCBlock;
import bart.oilcraft.blocks.OCBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Created by Bart on 12/02/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void initModels() {
        initBlockModels();
    }

    private void initBlockModels() {
        for (Block block : OCBlockRegistry.BLOCKS) {
            if (block instanceof OCBlock) {
                for (int i : ((OCBlock) block).modelMetas()) {
                    ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation("oilcraft:" + ((OCBlock)block).blockName(i)));
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation("oilcraft:" + ((OCBlock)block).blockName(i), "inventory"));
                }
            }
        }
    }

    @Override
    public void registerFluidBlockRendering(Block block, String name) {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation("oilcraft:fluids", name);

        // use a custom state mapper which will ignore the LEVEL property
        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return fluidLocation;
            }
        });
    }
}
