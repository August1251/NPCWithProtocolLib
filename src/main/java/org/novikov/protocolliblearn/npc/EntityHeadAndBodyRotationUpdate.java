package org.novikov.protocolliblearn.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EntityHeadAndBodyRotationUpdate {

    private ProtocolManager protocolManager;

    public EntityHeadAndBodyRotationUpdate(
            ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
    }

    public void updateRotation(Player player, Location location) {
        /*
        As you can tell by the name of the package,
         the first package is responsible for rotating the head,
          the second package is responsible for the entire body.
         */
        PacketContainer rotateHead = protocolManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        PacketContainer rotateBody = protocolManager.createPacket(PacketType.Play.Server.ENTITY_LOOK);

        rotateHead.getIntegers()
                .write(0, -1); //The second argument sets the ID of the object that will be used
        rotateBody.getIntegers()
                .write(0, -1); //The second argument sets the ID of the object that will be used
        location.setDirection(player.getLocation().subtract(location).toVector()); //Set direction npc to player

        float yaw = location.getYaw();
        float pitch = location.getPitch();

        rotateHead.getBytes()
                .write(0, (byte) ((yaw % 360) * 256 / 360)); //set head rotation
        rotateBody.getBytes()
                .write(0, (byte) ((yaw % 360) * 256 / 360)) //set head rotation
                .write(1, (byte) ((pitch % 360) * 256 / 360)); //set body rotation

        /*
        In the first method arguments, we need to put player to who that send packet
        In the second method arguments, we need to put packet that sends.
         */
        protocolManager.sendServerPacket(player, rotateHead);
        protocolManager.sendServerPacket(player, rotateBody);
    }

}