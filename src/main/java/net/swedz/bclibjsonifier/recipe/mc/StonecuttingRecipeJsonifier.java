package net.swedz.bclibjsonifier.recipe.mc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.util.registry.Registry;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;

public final class StonecuttingRecipeJsonifier implements RecipeJsonifier<StonecuttingRecipe>
{
	@Override
	public Class<StonecuttingRecipe> recipeClass()
	{
		return StonecuttingRecipe.class;
	}
	
	@Override
	public JsonObject create(StonecuttingRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "minecraft:stonecutting");
		
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
		
		json.addProperty("result", Registry.ITEM.getId(recipe.getOutput().getItem()).toString());
		
		json.addProperty("count", recipe.getOutput().getCount());
		
		return json;
	}
}
