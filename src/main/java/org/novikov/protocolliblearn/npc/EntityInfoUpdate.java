package org.novikov.protocolliblearn.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import org.bukkit.entity.Player;

import java.util.*;

public class EntityInfoUpdate {

    private ProtocolManager protocolManager;
    private UUID uuid;

    public EntityInfoUpdate(
            ProtocolManager protocolManager,
            UUID uuid) {
        this.protocolManager = protocolManager;
        this.uuid = uuid;
    }

    public void playerInfoUpdate(Player player) {

        PacketContainer npc = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO); // make player info packet
        Set<EnumWrappers.PlayerInfoAction> playerInfoActionSet = new HashSet<>(); //The set collection that contains Player Info Action, very important

        /*
        Create a new custom game profile for our NPC.
        In the first arguments put a unique uuid generated with UUID.randomUUID();
        In the second arguments put a NPC's name above head
         */
        WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(uuid, "NPC");

        /*
        Create a new property about player's skin

        In the first leave as is and don't touch or nothing works thuth.
        In the second and third, indicate the values of your needs. how to get this data?

        Go to https://minecraftuuid.com/ and find the skin you want.
        If your found the skin, copy his Player UUID.

        Type a search in your browser, but don't press Enter, you'll need to edit that URL;
        https://sessionserver.mojang.com/session/minecraft/profile/uuid?unsigned=false

        Delete a word "uuid" and paste the player's uuid that you copied.
        And now you need to press Enter and you will get the result

        Copy the value and paste to second constructor arguments.
        Copy the signature and paster to third constructor arguments.

         */
        WrappedSignedProperty property = new WrappedSignedProperty(
                "textures",
                "ewogICJ0aW1lc3RhbXAiIDogMTcwOTEzODQ2ODQwOSwKICAicHJvZmlsZUlkIiA6ICIwNjlhNzlmNDQ0ZTk0NzI2YTViZWZjYTkwZTM4YWFmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb3RjaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yOTIwMDlhNDkyNWI1OGYwMmM3N2RhZGMzZWNlZjA3ZWE0Yzc0NzJmNjRlMGZkYzMyY2U1NTIyNDg5MzYyNjgwIgogICAgfQogIH0KfQ==",
                "ESOeT3Fo19IDBmQS231ZSm3XKCq5SVEbu74LArJoi9Ker1RXDsP3XV8mPgfeWCmm89v2RXLp7bcmmCtJI6JgKPZb3wCasSaM1SxwJ4WEaoByQvJ0FhRyeG126d9GEujQgOxmjErvKGLOKgRbF3ORoroSua8csiNn8+ADSyYrUud0lzczOMD9hx+qov+wSYb+zJ/9bR4aXFAnLxMbEAOYOyJCSJ+ZE4KQmvebgyNGHLSmC/Vm9zZIFAZrg62wV3SXBAuWGXv46jDPlaoimZv1/zmOMDj8dGwQ89WWR1zcR8ZBn9Ysgi+yfTvR3w/AHeuMa2IdQKxXw8QJl0kZp0c36RIIfKB25KcbED2HReuA/Nmg5S+HXju9mrK+M4YDvz34oRgBvnVjUTWtg6b8eW3OtaKlIkEgjlUGPZYU4OhoeoQaEylcjISyT4nS36iGumSSgXAW75GuiPI4b8KZ5iy0yXlWWSLTJr0axShFuvuv4Vd70qufnusnEGceMyOXBUCC5lcJ06RrIwUNdLWkwGh8OWdjXffvyhyAAbrq8oFNKxRzjf8E6XEAvClf5L6BSH8kd7OwxpEFzUMiJNHZ9tQ9j8EwsYv3cLUNhBV/yf2O96AqDmyCD3wTblQj8k4pJRyi+/Kj1476yVOfuc3LJ4jYcDX2pJv3LemUxLJ+JDMdJh0="        );

        //Now we need to add a skin property for our game profile.
        wrappedGameProfile.getProperties().clear();
        wrappedGameProfile.getProperties()
                .put("textures", property);

        /*
        In the first constructor arguments put our game profile
        In the second constructor arguments put your latency that your need
        In the third constructor arguments put a npc game mode, I put a creative.
        In the fourth constructor argument we need to put a wrapped chat component,
        I'm not lying and telling the truth - I don't know what it is. I think it's something to do with the chat,
        maybe it's the player name that is displayed if a player tell something in chat. I don't know, sorry.
         */
        PlayerInfoData playerInfoData = new PlayerInfoData(
                wrappedGameProfile,
                0,
                EnumWrappers.NativeGameMode.CREATIVE,
                WrappedChatComponent.fromText("name"));


        List<PlayerInfoData> playerInfoDataList = Arrays.asList(playerInfoData); //Add player's info data in list like here.

        playerInfoActionSet.add(EnumWrappers.PlayerInfoAction.ADD_PLAYER); //Adds player actions that the server must perform, in our case this is adding a player

        npc.getPlayerInfoActions()
                .write(0, playerInfoActionSet); //Add a player's action to our packet

        npc.getPlayerInfoDataLists().write(1, playerInfoDataList); //Add a list of player's info data in out packet

        /*
        In the first method arguments, we need to put player to who that send packet
        In the second method arguments, we need to put packet that sends.
         */
        protocolManager.sendServerPacket(player, npc);
    }

}