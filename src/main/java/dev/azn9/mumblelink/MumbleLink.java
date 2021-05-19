package dev.azn9.mumblelink;

import dev.azn9.mumblelink.command.MumbleCommand;
import dev.azn9.mumblelink.service.WebService;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class MumbleLink extends JavaPlugin {

    private WebService webService;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        String apiKey = this.getConfig().getString("apikey", "");

        if (!apiKey.matches("[a-zA-Z0-9]{32}")) {
            this.getLogger().severe("Please provide a valid API key!");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.webService = new WebService(apiKey);

        try {
            if (!this.webService.connect()) {
                this.getLogger().severe("Could not join the central server, please contact Azn9!");
                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();

            this.getLogger().severe("Could not join the central server, please contact Azn9!");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("mumble").setExecutor(new MumbleCommand(this.webService));
    }

    @Override
    public void onDisable() {
        if (this.webService != null) {
            this.webService.shutdown();
        }
    }

}
