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

public class Player extends Entity{
    private String first_name;
    private String last_name;
    private Long teamId;
    private PLAYER_POSITION player_position;
    private Double now_cost;
    private Double selected_by_percent;

    private int minutesPlayed;

    private Double xG;

    private Double xA;

    public void mapFromJson(JsonNode jsonPlayer)
    {
        this.setId(jsonPlayer.get("id").asLong());
        this.setCode(jsonPlayer.get("code").asLong());
        this.first_name=jsonPlayer.get("first_name").asText();
        this.last_name=jsonPlayer.get("second_name").asText();
        this.teamId=jsonPlayer.get("team").asLong();
        this.now_cost = ((double) jsonPlayer.get("now_cost").asInt()) / 10;
        int positionId = jsonPlayer.get("element_type").asInt();
        switch (positionId) {
            case 1 -> this.player_position = PLAYER_POSITION.GK;
            case 2 -> this.player_position = PLAYER_POSITION.DF;
            case 3 -> this.player_position = PLAYER_POSITION.MF;
            case 4 -> this.player_position = PLAYER_POSITION.FW;
        }
        this.selected_by_percent = jsonPlayer.get("selected_by_percent").asDouble();
        this.minutesPlayed=jsonPlayer.get("minutes").asInt();
        this.xG = jsonPlayer.get("expected_goals").asDouble();
        this.xA = jsonPlayer.get("expected_assists").asDouble();
    }


}
