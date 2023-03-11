package com.songro.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class oiie extends Event {
    private static final HandlerList hd = new HandlerList();
    public Player player;
    /*
    Shows Item Inventory
     */
    public oiie(Player player) {
        this.player = player;
    }

    public HandlerList getHandlers() {
        return hd;
    }

    public static HandlerList getHandlerList() {
        return hd;
    }
}
