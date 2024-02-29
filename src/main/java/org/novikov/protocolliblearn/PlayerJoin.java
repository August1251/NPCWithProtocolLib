package org.novikov.protocolliblearn;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.novikov.protocolliblearn.npc.NPC;

import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        NPC npc = new NPC(
                ProtocolLibrary.getProtocolManager(),
                UUID.randomUUID());

        Location location = new Location(Bukkit.getServer().getWorld("world"), -1.0, 2.0, 0.0);

        npc.spawn(event.getPlayer(), location);

    }

}