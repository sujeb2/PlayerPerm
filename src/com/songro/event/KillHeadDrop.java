package com.songro.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class KillHeadDrop implements Listener {

    Logger log = Bukkit.getLogger();

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        try {
            if (e.getEntity() != null) {
                if (Objects.requireNonNull(e.getEntity().getKiller()).hasPermission("perks.plus") || e.getEntity().getKiller().hasPermission("perks.pp") || e.getEntity().getKiller().hasPermission("perks.op") || e.getEntity().getKiller().hasPermission("perks.ultra") || (e.getEntity().getKiller() != null)) {
                    try {
                        if (e.getEntity().getKiller().hasPermission("perks.plus") || e.getEntity().getKiller().hasPermission("perks.pp") || e.getEntity().getKiller().hasPermission("perks.op") || e.getEntity().getKiller().hasPermission("perks.ultra") || e.getEntity().getKiller() != null) {
                            ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                            SkullMeta sm = (SkullMeta) item.getItemMeta();
                            ArrayList<String> lore = new ArrayList<>();
                            assert sm != null;
                            sm.setOwner((e.getEntity().getName()));
                            if (e.getEntity().getKiller() != null) {
                                lore.add(e.getEntity().getKiller() + "(이)가 죽인 " + e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
                            } else {
                                lore.add(ChatColor.GRAY + e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
                            }
                            sm.setLore(lore);
                            item.setItemMeta(sm);

                            e.getDrops().add(item);
                        }
                    } catch (NullPointerException npe) {
                        ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                        SkullMeta sm = (SkullMeta) item.getItemMeta();
                        ArrayList<String> lore = new ArrayList<>();
                        assert sm != null;
                        sm.setOwner((e.getEntity().getName()));
                        lore.add(e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
                        sm.setLore(lore);
                        item.setItemMeta(sm);

                        e.getDrops().add(item);
                    }
                } else {
                    ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                    SkullMeta sm = (SkullMeta) item.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    assert sm != null;
                    sm.setOwner((e.getEntity().getName()));
                    lore.add(e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
                    sm.setLore(lore);
                    item.setItemMeta(sm);

                    e.getDrops().add(item);
                }
            } else {
                ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                SkullMeta sm = (SkullMeta) item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                assert sm != null;
                sm.setOwner((e.getEntity().getName()));
                lore.add(e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
                sm.setLore(lore);
                item.setItemMeta(sm);

                e.getDrops().add(item);
            }
        } catch (NullPointerException npe2) {
            ItemStack item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
            SkullMeta sm = (SkullMeta) item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            assert sm != null;
            sm.setOwner((e.getEntity().getName()));
            lore.add(e.getEntity().getDisplayName() + ChatColor.GRAY + "의 머리이다.");
            sm.setLore(lore);
            item.setItemMeta(sm);

            e.getDrops().add(item);
        }
    }
}