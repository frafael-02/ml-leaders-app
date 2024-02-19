package dev.us3r.mlleadersapp.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.us3r.mlleadersapp.domain.Fixture;


import java.util.ArrayList;
import java.util.List;

public class MapFixtures {

    public static List<Fixture> mapFixtures(String json)
    {     List<Fixture> fixturesList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode root = objectMapper.readTree(json);
            JsonNode fixturesArrayNode = root.get("fixtures");

            for(JsonNode fixtureInfo: fixturesArrayNode)
            {
               Fixture fixture = new Fixture();
                fixture.mapFromJson(fixtureInfo);
                fixturesList.add(fixture);
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fixturesList;
    }
}
