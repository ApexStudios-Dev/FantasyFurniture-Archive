# ForgeTemplate

A simple template based off of the [MDK](https://github.com/MinecraftForge/MinecraftForge/tree/1.16.x/mdk) that I use to create new projects quicker by extracting all mod-related info to `gradle.properties`.
**This does not do the work for you.** It only makes the initial Gradle setup easier and less tedious.

## Setting up
* Open up `gradle.properties` and change all the necessary properties
* Rename the main package to be the same as `mod_base_package` in the properties file, e.g. `xyz.apex.forge.template`
* Rename the mixins config file (`src/main/resources/template.mixins.json`) to match `src/main/resources/<mod_id>.mixins.json`

Finally, import into your IDE of choice.