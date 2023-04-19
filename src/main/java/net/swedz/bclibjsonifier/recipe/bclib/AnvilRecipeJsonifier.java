package net.swedz.bclibjsonifier.recipe.bclib;

import com.google.gson.JsonObject;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;
import org.betterx.bclib.recipes.AnvilRecipe;

import java.lang.reflect.Field;

public final class AnvilRecipeJsonifier implements RecipeJsonifier<AnvilRecipe>
{
	@Override
	public Class<AnvilRecipe> recipeClass()
	{
		return AnvilRecipe.class;
	}
	
	@Override
	public JsonObject create(AnvilRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "bclib:smithing");
		
		json.add("input", recipe.getMainIngredient().toJson());
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		json.addProperty("inputCount", recipe.getInputCount());
		
		try
		{
			Field fieldToolLevel = AnvilRecipe.class.getDeclaredField("toolLevel");
			fieldToolLevel.setAccessible(true);
			int toolLevel = (Integer) fieldToolLevel.get(recipe);
			json.addProperty("toolLevel", toolLevel);
		}
		catch (NoSuchFieldException | IllegalAccessException ex)
		{
			throw new RuntimeException(ex);
		}
		
		json.addProperty("anvilLevel", recipe.getAnvilLevel());
		
		json.addProperty("damage", recipe.getDamage());
		
		return json;
	}
}
