package net.swedz.bclibjsonifier.recipe.betterend;

import com.google.gson.JsonObject;
import net.minecraft.recipe.Ingredient;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;
import org.betterx.bclib.util.ItemUtil;
import org.betterx.betterend.recipe.builders.InfusionRecipe;

import java.util.List;

public final class InfusionRecipeJsonifier implements RecipeJsonifier<InfusionRecipe>
{
	@Override
	public Class<InfusionRecipe> recipeClass()
	{
		return InfusionRecipe.class;
	}
	
	@Override
	public JsonObject create(InfusionRecipe recipe)
	{
		JsonObject json = new JsonObject();
		
		json.addProperty("type", "betterend:infusion");
		
		json.add("input", recipe.getIngredients().get(0).toJson());
		
		json.add("result", recipe.getOutput().toJsonJS());
		
		json.addProperty("group", "infusion");
		
		json.addProperty("time", recipe.getInfusionTime());
		
		JsonObject catalysts = new JsonObject();
		String[] directions = new String[] {"north", "north_east", "east", "south_east", "south", "south_west", "west", "north_west"};
		List<Ingredient> catalystIngredients = recipe.getIngredients().subList(1, recipe.getIngredients().size());
		int index = 0;
		for(Ingredient ingredient : catalystIngredients)
		{
			if(ingredient.isEmpty())
			{
				continue;
			}
			catalysts.addProperty(directions[index++], ItemUtil.toStackString(ingredient.getMatchingStacks()[0]));
		}
		json.add("catalysts", catalysts);
		
		return json;
	}
}
