package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SwitchTeamCommand implements CommandExecutor {
    private final GameManager gameManager;

    public SwitchTeamCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!gameManager.playerList.containsKey(p)) {
                p.sendMessage("You're not in the game!");
                return true;
            }
            gameManager.getPlayerManager().setPlayerTeam(p, Integer.parseInt(strings[0]));
            Bukkit.getLogger().info(gameManager.playerList.get(p).toString());
        }
        return true;
    }
}
