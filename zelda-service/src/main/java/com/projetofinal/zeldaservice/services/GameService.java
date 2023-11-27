package com.projetofinal.zeldaservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameService {

    private final WebClient webClient;

    @Autowired
    public GameService(WebClient webClient) {
        this.webClient = webClient;
    }
    public List<GameModel> listGames() {

        List<GameModel> gamesList = this.webClient
                .method(HttpMethod.GET)
                .uri("/games")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMapMany(jsonNode -> {
                    JsonNode dataNode = jsonNode.get("data");
                    if (dataNode != null && dataNode.isArray()) {
                        ObjectMapper mapper = new ObjectMapper();
                        return Flux.fromIterable(mapper.convertValue(dataNode, new TypeReference<List<GameModel>>() {}));
                    } else {
                        return Flux.error(new RuntimeException("Failed to parse data from the API response"));
                    }
                })
                .collectList()
                .block();

        return gamesList;
    }
    public GameModel getGameById(String gameId) {
        GameModel game = this.webClient
                .method(HttpMethod.GET)
                .uri("/games/{gameId}", gameId)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNode.get("data");
                    if (dataNode != null && dataNode.isObject()) {
                        ObjectMapper mapper = new ObjectMapper();
                        return Mono.just(mapper.convertValue(dataNode, GameModel.class));
                    } else {
                        return Mono.error(new RuntimeException("Failed to parse data from the API response"));
                    }
                })
                .block();

        return game;
    }
}
