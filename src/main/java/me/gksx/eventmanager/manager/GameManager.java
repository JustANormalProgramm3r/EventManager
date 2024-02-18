package me.gksx.eventmanager.manager;

import me.gksx.eventmanager.EventManager;
import me.gksx.eventmanager.tasks.GameStartCountdownTask;
import me.gksx.eventmanager.util.util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;

import static me.gksx.eventmanager.manager.GameState.ACTIVE;
import static me.gksx.eventmanager.manager.GameState.STARTING;

public class GameManager {
    public final HashMap<Player, Integer> playerList = new HashMap<>();
    public final HashMap<Integer, HashSet<Player>> teamList = new HashMap<>();
    private final EventManager plugin;
    private final BlockManager blockManager;
    private final PlayerManager playerManager;
    private GameState gameState = GameState.LOBBY;
    private GameStartCountdownTask gameStartCountdownTask;

    public GameManager(EventManager plugin) {
        this.plugin = plugin;

        this.playerManager = new PlayerManager(this);
        this.blockManager = new BlockManager(this);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == ACTIVE && gameState == STARTING) return;
        this.gameState = gameState;
        if (gameState.equals(ACTIVE)) {
            if (this.gameStartCountdownTask != null) this.gameStartCountdownTask.cancel();
            util.broadcast("Fight!");
            getPlayerManager().giveKits();
        } else if (gameState.equals(STARTING)) {
            playerManager.registerPlayers();
            playerManager.tpPlayersToGame();
            util.broadcast("Starting in 10 seconds!");
            this.gameStartCountdownTask = new GameStartCountdownTask(this);
            this.gameStartCountdownTask.runTaskTimer(plugin, 0, 20);
        } else if (gameState.equals(GameState.LOBBY)) {
            util.broadcast("Waiting for players..");
        } else if (gameState.equals(GameState.ENDING)) {
            util.broadcast("Game over!");
            playerManager.tpPlayersToLobby();
            teamList.clear();
            playerList.clear();
            Bukkit.getScheduler().runTaskLater(plugin, () -> setGameState(GameState.LOBBY), 100);
        }
    }

    public @NotNull Location getLobbyWorld() {
        World world = Bukkit.getServer().getWorld("world");
        return new Location(world, 0, 60, 0);
    }

    public Location getGameWorld() {
        World world = Bukkit.getServer().getWorld("game");
        return new Location(world, 0, -60, 0);
    }

    public void testPlayerSystem() {
        Bukkit.getLogger().info(String.valueOf(playerList.size()));
        playerList.forEach((player, integer) -> Bukkit.getLogger().info(player.toString()));
    }

    public void cleanup() {
        Bukkit.getLogger().info("sshhh im mewing..");
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
