package bart.oilcraft.world;

import bart.oilcraft.fluids.OCFluidRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Bart on 30/03/2016.
 */
public class WorldGenOilPool implements IWorldGenerator {

    private Block[] blackListBlocks = new Block[]{Blocks.log, Blocks.leaves};

    private Block fillerFluid = OCFluidRegistry.oil.getBlock();

    private double size;

    private int minHeight = 70;
    private boolean destroyBlocks = false;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0 && random.nextInt(8) == 0) {
            size = (random.nextDouble() + 0.7D) * 1.2d;
            generate(world, random, chunkX * 16, 30 + random.nextInt(30), chunkZ * 16);
        }
    }


    private boolean generate(World world, Random rand, int x, int y, int z) {
        x -= 8;
        z -= 8;
        if (world.getBiomeGenForCoords(new BlockPos(x, y, z)) == Biomes.desert || world.getBiomeGenForCoords(new BlockPos(x, y, z)) == Biomes.plains) {

            if (y <= minHeight || (world.getBlockState(new BlockPos(x, y, z)) == Blocks.stone.getDefaultState() && !destroyBlocks))
                return false;
            y -= 4;

            for (int xx = x; xx < x + 16; ++xx)
                for (int zz = z; zz < z + 16; ++zz)
                    for (int yy = y; yy < y + 8; ++yy)
                        if (isBlacklistedBlock(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock()))
                            return false;

            boolean[] placeFluid = new boolean[2048];

            int xx;

            for (int iteration = 0, iterAmount = rand.nextInt(3) + 5; iteration < iterAmount; ++iteration) {
                double d0 = (rand.nextDouble() * 6D + 3D) * size * (0.4D + rand.nextDouble() * 0.6D);
                double d1 = (rand.nextDouble() * 4D + 2D) * size / 2.5D;
                double d2 = (rand.nextDouble() * 6D + 3D) * size * (0.4D + rand.nextDouble() * 0.6D);
                double d3 = rand.nextDouble() * (16D - d0 - 2D) + 1D + d0 / 2D;
                double d4 = rand.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
                double d5 = rand.nextDouble() * (16D - d2 - 2D) + 1D + d2 / 2D;

                for (xx = 1; xx < 15; ++xx)
                    for (int zz = 1; zz < 15; ++zz)
                        for (int yy = 1; yy < 7; ++yy) {
                            double d6 = (xx - d3) / (d0 / 2.0D);
                            double d7 = (yy - d4) / (d1 / 2.0D);
                            double d8 = (zz - d5) / (d2 / 2.0D);
                            double dist = d6 * d6 + d7 * d7 + d8 * d8;

                            if (dist < 1D)
                                placeFluid[(xx * 16 + zz) * 8 + yy] = true;
                        }
            }

            int yy;
            int zz;
            boolean flag;

            for (xx = 0; xx < 16; ++xx)
                for (zz = 0; zz < 16; ++zz)
                    for (yy = 0; yy < 8; ++yy) {
                        flag = !placeFluid[(xx * 16 + zz) * 8 + yy] && (xx < 15 && placeFluid[((xx + 1) * 16 + zz) * 8 + yy] || xx > 0 && placeFluid[((xx - 1) * 16 + zz) * 8 + yy] || zz < 15 && placeFluid[(xx * 16 + zz + 1) * 8 + yy] || zz > 0 && placeFluid[(xx * 16 + zz - 1) * 8 + yy] || yy < 7 && placeFluid[(xx * 16 + zz) * 8 + yy + 1] || yy > 0 && placeFluid[(xx * 16 + zz) * 8 + yy - 1]);
                        if (flag) {
                            Material material = world.getBlockState(new BlockPos(x + xx, y + yy, z + zz)).getMaterial();
                            if (yy >= 4 && material.isLiquid())
                                return false;
                            if (yy < 4 && !material.isSolid() && world.getBlockState(new BlockPos(x + xx, y + yy, z + zz)).getBlock() != fillerFluid)
                                return false;
                        }
                    }

            for (xx = 0; xx < 16; ++xx)
                for (zz = 0; zz < 16; ++zz)
                    for (yy = 0; yy < 8; ++yy)
                        if (placeFluid[(xx * 16 + zz) * 8 + yy])
                            world.setBlockState(new BlockPos(x + xx, y + yy, z + zz), yy >= 4 ? Blocks.air.getDefaultState() : fillerFluid.getDefaultState(), 2);
            System.out.println("generate at: " + (new BlockPos(x, y, z)).toString());
            return true;
        }
        return false;
    }

    private boolean isBlacklistedBlock(Block block) {
        for (Block blacklistBlock : blackListBlocks)
            if (block.equals(blacklistBlock))
                return true;
        return false;
    }
}
