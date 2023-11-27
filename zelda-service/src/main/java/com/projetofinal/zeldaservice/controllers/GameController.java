package com.projetofinal.zeldaservice.controllers;

import com.projetofinal.zeldaservice.models.classes.GameModel;
import com.projetofinal.zeldaservice.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zelda-service")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity listGames(){
        try{
            List<GameModel> gamesList = gameService.listGames();
            return ResponseEntity.status(HttpStatus.OK).body(gamesList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<String> getGame(@PathVariable String gameId) {
        try {
            GameModel games = gameService.getGameById(gameId);
            return ResponseEntity.status(HttpStatus.CREATED).body(gameId);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
