package net.swedz.bclibjsonifier;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.recipe.Recipe;
import net.swedz.bclibjsonifier.config.YamlDazzleConfHandler;
import net.swedz.bclibjsonifier.recipe.RecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.RecipeTracker;
import net.swedz.bclibjsonifier.recipe.bclib.AlloyingRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.bclib.AnvilRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.betterend.InfusionRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.mc.CookingRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.mc.CraftingShapedRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.mc.CraftingShapelessRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.mc.SmithingRecipeJsonifier;
import net.swedz.bclibjsonifier.recipe.mc.StonecuttingRecipeJsonifier;

import java.util.Optional;
import java.util.Set;

public final class BCLibJsonifier implements PreLaunchEntrypoint
{
	@Override
	public void onPreLaunch()
	{
		instance = this;
		
		this.setupConfig();
		
		recipeTracker = new RecipeTracker();
		
		this.setupJsonifiers();
	}
	
	private static BCLibJsonifier instance;
	
	public static BCLibJsonifier getInstance()
	{
		return instance;
	}
	
	private BCLibJsonifierConfig config;
	
	public BCLibJsonifierConfig getConfig()
	{
		return config;
	}
	
	private void setupConfig()
	{
		YamlDazzleConfHandler<BCLibJsonifierConfig> configHandler = YamlDazzleConfHandler.create(
				FabricLoader.getInstance().getConfigDir(), "bclib-jsonifier.yml",
				BCLibJsonifierConfig.class
		);
		configHandler.reload();
		config = configHandler.config();
	}
	
	private RecipeTracker recipeTracker;
	
	public RecipeTracker getRecipeTracker()
	{
		return recipeTracker;
	}
	
	private Set<RecipeJsonifier> recipeJsonifiers;
	
	public void registerJsonifier(RecipeJsonifier jsonifier)
	{
		recipeJsonifiers.add(jsonifier);
	}
	
	public Optional<RecipeJsonifier> getRecipeJsonifier(Recipe recipe)
	{
		return recipeJsonifiers.stream()
				.filter((jsonifier) -> jsonifier.recipeClass().isAssignableFrom(recipe.getClass()))
				.findFirst();
	}
	
	public Optional<JsonObject> jsonify(Recipe recipe)
	{
		return getRecipeJsonifier(recipe).map((j) -> j.create(recipe));
	}
	
	private void setupJsonifiers()
	{
		recipeJsonifiers = Sets.newHashSet();
		
		this.registerJsonifier(new CraftingShapedRecipeJsonifier());
		this.registerJsonifier(new CraftingShapelessRecipeJsonifier());
		this.registerJsonifier(new CookingRecipeJsonifier());
		this.registerJsonifier(new SmithingRecipeJsonifier());
		this.registerJsonifier(new StonecuttingRecipeJsonifier());
		
		this.registerJsonifier(new AlloyingRecipeJsonifier());
		this.registerJsonifier(new AnvilRecipeJsonifier());
		
		if(FabricLoader.getInstance().isModLoaded("betterend"))
		{
			this.registerJsonifier(new InfusionRecipeJsonifier());
		}
	}
}
