package org.novikov.protocolliblearn;

import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.novikov.protocolliblearn.npc.EntityHeadAndBodyRotationUpdate;

import java.util.UUID;

public class PlayerMoving implements Listener {

    private ProtocolManager protocolManager;
    private Location location;

    public PlayerMoving(
            ProtocolManager protocolManager,
            Location location) {
        this.protocolManager = protocolManager;
        this.location = location;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        EntityHeadAndBodyRotationUpdate updateHeadAndBodyRotation = new EntityHeadAndBodyRotationUpdate(protocolManager);

        /*
        If the player's location is greater than 5, the NPC does not watch the player.
        If you don't do this, it will create lags; you can't let the NPC constantly look at the player!
         */
        if (player.getLocation().distance(location) <= 5) {
            updateHeadAndBodyRotation.updateRotation(player, location);
        }

    }

}