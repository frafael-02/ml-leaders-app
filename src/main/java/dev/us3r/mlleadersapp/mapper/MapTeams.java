package dev.us3r.mlleadersapp.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.us3r.mlleadersapp.domain.Player;
import dev.us3r.mlleadersapp.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class MapTeams {

    public static List<Team> mapTeams(String json)
    {     List<Team> teamList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode root = objectMapper.readTree(json);
            JsonNode teamsArrayNode = root.get("teams");

            for(JsonNode teamInfo : teamsArrayNode)
            {
                Team team = new Team();
                team.mapFromJson(teamInfo);
                teamList.add(team);
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return teamList;
    }
}
