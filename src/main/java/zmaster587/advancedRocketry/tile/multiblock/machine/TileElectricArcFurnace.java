package zmaster587.advancedRocketry.tile.multiblock.machine;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zmaster587.advancedRocketry.api.AdvancedRocketryBlocks;
import zmaster587.advancedRocketry.inventory.TextureResources;
import zmaster587.advancedRocketry.util.AudioRegistry;
import zmaster587.libVulpes.LibVulpes;
import zmaster587.libVulpes.api.LibVulpesBlocks;
import zmaster587.libVulpes.api.material.AllowedProducts;
import zmaster587.libVulpes.api.material.Material;
import zmaster587.libVulpes.api.material.MaterialRegistry;
import zmaster587.libVulpes.block.BlockMeta;
import zmaster587.libVulpes.block.multiblock.BlockMultiBlockComponentVisible;
import zmaster587.libVulpes.client.util.ProgressBarImage;
import zmaster587.libVulpes.interfaces.IRecipe;
import zmaster587.libVulpes.inventory.modules.IModularInventory;
import zmaster587.libVulpes.inventory.modules.ModuleBase;
import zmaster587.libVulpes.inventory.modules.ModuleProgress;
import zmaster587.libVulpes.recipe.NumberedOreDictStack;
import zmaster587.libVulpes.recipe.RecipesMachine;
import zmaster587.libVulpes.tile.IMultiblock;
import zmaster587.libVulpes.tile.multiblock.TileMultiblockMachine;
import zmaster587.libVulpes.tile.multiblock.hatch.TileInputHatch;
import zmaster587.libVulpes.tile.multiblock.hatch.TileOutputHatch;

public class TileElectricArcFurnace extends TileMultiblockMachine implements IModularInventory {


	public static final Object[][][] structure = { 
		{	{null,null,null,null,null},
			{null,'P',AdvancedRocketryBlocks.blockBlastBrick,'P',null},
			{null,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,null},
			{null,AdvancedRocketryBlocks.blockBlastBrick,'P',AdvancedRocketryBlocks.blockBlastBrick,null},
			{null,null,null,null,null},
		},

		{	{null,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,null},
			{AdvancedRocketryBlocks.blockBlastBrick, "blockCoil",Blocks.AIR, "blockCoil",AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,Blocks.AIR,Blocks.AIR,Blocks.AIR,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,Blocks.AIR, "blockCoil",Blocks.AIR,AdvancedRocketryBlocks.blockBlastBrick},
			{null,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,null},
		},

		{	{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,Blocks.AIR,Blocks.AIR,Blocks.AIR,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,Blocks.AIR,Blocks.AIR,Blocks.AIR,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,Blocks.AIR,Blocks.AIR,Blocks.AIR,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
		},

		{	{AdvancedRocketryBlocks.blockBlastBrick,'*','c','*',AdvancedRocketryBlocks.blockBlastBrick},
			{'*',AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,'*'},
			{'*',AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick, '*'},
			{'*',AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick, '*'},
			{AdvancedRocketryBlocks.blockBlastBrick,'*','*','*',AdvancedRocketryBlocks.blockBlastBrick},
		},

		{	{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
			{AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick,AdvancedRocketryBlocks.blockBlastBrick},
		}

	};

	@Override
	public List<BlockMeta> getAllowableWildCardBlocks() {
		List<BlockMeta> list = super.getAllowableWildCardBlocks();
		list.add(new BlockMeta(LibVulpesBlocks.blockHatch, 0));
		list.add(new BlockMeta(LibVulpesBlocks.blockHatch, 1));
		list.add(new BlockMeta(AdvancedRocketryBlocks.blockBlastBrick, -1));
		return list;
	}
	
	@Override
	public void registerRecipes() {
		RecipesMachine.getInstance().addRecipe(TileElectricArcFurnace.class, MaterialRegistry.getMaterialFromName("Silicon").getProduct(AllowedProducts.getProductByName("INGOT")), 12000, 1, Blocks.SAND);
		RecipesMachine.getInstance().addRecipe(TileElectricArcFurnace.class, MaterialRegistry.getMaterialFromName("Steel").getProduct(AllowedProducts.getProductByName("INGOT")), 6000, 1, "ingotIron", new ItemStack(Items.COAL,1,1));
		//TODO add 2Al2O3 as output
		RecipesMachine.getInstance().addRecipe(TileElectricArcFurnace.class, MaterialRegistry.getMaterialFromName("TitaniumAluminide").getProduct(AllowedProducts.getProductByName("INGOT"), 3), 9000, 20, new NumberedOreDictStack("ingotAluminum", 7), new NumberedOreDictStack("ingotTitanium", 3)); //TODO titanium dioxide
		RecipesMachine.getInstance().addRecipe(TileElectricArcFurnace.class, MaterialRegistry.getMaterialFromName("TitaniumIridium").getProduct(AllowedProducts.getProductByName("INGOT"),2), 3000, 20, "ingotTitanium", "ingotIridium");

	}

	@Override
	protected void integrateTile(TileEntity tile) {
		super.integrateTile(tile);
	}

	@Override
	public Object[][][] getStructure() {
		return structure;
	}

	@Override
	public SoundEvent getSound() {
		return AudioRegistry.electricArcFurnace;
	}

	@Override
	public String getMachineName() {
		return "tile.electricArcFurnace.name";
	}

	@Override
	public float getTimeMultiplierForBlock(IBlockState state, TileEntity tile) {

		Material material = MaterialRegistry.getMaterialFromItemStack(new ItemStack(state.getBlock(),1, state.getBlock().getMetaFromState(state)));
		if(material == MaterialRegistry.getMaterialFromName("Gold"))
			return 0.9f;
		else if(material == MaterialRegistry.getMaterialFromName("Aluminum"))
			return 0.8f;
		else if(material == MaterialRegistry.getMaterialFromName("Titanium"))
			return 0.75f;
		else if(material == MaterialRegistry.getMaterialFromName("Iridium"))
			return 0.5f;

		return super.getTimeMultiplierForBlock(state, tile);
	}

	@Override
	public List<ModuleBase> getModules(int ID, EntityPlayer player) {
		// TODO Auto-generated method stub
		List<ModuleBase> modules = super.getModules(ID, player);

		modules.add(new ModuleProgress(80, 20, 0, TextureResources.arcFurnaceProgressBar, this));
		return modules;
	}
}
