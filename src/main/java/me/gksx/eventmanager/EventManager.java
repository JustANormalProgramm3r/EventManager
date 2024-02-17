package me.gksx.eventmanager;

import me.gksx.eventmanager.commands.*;
import me.gksx.eventmanager.listeners.BlockBreakListener;
import me.gksx.eventmanager.listeners.DeathListener;
import me.gksx.eventmanager.listeners.PlayerHurtListener;
import me.gksx.eventmanager.listeners.PlayerKitManagerListener;
import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.PlayerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EventManager extends JavaPlugin {

    private static EventManager instance;
    private GameManager gameManager;


    public static EventManager getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        this.gameManager = new GameManager(this);
        PlayerManager playerManager = new PlayerManager(gameManager);
        Objects.requireNonNull(getCommand("startEvent")).setExecutor(new StartCommand(gameManager));
        Objects.requireNonNull(getCommand("stopEvent")).setExecutor(new StopCommand(gameManager));
        Objects.requireNonNull(getCommand("spectate")).setExecutor(new SpectateCommand(gameManager));
        Objects.requireNonNull(getCommand("switchTeam")).setExecutor(new SwitchTeamCommand(gameManager));
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand(gameManager));
        Objects.requireNonNull(getCommand("getTeam")).setExecutor(new GetTeamCommand(gameManager));
        Objects.requireNonNull(getCommand("manageKits")).setExecutor(new ManageKitsCommand(gameManager));
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager, playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerHurtListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerKitManagerListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(gameManager), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        gameManager.cleanup();
    }

}
