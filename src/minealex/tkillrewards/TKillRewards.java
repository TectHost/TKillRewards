package minealex.tkillrewards;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TKillRewards extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = (Player) event.getEntity().getKiller();

            if (event.getEntity() instanceof Monster) {
                List<String> mobCommands = getConfig().getStringList("kill_rewards.mob_kill_commands");

                for (String command : mobCommands) {
                    String formattedCommand = command.replace("%player%", player.getName());
                    getServer().dispatchCommand(getServer().getConsoleSender(), formattedCommand);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getKiller() instanceof Player) {
            Player killer = player.getKiller();
            
            List<String> playerCommands = getConfig().getStringList("kill_rewards.player_kill_commands");

            for (String command : playerCommands) {
                String formattedCommand = command.replace("%player%", killer.getName()).replace("%victim%", player.getName());
                getServer().dispatchCommand(getServer().getConsoleSender(), formattedCommand);
            }
        }
    }
}
