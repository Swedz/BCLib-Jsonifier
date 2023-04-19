package net.swedz.bclibjsonifier.config;

import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public final class YamlDazzleConfHandler<C> extends ConfigurationHelper<C>
{
	private volatile C configData;
	
	private YamlDazzleConfHandler(Path configFolder, String fileName, ConfigurationFactory<C> factory)
	{
		super(configFolder, fileName, factory);
	}
	
	public static <C> YamlDazzleConfHandler<C> create(Path configFolder, String fileName, Class<C> configClass)
	{
		SnakeYamlOptions yamlOptions = new SnakeYamlOptions.Builder()
				.commentMode(CommentMode.fullComments())
				.build();
		return new YamlDazzleConfHandler<>(
				configFolder, fileName,
				SnakeYamlConfigurationFactory.create(
						configClass,
						ConfigurationOptions.defaults(),
						yamlOptions
				)
		);
	}
	
	public void reload()
	{
		try
		{
			configData = reloadConfigData();
		}
		catch (IOException ex)
		{
			throw new UncheckedIOException(ex);
		}
		catch (ConfigFormatSyntaxException ex)
		{
			configData = getFactory().loadDefaults();
			System.err.println("Uh-oh! The syntax of your configuration are invalid. Check your YAML syntax with a tool such as https://yaml-online-parser.appspot.com/");
			ex.printStackTrace();
			
		}
		catch (InvalidConfigException ex)
		{
			configData = getFactory().loadDefaults();
			System.err.println("Uh-oh! The values in your configuration are invalid. Check to make sure you have specified the right data types.");
			ex.printStackTrace();
		}
	}
	
	public C config()
	{
		if(configData == null)
		{
			throw new IllegalStateException("Configuration has not been loaded yet");
		}
		return configData;
	}
}
