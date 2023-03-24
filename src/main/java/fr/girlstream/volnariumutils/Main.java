package fr.girlstream.volnariumutils;

import fr.girlstream.volnariumutils.Managers.EventsManagers;
import fr.girlstream.volnariumutils.Managers.SqLiteManagers;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public final class Main extends JavaPlugin {

    private static Main main;
    private File DataBase;
    private SqLiteManagers sqLiteManagers;


    @Override
    public void onEnable() {
        main = this;


        getLogger().info("Starting ...");

        DataBase = new File(getDataFolder(), "database.db");
        if(!DataBase.exists()) {
            getLogger().info("Creation de la base de donnée");
            DataBase.getParentFile().mkdirs();
            saveResource("database.db", false);
            try {
                sqLiteManagers.openConnection();
                Statement statement = sqLiteManagers.getConnection().createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS player (id_player TEXT PRIMARY KEY, name TEXT, datetime DATETIME)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS rank (rank TEXT PRIMARY KEY, perm TEXT, cooldown INTEGER)");
                String rank1 = "VIP+";
                String perm1 = "cd.vip+";
                int cooldown1 = 50;
                String insert1 = "INSERT INTO rank (rank, perm, cooldown) VALUES ('" + rank1 + "', '" + perm1 + "', " + cooldown1 + ")";
                statement.executeUpdate(insert1);

                String rank2 = "HERO";
                String perm2 = "cd.hero";
                int cooldown2 = 20;
                String insert2 = "INSERT INTO rank (rank, perm, cooldown) VALUES ('" + rank2 + "', '" + perm2 + "', " + cooldown2 + ")";
                statement.executeUpdate(insert2);
                statement.close();
                sqLiteManagers.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



        EventsManagers.register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Main getInstance() {
        return main;
    }

    public SqLiteManagers getSqLiteManagers() {
        return sqLiteManagers;
    }
}
