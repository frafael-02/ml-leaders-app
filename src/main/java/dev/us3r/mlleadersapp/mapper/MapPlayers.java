package dev.us3r.mlleadersapp.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.us3r.mlleadersapp.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class MapPlayers {

    public static List<Player> mapPlayers(String json)
    {     List<Player> playerList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode root = objectMapper.readTree(json);
            JsonNode playersArrayNode = root.get("elements");

            for(JsonNode playerInfo : playersArrayNode)
            {
                Player player = new Player();
                player.mapFromJson(playerInfo);
                playerList.add(player);
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return playerList;
    }
}
