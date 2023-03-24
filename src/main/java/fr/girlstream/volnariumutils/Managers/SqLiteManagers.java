package fr.girlstream.volnariumutils.Managers;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqLiteManagers {


    private Connection connection;

    public SqLiteManagers() throws SQLException {
    }


    public void openConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            return;
        }

        this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    public void closeConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            return;
        }

        this.connection.close();
    }

    public boolean isOpen() throws SQLException {
        return !(this.connection == null && this.connection.isClosed());
    }

    public void doBasic() throws SQLException {
        if(!isOpen()){
            throw new SQLException("La connexion n'est pas ouverte.");
        }

        Statement statement = this.connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS player (id_player TEXT PRIMARY KEY, name TEXT, datetime DATETIME)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS rank (rank TEXT PRIMARY KEY, perm TEXT, cooldown INTEGER)");
        statement.close();
    }

    public boolean isPlayerExist(Player player){

    }

    public Connection getConnection() {
        return connection;
    }
}
