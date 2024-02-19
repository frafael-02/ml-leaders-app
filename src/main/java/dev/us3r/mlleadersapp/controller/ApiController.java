package dev.us3r.mlleadersapp.controller;

import dev.us3r.mlleadersapp.domain.Fixture;
import dev.us3r.mlleadersapp.domain.Player;
import dev.us3r.mlleadersapp.domain.Standing;
import dev.us3r.mlleadersapp.domain.Team;
import dev.us3r.mlleadersapp.service.ApiService;
import dev.us3r.mlleadersapp.util.PlayerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;


@Controller
@Slf4j

public class ApiController {

    private final ToDoubleFunction<Player> xGI = player -> player.getXG() + player.getXA();


    private final ApiService apiService;

    public ApiController(ApiService apiService)
    {
        this.apiService=apiService;
    }

    @GetMapping
    public String getStandings(Model model)
    {
        List<Standing> standings = apiService.getStandings();

        model.addAttribute("leaders", standings);


        return "index";
    }

    @GetMapping("/playerList")
    public ResponseEntity<List<Player>> getPlayer()
    {
        List<Player> result = new ArrayList<>();
        //filtering highest xGI with lambda
        // Player player = apiService.getPlayers().stream().max(Comparator.comparingDouble( p -> (p.getXG() + p.getXA()))).get();
        List<Player> playerList = apiService.getPlayers();
       /* playerList = playerList.stream()
                .sorted(
                        Comparator.comparingDouble(xGI)
                                .thenComparingDouble(Player::getMinutesPlayed)
                                .thenComparingDouble(Player::getSelected_by_percent)
                                .thenComparingDouble(Player::getNow_cost).reversed()
                )
                .collect(Collectors.toList());*/


        for(Player player : playerList)
        {
            int difficulty = PlayerUtil.futureFixturesDifficulty(apiService.getFixtures(String.valueOf(player.getId())), 4);
            if(difficulty <11 && xGI.applyAsDouble(player) >= 1.5 && player.getMinutesPlayed() > 277)
            {
                log.info(player.getLast_name() + difficulty);
                result.add(player);
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable("id") Integer id)
    {
        Team team = apiService.getTeams().get(id);

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<?> getPlayerDifficulty(@PathVariable("id") Long id)
    {
        Player player = apiService.getPlayers().stream().filter(p -> p.getId().equals(id)).findFirst().get();
       int difficulty = PlayerUtil.futureFixturesDifficulty(apiService.getFixtures(String.valueOf(id)), 5);
       Map<Player, Integer> result = new HashMap<>();
       result.put(player, difficulty);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
