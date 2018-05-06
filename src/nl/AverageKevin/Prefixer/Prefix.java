package nl.AverageKevin.Prefixer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Prefix
  implements CommandExecutor
{
  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
  {
    if (!(cs instanceof Player))
    {
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        ConfigHandler.getInstance().Console_Not_Allowed());
      return true;
    }
    Player p = (Player)cs;
    if (!cs.hasPermission("prefixer.use"))
    {
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        ConfigHandler.getInstance().noPermission());
      
      return true;
    }
    if (args.length == 0)
    {
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        "§c/prefix <Prefix> | /prefix [-u] <Target Player> <Prefix>");
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        "§cPlugin made by §4AverageKevin");
      
      return true;
    }
    if ((args.length >= 1) && (!args[0].equalsIgnoreCase("-u")))
    {
      String prefix = "";
      for (int i = 0; i < args.length; i++) {
        prefix = prefix + args[i].replace("&", "§") + " ";
      }
      StringBuilder strb = new StringBuilder();
      strb.append(prefix);
      
      prefix = strb.deleteCharAt(strb.length() - 1).toString();
      
      ConfigHandler.getInstance().setPrefix(p, prefix);
      
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        "§aYour prefix have been changed to §e" + 
        prefix.replace("&", "§"));
      
      return true;
    }
    if ((args.length >= 3) && (args[0].equalsIgnoreCase("-u")))
    {
      Player target = Bukkit.getPlayer(args[1]);
      if (target != null)
      {
        String prefix = "";
        for (int i = 2; i < args.length; i++) {
          prefix = prefix + args[i].replace("&", "§") + " ";
        }
        StringBuilder strb = new StringBuilder();
        strb.append(prefix);
        
        prefix = strb.deleteCharAt(strb.length() - 1).toString();
        
        ConfigHandler.getInstance().setPrefix(target, prefix);
        
        cs.sendMessage(ConfigHandler.getInstance().getPrefix() + "§e" + 
          target.getName() + 
          "'s §6Prefix have been changed to: §c" + 
          prefix.replace("&", "§"));
        target.sendMessage(ConfigHandler.getInstance().getPrefix() + 
          "§e" + cs.getName() + 
          " §6has changed your prefix to: §c" + 
          prefix.replace("&", "§"));
        
        return true;
      }
      cs.sendMessage(ConfigHandler.getInstance().getPrefix() + 
        ConfigHandler.getInstance().notOnline());
      
      return true;
    }
    return false;
  }
}