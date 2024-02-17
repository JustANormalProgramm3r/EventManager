package me.gksx.eventmanager.manager;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {
    private final Set<Material> allowedToBreak = new HashSet<>();

    public BlockManager(GameManager gameManager) {
        allowedToBreak.add(Material.OAK_LOG);
        allowedToBreak.add(Material.BIRCH_LOG);
        allowedToBreak.add(Material.SPRUCE_LOG);
        allowedToBreak.add(Material.JUNGLE_LOG);
        allowedToBreak.add(Material.ACACIA_LOG);
        allowedToBreak.add(Material.DARK_OAK_LOG);
        allowedToBreak.add(Material.OAK_LEAVES);
        allowedToBreak.add(Material.BIRCH_LEAVES);
        allowedToBreak.add(Material.SPRUCE_LEAVES);
        allowedToBreak.add(Material.JUNGLE_LEAVES);
        allowedToBreak.add(Material.ACACIA_LEAVES);
        allowedToBreak.add(Material.DARK_OAK_LEAVES);
    }

    public boolean canBreak(Block block) {
        return allowedToBreak.contains(block.getType());
    }

}
