package zmaster587.advancedRocketry.integration.jei.blastFurnace;

import java.util.LinkedList;
import java.util.List;

import zmaster587.libVulpes.interfaces.IRecipe;
import zmaster587.libVulpes.recipe.RecipesMachine;
import mezz.jei.api.IJeiHelpers;

public class BlastFurnaceRecipeMaker {

	public static List<BlastFurnaceWrapper> getMachineRecipes(IJeiHelpers helpers, Class clazz) {
		
		List<BlastFurnaceWrapper> list = new LinkedList<BlastFurnaceWrapper>();
		for(IRecipe rec : RecipesMachine.getInstance().getRecipes(clazz)) {
			list.add(new BlastFurnaceWrapper(rec));
		}
		return list;
	}
	
}
