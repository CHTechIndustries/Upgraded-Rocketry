package zmaster587.advancedRocketry.world.biome;

import java.util.Random;

import zmaster587.advancedRocketry.world.decoration.MapGenCrater;
import zmaster587.advancedRocketry.world.gen.WorldGenNoTree;
import zmaster587.advancedRocketry.world.gen.WorldGenSwampTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenDeepSwamp extends Biome {

	public static MapGenBase swampTree;
	private final static WorldGenNoTree noTree = new WorldGenNoTree(false);
	
	public BiomeGenDeepSwamp(int biomeId, boolean register) {
		super(new BiomeProperties("DeepSwamp").setBaseHeight(-0.1f).setHeightVariation(0.2f).setRainfall(0.9f).setTemperature(0.9f).setWaterColor(14745518));

		registerBiome(biomeId, "DeepSwamp", this);
		
		this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.flowersPerChunk = 1;
        this.theBiomeDecorator.deadBushPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator.sandPerChunk2 = 0;
        this.theBiomeDecorator.sandPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
        this.flowers.clear();
        this.addFlower(Blocks.RED_FLOWER.getDefaultState(), 10);
		swampTree = new WorldGenSwampTree(2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float p_76731_1_) {
		return 0x203020;
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return this.SWAMP_FEATURE;
	}
	
	@Override
	public EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		return EnumFlowerType.BLUE_ORCHID;
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand,
			ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		
		double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
                    {
                        chunkPrimerIn.setBlockState(j, k, i, WATER);

                        if (d0 < 0.12D)
                        {
                            chunkPrimerIn.setBlockState(j, k + 1, i, Blocks.WATERLILY.getDefaultState());
                        }
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);

        
		//Decoration time takes too long due to block relights, so run at terrain gen time
		///swampTree.func_151539_a(null, world, x, z, block); //Arg 1 never actually used so fake it
		//Yes this is hacky
		if(x % 16 == 0 && z % 16 == 0 )
			swampTree.generate(worldIn, x/16, z/16, chunkPrimerIn);
	}
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
    	
    	double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);
        return d0 < -0.1D ? 5011004 : 6975545;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
        return 6975545;
    }
}
