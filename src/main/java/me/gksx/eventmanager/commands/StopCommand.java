package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StopCommand implements CommandExecutor {

    private final GameManager gameManager;

    public StopCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender.hasPermission("eventmanager.stop")) {
            gameManager.setGameState(GameState.ENDING);
        }

        return true;
    }
}
