package name.brychta.sqlwhitelist;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class SQLWhitelistPlayerListener implements Listener{
    Plugin plg;
    public SQLWhitelistPlayerListener(Plugin plg){
        this.plg=plg;
    }
    public void onPlayerJoin(PlayerJoinEvent event){
        
    }
}
