package me.gksx.eventmanager;

import org.bukkit.Bukkit;

public class util {
    public static void broadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });
    }
}
