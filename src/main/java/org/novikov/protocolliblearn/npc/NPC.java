package org.novikov.protocolliblearn.npc;

import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.novikov.protocolliblearn.Protocolliblearn;

import java.util.UUID;

public class NPC {

    private ProtocolManager protocolManager;
    private UUID uuid;

    public NPC(
            ProtocolManager protocolManager,
            UUID uuid) {
        this.protocolManager = protocolManager;
        this.uuid = uuid;
    }

    public void spawn(Player player, Location location) {
        EntityInfoUpdate updateInfo = new EntityInfoUpdate(protocolManager, uuid);
        EntitySpawn spawnEntity = new EntitySpawn(protocolManager, uuid);

        updateInfo.playerInfoUpdate(player);
        spawnEntity.spawnEntity(player, location);
    }

}