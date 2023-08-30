package dev.us3r.mlleadersapp.controller;

import dev.us3r.mlleadersapp.domain.Standing;
import dev.us3r.mlleadersapp.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller

public class ApiController {

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


}
