package dev.us3r.mlleadersapp.scrapping;
import dev.us3r.mlleadersapp.domain.Standing;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {

    static final String PAGE_URL= "https://www.livefpl.net/leagues/14480";

    public static List<Standing> scrapStandings()
    {

        try {
            // fetching the target website
            Document doc = Jsoup
                    .connect(PAGE_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*")
                    .get();

            List<Standing> standings = new ArrayList<>();


            Elements teamNames = doc.select("h3.table-team-name");
            Elements playerNames = doc.select("p.table-user-name");
            Elements teamPts = doc.select("td.table-total");
            for(int i=0;i<teamNames.size();i++)
            {
                Standing standing = new Standing(i+1L, teamNames.get(i).text(), playerNames.get(i).text(), Integer.parseInt(teamPts.get(i).text()));
                standings.add(standing);
            }

            return standings;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
