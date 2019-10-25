package de.nexxus.vampire.commands;

//Created by MrKompetnz on 10/25/19

import de.nexxus.vampire.gamestate.LobbyState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private static final int START_SECONDS = 5;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender);
            if(player.hasPermission("vampire.start")) {
                if(args.length == 0) {
                    if(Main.getPlugin().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
                        LobbyState lobbyState = (LobbyState) Main.getPlugin().getGameStateManager().getCurrentGameState();
                        if(lobbyState.getLobbyCountdown().isRunning() && lobbyState.getLobbyCountdown().getSeconds() > START_SECONDS) {
                            lobbyState.getLobbyCountdown().setSeconds(START_SECONDS);
                            player.sendMessage(Data.PREFIX + "§aDer Spielstart wurde beschleunigt!");
                        } else
                            player.sendMessage(Data.PREFIX + "§cDas Spiel ist bereits gestartet.");
                    } else
                        player.sendMessage(Data.PREFIX + "§cDas Spiel ist bereits gestartet.");
                } else {
                    player.sendMessage(Data.PREFIX + "§cBitte benutze §6/start§c!");
                }
            } else {
                player.sendMessage(Data.NO_PERMISSION);
            }
        }
        return false;
    }
}
