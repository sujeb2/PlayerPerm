package com.songro.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.logging.Logger;

import static com.songro.Main.plugin;

public class tippedFloatingArrow {

    public void tippedFloatingArrow() {
        Logger log = Bukkit.getLogger();
        int getTime = plugin.getCustomConfig().getInt("tippedArrowTime.floatingArrow");

        try {
            ItemStack tippedFloatingArrow = new ItemStack(Material.TIPPED_ARROW, 1);
            PotionMeta tfap = (PotionMeta) tippedFloatingArrow.getItemMeta();
            tfap.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * getTime, 0), true);
            tfap.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "공중 부양의 화살");
            tfap.setLore(List.of(ChatColor.AQUA + "화살을 맞으면 30초 동안 공중부양 효과를 얻습니다."));

            tippedFloatingArrow.setItemMeta(tfap);
            {
                NamespacedKey key = new NamespacedKey(plugin, "tfa");
                ShapedRecipe recipe = new ShapedRecipe(key, tippedFloatingArrow);
                recipe.shape("Z",
                        "X",
                        "Z");
                recipe.setIngredient('X', Material.ARROW);
                recipe.setIngredient('Z', Material.SHULKER_SHELL);

                Bukkit.addRecipe(recipe);
            }
        } catch (NullPointerException npe) {
            log.severe("레시피 파일이 잘못되었거나, 오류가 났습니다.");
            log.severe("오류 로그: " + npe);
        }
    }

}
