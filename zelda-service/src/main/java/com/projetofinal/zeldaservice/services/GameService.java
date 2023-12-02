package com.projetofinal.zeldaservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import com.projetofinal.zeldaservice.models.classes.ListGameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameService {

    //private final WebClient webClient;

    @Autowired
    public GameService() {}
    public List<GameModel> listGames() {

        String uri = "https://zelda.fanapis.com/api/games";
        RestTemplate restTemplate = new RestTemplate();
        ListGameModel resp = restTemplate.getForObject(uri, ListGameModel.class);

        return resp.getData();
    }

}
