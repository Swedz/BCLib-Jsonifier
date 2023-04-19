package net.swedz.bclibjsonifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BCLibJsonifierProvider
{
	BCLibJsonifier       mod    = BCLibJsonifier.getInstance();
	Logger               logger = LoggerFactory.getLogger("bclibjsonifier");
	BCLibJsonifierConfig config = mod.getConfig();
}
