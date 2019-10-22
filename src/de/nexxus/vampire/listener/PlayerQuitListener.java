package de.nexxus.vampire.listener;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
    // Set quitmessage etc.

        event.setQuitMessage(Data.PREFIX + "§6" + event.getPlayer().getName() + " §8hat das Spiel verlassen.");
        TeamManager.allPlayers.remove(event.getPlayer());
        LobbyCountdown countdown = new LobbyCountdown(Main.getManager().getGameStateManager());
        if(countdown.isRunning()) {
            countdown.stop();
            if (!countdown.isIdling()){
                countdown.startIdle();
            }
        }
    }
}
