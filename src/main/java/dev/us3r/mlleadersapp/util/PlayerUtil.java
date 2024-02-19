package dev.us3r.mlleadersapp.util;

import dev.us3r.mlleadersapp.domain.Fixture;
import dev.us3r.mlleadersapp.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerUtil {

    private static final Double differentialMax = 12.0;

    private static final int minutes_benchmark = 210;

    private static final double xGI_benchmark = 1.5;


    public static List<Player> findDifferentials(List<Player> playerList)
    {
            return playerList.stream().filter(player -> player.getSelected_by_percent()<=differentialMax).collect(Collectors.toList());
    }

    public static List<Player> findPlayersThatPlay(List<Player> playerList)
    {
        return playerList.stream().filter(player -> player.getMinutesPlayed() >= minutes_benchmark).collect(Collectors.toList());
    }

    public static List<Player> findPlayersByxGI(List<Player> playerList)
    {
        return playerList.stream().filter(p -> (p.getXG() + p.getXA()) > xGI_benchmark).collect(Collectors.toList());


    }

    public static int futureFixturesDifficulty(List<Fixture> fixturesList, int duration)
    {
        int totalDifficulty=0;
        for(int i=0;i<duration;i++)
        {
            totalDifficulty=totalDifficulty+fixturesList.get(i).getDifficulty();
        }

        return totalDifficulty;
    }


}
