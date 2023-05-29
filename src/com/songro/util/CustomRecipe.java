package com.songro.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomRecipe {
    public GetCustomRecipeString(String path) {
        return Main.getPlugin().getCustomRecipe().getString(path);
    }
}