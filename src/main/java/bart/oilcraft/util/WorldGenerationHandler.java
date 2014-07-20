package bart.oilcraft.util;

import bart.oilcraft.blocks.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Bart on 19-7-2014.
 */
public class WorldGenerationHandler implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId ) {
            case -1: {
                break;
            }
            case 0: {
                generateSurface(world, random, chunkX, chunkZ);
                break;
            }
            case 1: {
                break;
            }
        }
    }

    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 10; k++) {
            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            int firstBlockYCoord = rand.nextInt(60);

            (new WorldGenMinable(ModBlocks.CrudeOilOre, 0, 4, Blocks.stone)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
    }
}
