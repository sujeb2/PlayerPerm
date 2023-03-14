package com.songro.commands.op;

import com.songro.handler.AFK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IsAFK implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {


        if(sender instanceof Player player){

            if(args.length == 0){
                if(AFK.isAFK(player)){
                    player.sendMessage(ChatColor.BLUE + "[PlayerPerms] 현재 잠수중입니다.");
                }else{
                    player.sendMessage(ChatColor.DARK_GRAY + "[PlayerPerms] 잠수중이 아닙니다.");
                }
            } else {
                if(AFK.isAFK(Bukkit.getPlayerExact(args[0]))){
                    player.sendMessage(ChatColor.BLUE + "[PlayerPerms] 현재 " + args[0] +"님은 잠수중입니다");
                }else{
                    player.sendMessage(ChatColor.DARK_GRAY + "[PlayerPerms] 현재 " + args[0] +"님은 잠수중이 아닙니다.");
                }
            }

        }

        return true;
    }
}