package net.swedz.bclibjsonifier.mixin;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.swedz.bclibjsonifier.BCLibJsonifierProvider;
import org.betterx.bclib.recipes.BCLRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BCLRecipeManager.class)
public class InterceptRecipesMixin implements BCLibJsonifierProvider
{
	@Inject(at = @At("HEAD"), method = "addRecipe", cancellable = true)
	private static void addRecipe(RecipeType<?> type, Recipe recipe, CallbackInfo info)
	{
		mod.getRecipeTracker().track(type, recipe);
		
		info.cancel();
	}
}
