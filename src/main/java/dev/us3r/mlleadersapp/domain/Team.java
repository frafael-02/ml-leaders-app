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
public class Team extends Entity{

    private String name;
    private String short_name;
    private Integer strength;
    private Integer strength_overall_home;
    private Integer strength_overall_away;
    private Integer strength_attack_home;
    private Integer strength_attack_away;
    private Integer strength_defence_home;
    private Integer strength_defence_away;

    public void mapFromJson(JsonNode jsonTeam)
    {
        this.setId(jsonTeam.get("id").asLong());
        this.setCode(jsonTeam.get("code").asLong());
        this.name = jsonTeam.get("name").asText();
        this.short_name=jsonTeam.get("short_name").asText();
        this.strength=jsonTeam.get("strength").asInt();
        this.strength_overall_home=jsonTeam.get("strength_overall_home").asInt();
        this.strength_overall_away=jsonTeam.get("strength_overall_away").asInt();
        this.strength_attack_home=jsonTeam.get("strength_attack_home").asInt();
        this.strength_attack_away=jsonTeam.get("strength_attack_away").asInt();
        this.strength_defence_home=jsonTeam.get("strength_defence_home").asInt();
        this.strength_defence_away=jsonTeam.get("strength_defence_away").asInt();
    }


}
