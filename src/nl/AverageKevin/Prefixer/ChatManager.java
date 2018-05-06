package nl.AverageKevin.Prefixer;

import java.io.File;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@SuppressWarnings("unused")
public class ChatManager
  implements Listener
{
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e)
  {
    Player p = e.getPlayer();
    
    File file = new File("plugins//" + main.getInstance.getName(), "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    
    String format = cfg.getString("format");
    
    format = format.replace("{prefix}", ConfigHandler.getInstance().getPlayerPrefix(p));
    format = format.replace("{player}", p.getName());
    format = format.replace("{message}", e.getMessage());
    format = format.replace("{format}", e.getFormat());
    format = format.replace("{world}", p.getWorld().getName());
    format = format.replace("{level}", String.valueOf(p.getLevel()));
    format = format.replace("&", "§");
    
    e.setFormat(format);
  }
}
