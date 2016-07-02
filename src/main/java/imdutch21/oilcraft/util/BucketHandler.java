package imdutch21.oilcraft.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bart on 14/02/2016.
 */
public class BucketHandler {
    public static BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    private BucketHandler() {
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {

        ItemStack result = fillCustomBucket(event.getWorld(), event.getTarget().getBlockPos());

        if (result == null)
            return;

        event.setFilledBucket(result);
        event.setResult(Event.Result.ALLOW);
    }

    private ItemStack fillCustomBucket(World world, BlockPos pos) {

        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        int metadata = block.getMetaFromState(blockState);

        Item bucket = buckets.get(block);
        if (bucket != null && metadata == 0) {
            world.setBlockToAir(pos);
            return new ItemStack(bucket);
        } else
            return null;

    }
}
