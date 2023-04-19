package net.swedz.bclibjsonifier.recipe;

import com.google.common.collect.Maps;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

import java.util.Map;
import java.util.Set;

public final class RecipeTracker
{
	private final Map<Recipe, RecipeType<?>> recipes = Maps.newHashMap();
	
	public void track(RecipeType<?> recipeType, Recipe recipe)
	{
		recipes.put(recipe, recipeType);
	}
	
	public Set<Map.Entry<Recipe, RecipeType<?>>> entries()
	{
		return recipes.entrySet();
	}
}
