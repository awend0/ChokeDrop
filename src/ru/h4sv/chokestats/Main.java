package ru.h4sv.chokestats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin
{
    Logger log = Logger.getLogger("Minecraft");

    public void onEnable()
    {
        getCommand("stats").setExecutor(new Commands(this));
        getLogger().info("Enabled!");
    }
}
