package net.swedz.bclibjsonifier.recipe.bclib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;
import org.betterx.bclib.recipes.AlloyingRecipe;

public final class AlloyingRecipeJsonifier implements RecipeJsonifier<AlloyingRecipe>
{
	@Override
	public Class<AlloyingRecipe> recipeClass()
	{
		return AlloyingRecipe.class;
	}
	
	@Override
	public JsonObject create(AlloyingRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "bclib:alloying");
		
		JsonArray ingredients = new JsonArray();
		for(Ingredient ingredient : recipe.getIngredients())
		{
			ingredients.add(ingredient.toJson());
		}
		json.add("ingredients", ingredients);
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		json.addProperty("experience", recipe.getExperience());
		
		json.addProperty("smelttime", recipe.getSmeltTime());
		
		return json;
	}
}
