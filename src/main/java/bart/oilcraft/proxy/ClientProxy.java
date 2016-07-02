package bart.oilcraft.proxy;

import bart.oilcraft.OilCraftMain;
import bart.oilcraft.block.OCBlock;
import bart.oilcraft.block.OCBlockRegistry;
import bart.oilcraft.client.render.RenderFactoryEntityGooBall;
import bart.oilcraft.entity.EntityGooBall;
import bart.oilcraft.fluids.OCFluidBlock;
import bart.oilcraft.item.OCItem;
import bart.oilcraft.item.OCItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void initModels() {
        initBlockModels();
        initItemModels();
    }

    @Override
    public void initRenderers() {
        super.initRenderers();
        RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RenderFactoryEntityGooBall());
    }

    private void initBlockModels() {
        for (Block block : OCBlockRegistry.BLOCKS) {
            if (block instanceof OCFluidBlock){
                registerFluidBlockRendering(block, block.getRegistryName().toString().replace("oilcraft:", ""));
            } else if (block instanceof OCBlock) {
                for (int i : ((OCBlock) block).modelMetas()) {
                    ModelBakery.registerItemVariants(Item.getItemFromBlock(block), block.getRegistryName());
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(block.getRegistryName(), "inventory"));
                }
            }
        }
    }

    private void initItemModels() {
        for (Item item : OCItemRegistry.ITEMS) {
            if (item instanceof OCItem) {
                for (int i : ((OCItem) item).modelMetas()) {
                    ModelBakery.registerItemVariants(item, new ResourceLocation("oilcraft:" + ((OCItem)item).itemName(i)));
                    ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation("oilcraft:" + ((OCItem)item).itemName(i), "inventory"));
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
