package net.swedz.bclibjsonifier.recipe;

import com.google.gson.JsonObject;
import net.minecraft.recipe.Recipe;

public interface RecipeJsonifier<R extends Recipe<?>>
{
	Class<R> recipeClass();
	
	JsonObject create(R recipe);
}
