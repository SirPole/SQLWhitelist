package name.brychta.sqlwhitelist;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
//@author Martin Brychta [SirPole]
public class SQLWhitelistPlayerListener implements Listener {

    SQLWhitelist plg;

    public SQLWhitelistPlayerListener(SQLWhitelist plg) {
        this.plg = plg;
    }

    /*
     * Kicks player that isn't in database
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        try {
            ResultSet rs = plg.db.query("SELECT * FROM whitelist WHERE player='" + event.getPlayer().getName() + "'");
            if (!rs.first()) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, plg.getConfig().getString("kick"));
            }
            rs.close();
        } catch (SQLException ex) {
            plg.getLogger().severe(ex.getMessage());
        }
    }
}
