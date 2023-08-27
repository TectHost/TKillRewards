package minealex.tkillrewards.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Commands implements CommandExecutor {

    private final Plugin plugin;
    private final FileConfiguration config;

    public Commands(Plugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("tkr.version")) {
            String pluginName = ChatColor.translateAlternateColorCodes('&', plugin.getName());
            String pluginVersion = ChatColor.translateAlternateColorCodes('&', plugin.getDescription().getVersion());

            // Obtener el mensaje desde el config.yml
            String versionMessage = config.getString("messages.version-message");
            versionMessage = versionMessage.replace("%plugin%", pluginName);
            versionMessage = versionMessage.replace("%version%", pluginVersion);

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', versionMessage));
        } else {
            // Obtener el mensaje de falta de permiso desde el config.yml
            String noPermissionMessage = config.getString("messages.no-permission-message");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissionMessage));
        }
        return true;
    }
}
