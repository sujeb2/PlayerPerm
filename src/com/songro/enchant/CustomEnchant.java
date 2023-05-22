package com.songro.enchant;

import com.songro.util.CustomEnchantWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CustomEnchant {
    static Logger log = Bukkit.getLogger();

    public static final Enchantment TELEPORT = new CustomEnchantWrapper("teleport", "Teleport", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPORT);

        if (!registered) {
            registerCustomEnchantment(TELEPORT);
        }
    }

    public static void registerCustomEnchantment(Enchantment enchantment) {
        boolean registered = true;
        log.info("[PlayerPerms] 인첸트 등록중...");
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }

        if(registered) {
            log.info(ChatColor.GREEN + "[PlayerPerms] 인첸트 등록 성공");
        }
    }

}
