package someguy.postmortem;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Postmortem extends JavaPlugin implements Listener
{
	private boolean notifyOwnDeath;
	private boolean notifyOwnKill;
	private boolean log;
	private String msgPlayerFormat;
	private String msgOtherFormat;

	public void onEnable()
	{
		saveDefaultConfig();
		notifyOwnDeath = getConfig().getBoolean("notifyOwnDeath");
		notifyOwnKill = getConfig().getBoolean("notifyOwnKill");
		log = getConfig().getBoolean("log");
		msgPlayerFormat = getConfig().getString("playerFormat");
		msgOtherFormat = getConfig().getString("nonPlayerFormat");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		if (event.getEntity().hasPermission("postmortem.notify"))
		{
			String victim = event.getEntity().getName();
			String killer = "";
			Location coords = event.getEntity().getLocation();
			String location = "X: " + coords.getBlockX() + " Y: " + coords.getBlockY() + " Z: " + coords.getBlockZ();
			String world = coords.getWorld().getName();
			String msg;
			if (event.getEntity().getKiller() != null)
			{
				killer = event.getEntity().getKiller().getName();
				msg = msgPlayerFormat.replaceAll("\\{VICTIM\\}", victim).replaceAll("\\{KILLER\\}", killer).replaceAll("\\{LOCATION\\}",location).replaceAll("\\{WORLD\\}", world).replaceAll("&", "§");
			}
			else
				msg = msgOtherFormat.replaceAll("\\{VICTIM\\}", victim).replaceAll("\\{LOCATION\\}",location).replaceAll("\\{WORLD\\}", world).replaceAll("&", "§");
			for (Player player : getServer().getOnlinePlayers())
			{
				if(player.hasPermission("postmortem.receive") && !killer.isEmpty())
				{
					if ((notifyOwnDeath || !(player.getName().equals(victim))) && (notifyOwnKill || !(player.getName().equals(killer))))
					{
						player.sendMessage(msg);
					}
				}
				else if (player.hasPermission("postmortem.receive"))
				{
					if ((notifyOwnDeath || !(player.getName().equals(victim))))
					{
						player.sendMessage(msg);
					}
				}
			}
			
			if (log)
				getLogger().info(msg);
		}
	}
}
