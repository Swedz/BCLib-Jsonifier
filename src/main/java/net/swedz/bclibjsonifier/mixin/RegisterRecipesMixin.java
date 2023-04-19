package net.swedz.bclibjsonifier.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.swedz.bclibjsonifier.BCLibJsonifierProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Optional;

@Mixin(RecipeManager.class)
public class RegisterRecipesMixin implements BCLibJsonifierProvider
{
	@Inject(at = @At("HEAD"), method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V")
	private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo callback)
	{
		int count = 0;
		
		for(Map.Entry<Recipe, RecipeType<?>> entry : mod.getRecipeTracker().entries())
		{
			final Recipe recipe = entry.getKey();
			final Optional<JsonObject> jsonOptional = mod.jsonify(recipe);
			
			if(jsonOptional.isPresent())
			{
				final JsonObject json = jsonOptional.get();
				
				map.put(recipe.getId(), json);
				count++;
				
				logger.debug("[BCLibJsonifier] Registered BCLib recipe '{}' with json {}", recipe.getId(), json);
			}
			else
			{
				logger.error("[BCLibJsonifier] Failed to jsonify BCLib recipe '{}'", recipe.getId());
			}
		}
		
		logger.info("[BCLibJsonifier] Successfully loaded {} BCLib recipes into the recipe registry using json! No thanks to the losers over at BCLib, lmao.", count);
	}
}
