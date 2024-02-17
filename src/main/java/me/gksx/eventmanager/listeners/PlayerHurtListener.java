package me.gksx.eventmanager.listeners;

import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHurtListener implements Listener {
    private final GameManager gameManager;

    public PlayerHurtListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    private void onPlayerHurt(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player hurt = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();
            if (!gameManager.playerList.containsKey(hurt) || !gameManager.playerList.containsKey(attacker)) return;
            Integer attackerTeam = gameManager.getPlayerManager().getPlayerTeam(attacker);
            Integer hurtTeam = gameManager.getPlayerManager().getPlayerTeam(hurt);
            if (attackerTeam.equals(hurtTeam) || gameManager.getGameState() == GameState.STARTING) {
                event.setCancelled(true);
            } // i hope this works lol, got no friends to test it lmao
        }
    }
}