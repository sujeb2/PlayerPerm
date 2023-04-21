package com.songro.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomRecipe {
    private List<String> recipeLines = new ArrayList<>();

    public CustomRecipe(File configFile) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        for (int i = 1; i <= 9; i++) {
            String line = config.getString("line" + i);
            if (line == null) {
                line = "AIR";
            }
            recipeLines.add(line);
        }
    }

    public List<String> getRecipeLines() {
        return recipeLines;
    }
}