package org.novikov.protocolliblearn;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Protocolliblearn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        NPCClickableEvent npcClickelableEvent = new NPCClickableEvent(this, ProtocolLibrary.getProtocolManager());

        npcClickelableEvent.registerEvent();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}