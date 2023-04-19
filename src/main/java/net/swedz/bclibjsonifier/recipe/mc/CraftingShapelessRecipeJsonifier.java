package net.swedz.bclibjsonifier.recipe.mc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;

public final class CraftingShapelessRecipeJsonifier implements RecipeJsonifier<ShapelessRecipe>
{
	@Override
	public Class<ShapelessRecipe> recipeClass()
	{
		return ShapelessRecipe.class;
	}
	
	@Override
	public JsonObject create(ShapelessRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "minecraft:crafting_shapeless");
		
		JsonArray ingredients = new JsonArray();
		for(Ingredient ingredient : recipe.getIngredients())
		{
			ingredients.add(ingredient.toJson());
		}
		json.add("ingredients", ingredients);
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		return json;
	}
}
