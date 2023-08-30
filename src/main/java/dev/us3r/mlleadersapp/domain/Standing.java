package dev.us3r.mlleadersapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Standing {

    private long place;
    private String teamName;
    private String playerName;
    private int points;
}
