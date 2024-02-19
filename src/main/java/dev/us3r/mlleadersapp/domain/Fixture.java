package dev.us3r.mlleadersapp.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Fixture extends Entity{

    private Long teamId_h;
    private Long teamId_a;
    private int gameWeek;
    private int difficulty;


    public void mapFromJson(JsonNode fixtureInfo)
    {
        this.setId(fixtureInfo.get("id").asLong());
        this.setCode(fixtureInfo.get("code").asLong());
        this.difficulty = fixtureInfo.get("difficulty").asInt();
        this.teamId_h = fixtureInfo.get("team_h").asLong();
        this.teamId_a=fixtureInfo.get("team_a").asLong();
        this.gameWeek=fixtureInfo.get("event").asInt();
    }



}
