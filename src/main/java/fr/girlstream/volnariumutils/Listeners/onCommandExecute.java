package fr.girlstream.volnariumutils.Listeners;

import com.google.common.base.Strings;
import fr.girlstream.volnariumutils.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;



public class onCommandExecute implements Listener {

    private Main instance = Main.getInstance();

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent e){
        String command =  e.getMessage();
        if(e.getMessage().split(" ")[0].equalsIgnoreCase("repair")){

        }
    }

}
