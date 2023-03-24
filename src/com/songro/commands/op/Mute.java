package com.songro.commands.op;

import com.songro.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Logger;

public class Mute implements CommandExecutor, Listener {
    boolean muted = false;

    public void MuteChat(Main main) {
        Objects.requireNonNull(main.getCommand("mute")).setExecutor(this);
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        Logger log = Bukkit.getLogger();

        try {
            if(sender instanceof ConsoleCommandSender || player.isOp()) {
                if (muted) {
                    muted = false;
                    Bukkit.broadcastMessage(ChatColor.GREEN + "[PlayerPerms] " + player.getDisplayName() + "에 의해 채팅이 풀어졌습니다.");
                } else {
                    muted = true;
                    Bukkit.broadcastMessage(ChatColor.GREEN + "[PlayerPerms] " + player.getDisplayName() + "에 의해 채팅이 막아졌습니다.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어를 사용할려면 OP 권한이 필요합니다.");
            }
        } catch (Exception e) {
            log.severe("오류가 발생했습니다");
            log.severe("오류 로그: " + e);
            log.severe("오류 코드: 0x100");
        }
        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if(muted) {
            if(p.hasPermission("def.mutebypass")) {
                p.sendMessage("not muted");
            } else {
                p.sendMessage("muted");
                e.setCancelled(true);
            }
         }
    }
}
