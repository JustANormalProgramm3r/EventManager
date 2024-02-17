package me.gksx.eventmanager.manager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class PlayerManager {
    private final GameManager gameManager;

    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void giveKits() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getGameMode() == GameMode.SURVIVAL).forEach(this::giveKit);
    }

    public void addPlayer(Player name, Integer team) {
        if (!gameManager.playerList.containsKey(name)) {
            gameManager.playerList.put(name, team);
            Bukkit.getLogger().info("Player " + name.getName() + " added to the game!");
            return;
        }
        Bukkit.getLogger().info("Player " + name.getName() + " is already in the game!");
    }

    public void removePlayer(Player name) {
        gameManager.playerList.remove(name);
    }

    public void removeTeam(Integer team) {
        gameManager.teamList.remove(team);
    }

    public void setPlayerTeam(Player name, Integer team) {
        gameManager.playerList.replace(name, team);
        Bukkit.getLogger().info("Player " + name.getName() + " is now in team " + team);
    }

    public Integer getPlayerTeam(Player name) {
        return gameManager.playerList.get(name);
    }

    public void removePlayerFromTeam(@NotNull Player name) {
        if (gameManager.teamList.get(gameManager.playerList.get(name)).size() == 1) {
            gameManager.teamList.remove(gameManager.playerList.get(name));
        } else {
            HashSet<Player> newPlayers = gameManager.teamList.get(gameManager.playerList.get(name));
            newPlayers.remove(name);
            gameManager.teamList.replace(gameManager.playerList.get(name), newPlayers);
        }
    }

    public void addPlayerToTeam(Player name, Integer team) {
        if (!gameManager.teamList.containsKey(team)) {
            HashSet<Player> players = new HashSet<>();
            players.add(name);
            gameManager.teamList.put(team, players);
            return;
        }
        HashSet<Player> players = gameManager.teamList.get(team);
        players.add(name);
        gameManager.teamList.replace(team, players);
    }

    public HashSet<String> getPlayersInTeam(Integer team) {
        HashSet<String> players = new HashSet<>();
        gameManager.playerList.forEach((player, integer) -> {
            if (integer.equals(team)) players.add(player.getName());
        });
        return players;
    }

    public void registerPlayers() {
        Integer i = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            addPlayer(player, i);
            addPlayerToTeam(player, i);
            i++;
        }
    }

    public void tpPlayersToLobby() {
        if (gameManager.playerList.isEmpty()) {
            Bukkit.getLogger().info("Player list is empty!");
            return;
        }
        gameManager.playerList.forEach((player, integer) -> {
            Bukkit.getLogger().info(player.getName());
            player.teleport(gameManager.getLobbyWorld());
        });
    }

    public void tpPlayersToGame() {
        gameManager.playerList.forEach((player, integer) -> player.teleport(gameManager.getGameWorld()));
    }

    public boolean checkPlayer(Player player) {
        return gameManager.playerList.containsKey(player);
    }

    public void giveKit(@NotNull Player player) {
        player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
        player.getInventory().addItem(new ItemStack(Material.SHIELD));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 16));
    }
}
