package com.projetofinal.zeldaservice.services;

import com.projetofinal.zeldaservice.models.classes.GameIdModel;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GameService {
    @Autowired
    public GameService() {

    }

    public GameModel getGameById(String gameId) {

        String uri = "https://zelda.fanapis.com/api/games/" + gameId;
        RestTemplate restTemplate = new RestTemplate();
        GameIdModel resp = restTemplate.getForObject(uri, GameIdModel.class);

        return resp.getData();


    }
}
