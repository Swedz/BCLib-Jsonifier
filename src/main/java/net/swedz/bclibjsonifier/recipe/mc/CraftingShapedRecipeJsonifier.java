package net.swedz.bclibjsonifier.recipe.mc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;

public final class CraftingShapedRecipeJsonifier implements RecipeJsonifier<ShapedRecipe>
{
	@Override
	public Class<ShapedRecipe> recipeClass()
	{
		return ShapedRecipe.class;
	}
	
	@Override
	public JsonObject create(ShapedRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "minecraft:crafting_shaped");
		
		JsonObject key = new JsonObject();
		JsonArray pattern = new JsonArray();
		int index = 0;
		for(Ingredient ingredient : recipe.getIngredients())
		{
			String symbol = " ";
			if(!ingredient.isEmpty())
			{
				symbol = Integer.toString(index);
				key.add(symbol, ingredient.toJson());
			}
			
			int line = index / recipe.getWidth();
			boolean withinBounds = pattern.size() > line;
			String patternLine = withinBounds ? pattern.get(line).getAsString() : "";
			patternLine += symbol;
			JsonPrimitive entry = new JsonPrimitive(patternLine);
			if(withinBounds)
			{
				pattern.set(line, entry);
			}
			else
			{
				pattern.add(entry);
			}
			
			index++;
		}
		json.add("key", key);
		json.add("pattern", pattern);
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		return json;
	}
}
