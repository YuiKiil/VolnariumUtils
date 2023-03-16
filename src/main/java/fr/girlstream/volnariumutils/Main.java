package fr.girlstream.volnariumutils;

import fr.girlstream.volnariumutils.Managers.EventsManagers;
import fr.girlstream.volnariumutils.Managers.SqLiteManagers;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

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
        }

        try {
            sqLiteManagers.openConnection();
            sqLiteManagers.doBasic();
            sqLiteManagers.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
