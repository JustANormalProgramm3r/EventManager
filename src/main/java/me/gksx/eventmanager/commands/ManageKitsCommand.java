package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.EventManager;
import me.gksx.eventmanager.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class ManageKitsCommand implements CommandExecutor {

    private final GameManager gameManager;

    public ManageKitsCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 27, ". Kit Manager .");

        ItemStack getEmeraldButton = new ItemStack(Material.EMERALD);
        ItemMeta ebMeta = getEmeraldButton.getItemMeta();
        ebMeta.setDisplayName(ChatColor.GREEN + "Create new kit");

        ItemStack getRedstoneButton = new ItemStack(Material.REDSTONE);
        ItemMeta ebMeta2 = getRedstoneButton.getItemMeta();
        ebMeta2.setDisplayName(ChatColor.GREEN + "Delete Kit");

        ItemStack getGoldenButton = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta ebMeta3 = getGoldenButton.getItemMeta();
        ebMeta3.setDisplayName(ChatColor.GREEN + "Change active kit");


        inventory.setItem(11, getEmeraldButton);
        inventory.setItem(13, getGoldenButton);
        inventory.setItem(15, getRedstoneButton);

        player.openInventory(inventory);
        player.setMetadata("OpenedMenu", new FixedMetadataValue(EventManager.getInstance(), inventory));

        return true;
    }
}
