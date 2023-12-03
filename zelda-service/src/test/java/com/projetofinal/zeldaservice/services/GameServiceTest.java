package com.projetofinal.zeldaservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.zeldaservice.models.classes.GameIdModel;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNull;


public class GameServiceTest {

    @Test
    public void testGamesId() {
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        GameService gameService = new GameService();

        String mockedResponse = "{\"success\":true,\"data\":{\"name\":\"The Legend of Zelda\",\"description\":\"The Legend of Zelda is the first installment of the Zelda series. It centers its plot around a boy named Link, who becomes the central protagonist throughout the series. It came out as early as 1986 for the Famicom in Japan, and was later released in the western world, including Europe and the US in 1987. It has since then been re-released several times, for the Nintendo GameCube as well as the Game Boy Advance. The Japanese version of the game on the Famicom is known as The Hyrule Fantasy: The Legend of Zelda. \",\"developer\":\"Nintendo R&D 4\",\"publisher\":\"Nintendo\",\"released_date\":\" February 21, 1986\",\"id\":\"5f6ce9d805615a85623ec2b7\"}}";

        GameIdModel mockGameIdModel = convertJsonToGameIdModel(mockedResponse);
        String gameId = "5f6ce9d805615a85623ec2b7";

        when(mockRestTemplate.getForObject("https://zelda.fanapis.com/api/games/" + gameId, GameIdModel.class)).thenReturn(mockGameIdModel);

        GameModel result = gameService.getGameById(gameId);
        Assert.assertEquals(result, mockGameIdModel.getData());
    }

    @Test
    public void testGamesIdInvalid() {
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        GameService gameService = new GameService();

        String invalidId = "invalidId";


        String uri = "https://zelda.fanapis.com/api/games/" + invalidId;
        GameIdModel errorResponse = new GameIdModel();
        errorResponse.setSuccess(false);


        when(mockRestTemplate.getForObject(uri, GameIdModel.class))
                .thenThrow(new HttpClientErrorException(
                        org.springframework.http.HttpStatus.BAD_REQUEST, "Bad Request", null, null, null));


        GameModel result = null;

        try {
            result = gameService.getGameById(invalidId);
        } catch (HttpClientErrorException.BadRequest ex) {
            result = null;
        }

        assertNull(result);
    }

    public static GameIdModel convertJsonToGameIdModel(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, GameIdModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
