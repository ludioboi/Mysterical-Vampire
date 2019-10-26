package de.nexxus.vampire.main;

import com.google.common.collect.Lists;
import de.nexxus.vampire.commands.BuildCommand;
import de.nexxus.vampire.commands.SetupCommand;
import de.nexxus.vampire.commands.StartCommand;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.listener.DeathListener;
import de.nexxus.vampire.listener.EventListener;
import de.nexxus.vampire.listener.PlayerQuitListener;
import de.nexxus.vampire.manager.ConfigFileUtil;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.testcommand.StartGameStateCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static Main getPlugin() {
        return plugin;
    }

    private static Main plugin;

    public static Manager getManager() {
        return manager;
    }

    private static Manager manager;
    private GameStateManager gameStateManager;
    private RoleManager roleManager;

    public static ArrayList<Player> players;

    @Override
    public void onEnable(){
        roleManager = new RoleManager();
        players = Lists.newArrayList();
        manager = new Manager();
        gameStateManager = new GameStateManager();
        plugin = this;
        init();
        ConfigFileUtil.setupFiles();
        manager.getGameStateManager().setGameState(GameState.LOBBY_STATE);
    }

    private void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    private void init() {
        registerListener(new EventListener());
        registerListener(new PlayerQuitListener());
        registerListener(new DeathListener());

        getCommand("setup").setExecutor(new SetupCommand());
        getCommand("state").setExecutor(new StartGameStateCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("start").setExecutor(new StartCommand());
    }
}