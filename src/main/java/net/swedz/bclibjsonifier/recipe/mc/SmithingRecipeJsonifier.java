package net.swedz.bclibjsonifier.recipe.mc;

import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;

import java.lang.reflect.Field;

public final class SmithingRecipeJsonifier implements RecipeJsonifier<SmithingRecipe>
{
	@Override
	public Class<SmithingRecipe> recipeClass()
	{
		return SmithingRecipe.class;
	}
	
	@SuppressWarnings("JavaReflectionMemberAccess")
	@Override
	public JsonObject create(SmithingRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "minecraft:smithing");
		
		try
		{
			Field fieldBase = SmithingRecipe.class.getDeclaredField("field_25389"); // SmithingRecipe#base
			fieldBase.setAccessible(true);
			Ingredient base = (Ingredient) fieldBase.get(recipe);
			json.add("base", base.toJson());
			
			Field fieldAddition = SmithingRecipe.class.getDeclaredField("field_25390"); // SmithingRecipe#addition
			fieldAddition.setAccessible(true);
			Ingredient addition = (Ingredient) fieldAddition.get(recipe);
			json.add("addition", addition.toJson());
		}
		catch (NoSuchFieldException | IllegalAccessException ex)
		{
			throw new RuntimeException(ex);
		}
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		return json;
	}
}
