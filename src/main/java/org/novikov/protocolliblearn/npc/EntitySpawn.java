package org.novikov.protocolliblearn.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.novikov.protocolliblearn.Protocolliblearn;

import java.util.UUID;

public class EntitySpawn {

    private ProtocolManager protocolManager;
    private UUID uuid;

    public EntitySpawn(
            ProtocolManager protocolManager,
            UUID uuid) {
        this.protocolManager = protocolManager;
        this.uuid = uuid;
    }

    public void spawnEntity(Player player, Location location) {

        /*
        Create a named entity spawn packet
         */
        PacketContainer npc = protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);


        npc.getIntegers()
                .write(0, -1) //the second value it's entity id, it must be unique!
                .writeSafely(1, 122); //the second value it's same entity id, but it use for only spawn entity

        npc.getUUIDs()
                .write(0, uuid); //uuid must be like uuid in player info packet!

        npc.getEntityTypeModifier()
                .writeSafely(0, EntityType.PLAYER); //Entity Type, nothing complicated.

        npc.getDoubles()
                .write(0, location.getX())
                .write(1, location.getY())
                .write(2, location.getZ()); //Spawn location

        npc.getBytes()
                .write(0, (byte) (0))
                .write(1, (byte) (0)); //rotate location

        /*
        In the first method arguments, we need to put player to who that send packet
        In the second method arguments, we need to put packet that sends.
         */
        protocolManager.sendServerPacket(player, npc);
    }

}