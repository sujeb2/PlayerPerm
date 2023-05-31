package com.songro.util;

import com.songro.Main;

public class CustomRecipe {
    public GetCustomRecipeString(String path) {
        return Main.getPlugin().getCustomRecipe().getString(path);
    }
}