package ru.h4sv.chokedrop;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.Iterator;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Handler implements Listener
{
    public final HashMap<String, ItemStack[]> inv;
    private final Main plugin;
    
    public Handler(final Main plugin) {
        this.inv = new HashMap<String, ItemStack[]>();
        this.plugin = plugin;
    }
    
    public boolean isExpensive(final String itemType) {
        for (final String str : this.plugin.lines) {
            if (itemType.equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        this.inv.put(e.getEntity().getName(), e.getEntity().getInventory().getContents());
        e.getDrops().removeIf(stack -> !this.isExpensive(stack.getType().toString()));
    }
    
    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event) {
        final String playerName = event.getPlayer().getName();
        final ItemStack[] array;
        final ItemStack[] content = array = this.inv.get(playerName);
        for (final ItemStack stack : array) {
            if (stack != null && this.isExpensive(stack.getType().toString()))
                stack.setType(Material.AIR);
        }
        event.getPlayer().getInventory().setContents(content);
        this.inv.remove(event.getPlayer().getName());
    }
}
