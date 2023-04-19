# BCLib Jsonifier

*Record scratch freeze frame* Sooooo, yeah. I bet right now you're probably wondering how I got here, huh? Well it's a long story. Well, not so long. Basically BCLib is setup incorrectly with how it registers tags and recipes which has the **very** annoying sideaffect of KubeJS not being able to interact with its recipes and tags. Little ol' me still wanted to include BetterEnd and BetterNether in their modpack so I did the reasonable thing that any person would do... Write a whole ass mod that hijacks the BCLib registry system and grabs it by the throat and forces it to work correctly! Hope this is as useful to you as it is to me!

## Bad News

Now before you start getting all happy, lemme give some bad news. As it stands right now, this is not a one size fits all solution. Yes, this does fix recipes in their entire messiness. However, tags are still a little weird. There's a config that comes with BCLib Jsonifier, though it's not insanely extensive. It only lets you define what tags BCLib should not be able to add to any items, blocks, biomes, so on and so forth. If this works for you, great. If not, I'm so sorry. I tried my darndest to get tags fully working like recipes, but I gave up lol.

Anyway, here's the default config. It's located at `./config/bclib-jsonifier.yml`.

```
blocked_tags:
- c:iron_ingots
```

This does exactly what you think it'd do. It blocks BCLib from adding `c:iron_ingots` to anything (item, block, what have you). This works very well for people who want to keep things like Cincinnasnite Ingots from BetterNether or Thallasium Ingots from BetterEnd but don't want them to be used as an iron equivalent. Works for my case, hope it works for yours.

## Contributing

If you'd like to contribute, go ahead! Make a fork and PR it! A few general requests I have for PRs though:
- Keep the formatting as-is and any new code added should follow the conventions and formatting
- Reach out to me on my [Discord](https://discord.gg/vNaqDzSNaB) server (#coding-stuff channel preferably) so we can collaborate easier on the changes

## Potential Future Features

- Full tag fix similar to recipes

## License

BCLib Jsonifier is available under the MIT license. Do whatever your little heart desires, I don't really care all too much. Just give a little smile my way, would ya?
