package me.gksx.eventmanager.listeners;

import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.GameState;
import me.gksx.eventmanager.util;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
    private final GameManager gameManager;

    public DeathListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent e) {
        if (gameManager.playerList.containsKey(e.getPlayer())) {
            e.setCancelled(true);
            e.getDrops().add(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
            e.getDrops().forEach(drop -> e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), drop));
            gameManager.getPlayerManager().removePlayerFromTeam(e.getPlayer());
            gameManager.getPlayerManager().removePlayer(e.getPlayer());
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            util.broadcast(e.getEntity().getName() + " has lost!");
            e.getPlayer().getLocation().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
            gameManager.teamList.forEach((integer, strings) -> {
                if (strings.isEmpty()) {
                    gameManager.getPlayerManager().removeTeam(integer);
                }
            });
            if (gameManager.teamList.size() == 1) {
                util.broadcast("Game over! Team number " + gameManager.getPlayerManager().getPlayerTeam(e.getPlayer()) + " has won!");
                util.broadcast("The following players were in the winning team:" + gameManager.getPlayerManager().getPlayersInTeam(gameManager.getPlayerManager().getPlayerTeam(e.getPlayer())));
                util.broadcast("You can do /lobby");
                gameManager.setGameState(GameState.ENDING);
            } else if (gameManager.teamList.isEmpty()) {
                util.broadcast("Game over! " + e.getEntity().getName() + " has won!");
                util.broadcast("You can do /lobby");
                gameManager.setGameState(GameState.ENDING);
            } else {
                util.broadcast("There are " + gameManager.teamList.size() + " teams left!");
            }
        }
    }
}
