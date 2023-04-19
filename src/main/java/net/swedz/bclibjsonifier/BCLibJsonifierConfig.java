package net.swedz.bclibjsonifier;

import com.google.common.collect.Lists;
import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

import java.util.List;

public interface BCLibJsonifierConfig
{
	static List<String> defaultBlockedTags()
	{
		return Lists.newArrayList("c:iron_ingots");
	}
	
	@ConfKey("blocked_tags")
	@ConfComments({"The list of tags to block BCLib from adding to items or blocks or anything."})
	@ConfDefault.DefaultObject("defaultBlockedTags")
	List<String> blockedTags();
}
