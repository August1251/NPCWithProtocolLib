package org.novikov.protocolliblearn;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.entity.Player;

public class NPCClickableEvent {

    private Protocolliblearn protocolliblearn;
    private ProtocolManager protocolManager;

    public NPCClickableEvent(
            Protocolliblearn protocolliblearn,
            ProtocolManager protocolManager) {
        this.protocolliblearn = protocolliblearn;
        this.protocolManager = protocolManager;
    }

    public void registerEvent() {
        protocolManager.addPacketListener(
                new PacketAdapter(
                        protocolliblearn,
                        ListenerPriority.NORMAL,
                        PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent packetEvent) {

                Player player = packetEvent.getPlayer();
                PacketContainer packetContainer = packetEvent.getPacket();
                EnumWrappers.EntityUseAction action = packetContainer.getEnumEntityUseActions().read(0).getAction();

                int entityId = packetContainer.getIntegers().read(0);

                if (entityId == -1 && action == EnumWrappers.EntityUseAction.ATTACK) {
                    player.sendMessage("Fuck You");
                }
            }
        });
    }

}