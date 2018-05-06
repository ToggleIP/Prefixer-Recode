package nl.AverageKevin.Prefixer;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigHandler
{
  private static ConfigHandler instance = null;
  
  public static ConfigHandler getInstance()
  {
    if (instance == null) {
      instance = new ConfigHandler();
    }
    return instance;
  }
  
  public String getPlayerPrefix(Player p)
  {
    File file = new File("plugins//" + main.getInstance.getName() + "//Players", p.getName() + ".yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    if (!file.exists()) {
      return "";
    }
    return cfg.getString("prefix").replace("&", "§");
  }
  
  public String getPrefix()
  {
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    return cfg.getString("prefix").replace("&", "§");
  }
  
  public String Console_Not_Allowed()
  {
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    return cfg.getString("messages.Console_Not_Allowed").replace("&", "§");
  }
  
  public String noPermission()
  {
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    return cfg.getString("messages.noPermission").replace("&", "§");
  }
  
  public String notOnline()
  {
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    return cfg.getString("messages.notOnline").replace("&", "§");
  }
  
  public void createConfig()
  {
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    if (!file.exists())
    {
      cfg.set("prefix", "&7[&cPrefixer&7]");
      
      cfg.set("format", "{prefix} &a{player}&7: {message}");
      cfg.set("ChatManager", Boolean.valueOf(false));
      
      cfg.set("messages.Console_Not_Allowed", "&cYou cant use this from Console");
      cfg.set("messages.noPermission", "&cYou dont have enough permissions to perform this command.");
      cfg.set("messages.notOnline", "&cThe Player is not Online!");
      try
      {
        cfg.save(file);
      }
      catch (IOException localIOException) {}
    }
    if (!cfg.isSet("ChatManager"))
    {
      cfg.set("ChatManager", Boolean.valueOf(false));
      try
      {
        cfg.save(file);
      }
      catch (IOException localIOException1) {}
    }
  }
  
  public void setPrefix(Player p, String prefix)
  {
    File file = new File("plugins//" + main.getInstance.getName() + "//Players", p.getName() + ".yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    cfg.set("prefix", prefix);
    try
    {
      cfg.save(file);
    }
    catch (Exception localException) {}
  }
}