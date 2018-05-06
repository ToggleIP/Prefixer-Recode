package nl.AverageKevin.Prefixer;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class main
  extends JavaPlugin
{
  public static main getInstance;
  
  public void onEnable()
  {
    getInstance = this;
    
    ConfigHandler.getInstance().createConfig();
    
    getCommand("prefix").setExecutor(new Prefix());
    Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
  }
  
  public void onDisable() {}
}
