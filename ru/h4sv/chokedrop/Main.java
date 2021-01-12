package ru.h4sv.chokedrop;

import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public List<String> lines;
    
    public void parseFile() {
        try {
            this.lines = Files.readAllLines(new File("ChokeDrop.txt").toPath(), StandardCharsets.UTF_8);
        }
        catch (IOException exc) {
            throw new RuntimeException("fail reading the equaled value file", exc);
        }
        this.getLogger().info("ChokeDrops.txt parsed!");
    }
    
    public void onEnable() {
        this.parseFile();
        Bukkit.getPluginManager().registerEvents((Listener)new Handler(this), (Plugin)this);
        this.getLogger().info("Enabled!");
    }
}
