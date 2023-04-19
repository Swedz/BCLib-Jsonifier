package net.swedz.bclibjsonifier.mixin;

import net.minecraft.tag.TagKey;
import net.swedz.bclibjsonifier.BCLibJsonifierProvider;
import org.betterx.worlds.together.tag.v3.TagRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TagRegistry.class)
public class InterceptTagsMixin implements BCLibJsonifierProvider
{
	@Inject(at = @At("HEAD"), method = "add(Lnet/minecraft/tag/TagKey;[Ljava/lang/Object;)V", cancellable = true)
	private void add(TagKey<?> id, Object[] elements, CallbackInfo info)
	{
		if(config.blockedTags().contains(id.id().toString()))
		{
			info.cancel();
		}
	}
}