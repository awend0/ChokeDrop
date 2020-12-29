package ru.h4sv.chokestats;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private Main plugin;
    public Commands(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length > 1)
            return false;
        Player p = null;
        if (!(sender instanceof Player) && args.length == 0)
        {
            sender.sendMessage("Петрович, иди  нахуй");
            return true;
        }
        if (args.length == 0)
            p = Bukkit.getPlayer(sender.getName());
        if (args.length == 1)
            p = Bukkit.getPlayer(args[0]);
        if (p == null)
        {
            sender.sendMessage("Игрок " + args[0] + " не онлайн или не существует");
            return true;
        }
        sender.sendMessage("Статистика игрока " + p.getName());
        sender.sendMessage("Уровень: " + p.getLevel());
        sender.sendMessage("Здоровье: " + (int)p.getHealth() + " / " + (int)(20 + p.getAbsorptionAmount()));
        sender.sendMessage("Местоположение: " + (int)p.getLocation().getX() + " " + (int)p.getLocation().getY() + " " + (int)p.getLocation().getZ());
        int arrowsInBody = p.getArrowsInBody();
        if (arrowsInBody > 0)
        {
            String arrowsMsg = "Кстати, в нём сейчас " + arrowsInBody;
            arrowsInBody %= 10;
            if (arrowsInBody == 1)
                arrowsMsg += " стрела";
            else if (arrowsInBody >= 2 && arrowsInBody <= 4)
                arrowsMsg += " стрелы";
            else
                arrowsMsg += " стрел";
            arrowsMsg += " :)";
            sender.sendMessage(arrowsMsg);
        }
        return true;
    }
}
