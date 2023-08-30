package dev.us3r.mlleadersapp.service;

import dev.us3r.mlleadersapp.domain.Standing;
import dev.us3r.mlleadersapp.scrapping.Scrapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApiServiceImpl implements ApiService{


    @Override
    public List<Standing> getStandings() {
        return Scrapper.scrapStandings();
    }
}
