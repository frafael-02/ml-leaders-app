package dev.us3r.mlleadersapp.service;

import dev.us3r.mlleadersapp.domain.Fixture;
import dev.us3r.mlleadersapp.domain.Player;
import dev.us3r.mlleadersapp.domain.Standing;
import dev.us3r.mlleadersapp.domain.Team;

import java.util.List;


public interface ApiService {

    List<Standing> getStandings();

    List<Player> getPlayers();

    List<Team> getTeams();

    List<Fixture> getFixtures(String id);
}
