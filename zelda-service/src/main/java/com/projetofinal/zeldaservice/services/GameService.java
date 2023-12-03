package com.projetofinal.zeldaservice.services;

import com.projetofinal.zeldaservice.models.classes.GameIdModel;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import com.projetofinal.zeldaservice.models.classes.ListGameModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class GameService {


    @Autowired
    public GameService() {
    }

    public List<GameModel> listGames() {

        String uri = "https://zelda.fanapis.com/api/games";
        RestTemplate restTemplate = new RestTemplate();
        ListGameModel resp = restTemplate.getForObject(uri, ListGameModel.class);

        return resp.getData();

    }

    public GameModel getGameById(String gameId) {

        String uri = "https://zelda.fanapis.com/api/games/" + gameId;
        RestTemplate restTemplate = new RestTemplate();
        GameIdModel resp = restTemplate.getForObject(uri, GameIdModel.class);

        return resp.getData();


    }
}
