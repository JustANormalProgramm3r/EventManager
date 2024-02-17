package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.manager.GameManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpectateCommand implements CommandExecutor {
    private final GameManager gameManager;

    public SpectateCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            p.teleport(gameManager.getGameWorld());
            p.setGameMode(GameMode.SPECTATOR);
        }
        return true;
    }
}
