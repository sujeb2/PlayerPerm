package com.songro.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerInfoNormal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender send, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (send.isOp()) {
            try {
                Player player = (Player) send;
                Player target = Bukkit.getPlayer(args[1]);
                boolean isOnline;
                var chatcolor = ChatColor.GREEN;
                var env = target.getWorld().getEnvironment();
                String pev = null;

                var x = target.getLocation().getX();
                var y = target.getLocation().getY();
                var z = target.getLocation().getZ();

                assert target != null;
                if(target.getPing() >= 1) chatcolor = ChatColor.GREEN;
                if(target.getPing() > 100) chatcolor = ChatColor.YELLOW;
                if(target.getPing() > 450) chatcolor = ChatColor.GOLD;
                if(target.getPing() > 500) chatcolor = ChatColor.RED;
                if(target.getWorld().getEnvironment() == World.Environment.NORMAL) pev = "오버월드";
                if(target.getWorld().getEnvironment() == World.Environment.NETHER) pev = "네더";
                if(target.getWorld().getEnvironment() == World.Environment.THE_END) pev = "엔드";

                if (args[0].equals("정보")) {
                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 플레이어가 존재하지 않거나, 서버에 들어온적이 없습니다.");
                    } else {
                        isOnline = Bukkit.getOnlinePlayers().contains(target);
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + " 님의 정보");
                        player.sendMessage(ChatColor.DARK_GRAY + "===============================");
                        if (!isOnline) player.sendMessage( ChatColor.DARK_GRAY + ": " + ChatColor.DARK_GRAY + "⏺ 오프라인");
                        else player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "⏺ 온라인");
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.RED + "♥ 체력: " + target.getHealth());
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.AQUA + "✧ 경험치: " + target.getExp());
                        //if(target.getBedSpawnLocation() == null) {
                        //    player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.DARK_GRAY + "스폰 위치: 존재하지 않음");
                        //} else player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "스폰 위치: X: " + target.getBedSpawnLocation().getX() + " ,Y: " + target.getBedSpawnLocation().getY() + " ,Z: " + target.getBedSpawnLocation().getZ());
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + chatcolor + "현재 핑: " + target.getPing() + "ms");
                        //player.sendMessage(ChatColor.DARK_GRAY + ": \uD83D\uDDE1 " + ChatColor.DARK_RED + "마지막으로 받은 데미지: " + (Math.round(target.getLastDamage())));
                        /*
                        if(pev == "오버월드") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.GREEN + pev + ChatColor.GRAY + "' 에 있는중");
                        } else if(pev == "네더") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.RED + pev + ChatColor.GRAY + "' 에 있는중");
                        } else if(pev == "엔드") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.DARK_GRAY + pev + ChatColor.GRAY + "' 에 있는중");
                        }
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 위치: X: " + (Math.round(x)) + " ,Y: " + (Math.round(y)) + " ,Z: " + (Math.round(z)));
                        */
                        String unixTimeStamp = String.valueOf(target.getLastPlayed());
                        long timestamp = Long.parseLong(unixTimeStamp);
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                        Date date = new Date();
                        date.setTime(timestamp);

                        String Datetime = sdf.format(date);
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + "마지막 접속 시간: " + ChatColor.GRAY + Datetime);
                    }
                } else if (args[0].equals("info")){
                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "[PlayerPerms] 플레이어가 존재하지 않거나, 서버에 들어온적이 없습니다.");
                    } else {
                        isOnline = Bukkit.getOnlinePlayers().contains(target);
                        player.sendMessage(ChatColor.GREEN + "[PlayerPerms] " + target.getDisplayName() + " 님의 정보");
                        player.sendMessage(ChatColor.DARK_GRAY + "===============================");
                        if (!isOnline) player.sendMessage( ChatColor.DARK_GRAY + ": " + ChatColor.DARK_GRAY + "⏺ 오프라인");
                        else player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "⏺ 온라인");
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.RED + "♥ 체력: " + target.getHealth());
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.AQUA + "✧ 경험치: " + target.getExp());
                        //if(target.getBedSpawnLocation() == null) {
                        //    player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.DARK_GRAY + "스폰 위치: 존재하지 않음");
                        //} else player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "스폰 위치: X: " + target.getBedSpawnLocation().getX() + " ,Y: " + target.getBedSpawnLocation().getY() + " ,Z: " + target.getBedSpawnLocation().getZ());
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + chatcolor + "현재 핑: " + target.getPing() + "ms");
                        //player.sendMessage(ChatColor.DARK_GRAY + ": \uD83D\uDDE1 " + ChatColor.DARK_RED + "마지막으로 받은 데미지: " + (Math.round(target.getLastDamage())));
                        /*
                        if(pev == "오버월드") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.GREEN + pev + ChatColor.GRAY + "' 에 있는중");
                        } else if(pev == "네더") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.RED + pev + ChatColor.GRAY + "' 에 있는중");
                        } else if(pev == "엔드") {
                            player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 '" + ChatColor.DARK_GRAY + pev + ChatColor.GRAY + "' 에 있는중");
                        }
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "현재 위치: X: " + (Math.round(x)) + " ,Y: " + (Math.round(y)) + " ,Z: " + (Math.round(z)));
                        */
                        String unixTimeStamp = String.valueOf(target.getLastPlayed());
                        long timestamp = Long.parseLong(unixTimeStamp);
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                        Date date = new Date();
                        date.setTime(timestamp);

                        String Datetime = sdf.format(date);
                        player.sendMessage(ChatColor.DARK_GRAY + ": " + "마지막 접속 시간: " + ChatColor.GRAY + Datetime);
                    }
                }
                return true;
            } catch (Exception e) {
                Player player = (Player) send;
                player.sendMessage(ChatColor.RED + "[PlayerPerms] 오류가 발생했습니다");
                player.sendMessage(ChatColor.YELLOW + "[PlayerPerms] 오류 로그: " + e + "\n[PlayerPerms] 오류코드: 0x13");
                return false;
            }
        } else {
            Player player = (Player) send;
            player.sendMessage(ChatColor.RED + "[PlayerPerms] 이 명령어는 OP만 사용 가능합니다.");
        }
        return false;
    }

}
