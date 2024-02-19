package dev.us3r.mlleadersapp.service;

import dev.us3r.mlleadersapp.domain.Fixture;
import dev.us3r.mlleadersapp.domain.Player;
import dev.us3r.mlleadersapp.domain.Standing;
import dev.us3r.mlleadersapp.domain.Team;
import dev.us3r.mlleadersapp.mapper.MapFixtures;
import dev.us3r.mlleadersapp.mapper.MapPlayers;
import dev.us3r.mlleadersapp.mapper.MapTeams;
import dev.us3r.mlleadersapp.scrapping.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ApiServiceImpl implements ApiService{

    private final RestTemplate restTemplate;

    private final String BASIC_URL = "https://fantasy.premierleague.com/api/bootstrap-static/";

    private final String FIXTURES_URL = "https://fantasy.premierleague.com/api/element-summary/";

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Standing> getStandings() {
        return Scrapper.scrapStandings();
    }


    @Override
    public List<Player> getPlayers() {
        ResponseEntity<String> response = restTemplate.getForEntity(BASIC_URL, String.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
           return MapPlayers.mapPlayers(response.getBody());

        }
        return null;

    }

    @Override
    public List<Team> getTeams() {
        ResponseEntity<String> response = restTemplate.getForEntity(BASIC_URL, String.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
            return MapTeams.mapTeams(response.getBody());

        }
        return null;
    }

    @Caching
    @Override
    public List<Fixture> getFixtures(String id) {
        ResponseEntity<String> response = restTemplate.getForEntity(FIXTURES_URL + id + "/", String.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
            return MapFixtures.mapFixtures(response.getBody());

        }
        return null;
    }





}
