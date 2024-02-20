package net.swedz.bclibjsonifier.recipe.mc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmokingRecipe;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;

public final class CookingRecipeJsonifier implements RecipeJsonifier<AbstractCookingRecipe>
{
	@Override
	public Class<AbstractCookingRecipe> recipeClass()
	{
		return AbstractCookingRecipe.class;
	}
	
	@Override
	public JsonObject create(AbstractCookingRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		String type = "minecraft:smelting";
		if(recipe instanceof BlastingRecipe)
		{
			type = "minecraft:blasting";
		}
		else if(recipe instanceof CampfireCookingRecipe)
		{
			type = "minecraft:campfire_cooking";
		}
		else if(recipe instanceof SmokingRecipe)
		{
			type = "minecraft:smoking";
		}
		json.addProperty("type", type);
		
		if(recipe.getIngredients().size() == 1)
		{
			json.add("ingredient", recipe.getIngredients().get(0).toJson());
		}
		else
		{
			JsonArray ingredients = new JsonArray();
			for(Ingredient ingredient : recipe.getIngredients())
			{
				ingredients.add(ingredient.toJson());
			}
			json.add("ingredient", ingredients);
		}
		
		json.addProperty("result", recipe.getOutput().toJsonJS().get("item").getAsString());
		
		json.addProperty("experience", recipe.getExperience());
		
		json.addProperty("cookingtime", recipe.getCookTime());
		
		return json;
	}
}
