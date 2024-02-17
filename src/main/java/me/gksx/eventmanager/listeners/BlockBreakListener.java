package me.gksx.eventmanager.listeners;

import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.manager.GameState;
import me.gksx.eventmanager.manager.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public BlockBreakListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getBlockManager().canBreak(event.getBlock()) && gameManager.getGameState().equals(GameState.ACTIVE) && playerManager.checkPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
