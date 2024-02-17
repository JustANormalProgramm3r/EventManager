package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {

    private final GameManager gameManager;

    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("eventmanager.start")) {
                p.sendMessage("You're not allowed to do that lil bro");
                return true;
            }
            gameManager.setGameState(GameState.STARTING);

        }
        return true;
    }
}
