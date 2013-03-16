package name.brychta.sqlwhitelist;

import org.bukkit.plugin.java.JavaPlugin;

public class SQLWhitelist extends JavaPlugin{
    public void onEnable(){
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new SQLWhitelistPlayerListener(this), this);
    }
    public void onDisable(){
        
    }

}
