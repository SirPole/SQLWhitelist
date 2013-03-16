package name.brychta.sqlwhitelist;

import java.sql.SQLException;
import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.MySQL;
import org.bukkit.plugin.java.JavaPlugin;
//@author Martin Brychta [SirPole]
public class SQLWhitelist extends JavaPlugin {

    Database db;

    /*
     * Performs actions at plugin load
     * Connects to db and creates table if not exists
     */
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SQLWhitelistPlayerListener(this), this);
        saveDefaultConfig();
        db = new MySQL(getLogger(), "[SQLWhitelist]", getConfig().getString("host"), getConfig().getInt("port"), getConfig().getString("db"), getConfig().getString("user"), getConfig().getString("pass"));
        if (!db.isOpen()) {
            if (db.open()) {
                getLogger().info("MySQL connection successful...");
            } else {
                getLogger().severe("MySQL connection failed!");
            }
        }
        try {
            db.query("CREATE TABLE IF NOT EXISTS whitelist(player varchar(50) NOT NULL, PRIMARY KEY(player))");
        } catch (SQLException ex) {
            getLogger().severe(ex.getMessage());
        }
    }

    /*
     * Performs actions at plugin unload
     * Closes db connection
     */
    public void onDisable() {
        if (db.isOpen()) {
            db.close();
            getLogger().info("MySQL connection closed...");
        }
    }
}
