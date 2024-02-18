package me.gksx.eventmanager.commands;

import me.gksx.eventmanager.EventManager;
import me.gksx.eventmanager.manager.GameManager;
import me.gksx.eventmanager.util.util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ManageKitsCommand implements CommandExecutor {

    public final String invName = Objects.requireNonNull(EventManager.getInstance().getConfig().get("MenuName")).toString();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 27, ". Kit Manager .");

        ItemStack getEmeraldButton = util.getItem(new ItemStack(Material.EMERALD), "Create Kit");
        ItemStack getRedStoneButton = util.getItem(new ItemStack(Material.DIAMOND), "Change Active Kit");
        ItemStack getGoldenButton = util.getItem(new ItemStack(Material.REDSTONE), "Delete Kit");

        inventory.setItem(11, getEmeraldButton);
        inventory.setItem(13, getGoldenButton);
        inventory.setItem(15, getRedStoneButton);

        player.openInventory(inventory);
        player.setMetadata("OpenedMenu", new FixedMetadataValue(EventManager.getInstance(), ". Kit Manager ."));

        return true;
    }
}
