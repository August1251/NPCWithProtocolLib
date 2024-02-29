package org.novikov.protocolliblearn;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Protocolliblearn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        NPCClickableEvent npcClickelableEvent = new NPCClickableEvent(this, ProtocolLibrary.getProtocolManager());

        npcClickelableEvent.registerEvent();

        Bukkit.getPluginManager().registerEvents(new PlayerMoving(
                        ProtocolLibrary.getProtocolManager(),
                        new Location(
                                Bukkit.getServer().getWorld("world"),
                                -1.0,
                                2.0,
                                0.0)
                        ), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}