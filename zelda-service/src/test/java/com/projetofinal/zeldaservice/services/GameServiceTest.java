package com.projetofinal.zeldaservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.zeldaservice.models.classes.GameIdModel;
import com.projetofinal.zeldaservice.models.classes.GameModel;
import com.projetofinal.zeldaservice.models.classes.ListGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNull;


public class GameServiceTest {

    @Test
    public void testListGames() {
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        GameService gameService = new GameService();

        String mockedResponse = "{\"success\":true,\"count\":20,\"data\":[{\"name\":\"The Legend of Zelda\",\"description\":\"The Legend of Zelda is the first installment of the Zelda series. It centers its plot around a boy named Link, who becomes the central protagonist throughout the series. It came out as early as 1986 for the Famicom in Japan, and was later released in the western world, including Europe and the US in 1987. It has since then been re-released several times, for the Nintendo GameCube as well as the Game Boy Advance. The Japanese version of the game on the Famicom is known as The Hyrule Fantasy: The Legend of Zelda. \",\"developer\":\"Nintendo R&D 4\",\"publisher\":\"Nintendo\",\"released_date\":\" February 21, 1986\",\"id\":\"5f6ce9d805615a85623ec2b7\"},{\"name\":\"The Legend of Zelda: A Link to the Past\",\"description\":\"One day, a band of evil thieves managed to open the gateway to the Sacred Realm, where the mystical Triforce was hidden. Upon finding the sacred golden relic, the leader of the thieves, Ganondorf, slew his followers and claimed it as his own. Before long, dark power began to flow forth from the Sacred Realm. People were drawn into this darkness, and never heard from again. As a result, the King of Hyrule ordered the seven sages to seal the entrance to the Sacred Realm. A great battle ensued—monsters poured into the Light World from the sacred land and attacked the castle. The Knights of Hyrule defended the sages during the great battle against evil, and, though most of them perished in the struggle, the sages were able to cast their seal, stopping the flow of darkness and trapping the evil king Ganon within. This battle became known as the Imprisoning War. \",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" April 13, 1992\",\"id\":\"5f6ce9d805615a85623ec2b8\"},{\"name\":\"The Legend of Zelda: Ocarina of Time\",\"description\":\"The Legend of Zelda: Ocarina of Time is the fifth main installment of The Legend of Zelda series and the first to be released for the Nintendo 64. It was one of the most highly anticipated games of its age. It is listed among the greatest video games ever created by numerous websites and magazines. Released in the United States on November 23, 1998, it was the first game in The Legend of Zelda series that was visually displayed in 3D . \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" November 23, 1998\",\"id\":\"5f6ce9d805615a85623ec2ba\"},{\"name\":\"The Legend of Zelda: Oracle of Ages\",\"description\":\"The Legend of Zelda: Oracle of Ages is one of two The Legend of Zelda  titles released for the Game Boy Color, the other being Oracle of Seasons, both representing the seventh and eighth main installments of the series. Released near the end of the system's lifespan, Oracle of Ages and its counterpart were said to \\\"send the Game Boy Color out with a bang.\\\" In anticipation of the upcoming release of the Game Boy Color's successor, the Game Boy Advance, the games exhibited special features  when played on the new handheld system. \",\"developer\":\"Capcom\",\"publisher\":\"Nintendo\",\"released_date\":\" May 14, 2001\",\"id\":\"5f6ce9d805615a85623ec2b9\"},{\"name\":\"The Legend of Zelda: Link's Awakening DX\",\"description\":\"The game was also made available on the Nintendo 3DS eShop on June 7, 2011 at the price of $5.99 US. \",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" December 15, 1998\",\"id\":\"5f6ce9d805615a85623ec2bb\"},{\"name\":\"The Legend of Zelda: Majora's Mask\",\"description\":\"The Legend of Zelda: Majora's Mask is the sixth main installment of The Legend of Zelda series, first released on the Nintendo 64 in 2000. Unique among The Legend of Zelda series, the game includes a time system that spans three days, and this cycle must be reset periodically to progress through the game. Majora's Mask is one of the few Zelda games in which Ganon does not play any role whatsoever. In addition, Princess Zelda does not play a major role; she is only seen once in a flashback scene from Ocarina of Time. \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" October 26, 2000\",\"id\":\"5f6ce9d805615a85623ec2bc\"},{\"name\":\"Zelda II: The Adventure of Link\",\"description\":\"A few years after the defeat of Ganon and the rescue of Princess Zelda, Link, now at the age of sixteen, is disturbed by the appearance of a mark on the back of his hand. Upon seeing this mark, Impa, the nurse of Princess Zelda, tells him the story of how, ages ago, the King of Hyrule had hidden a third part of the Triforce, the Triforce of Courage, in the Great Palace to safeguard it from evil. \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" January 14, 1987\",\"id\":\"5f6ce9d805615a85623ec2bd\"},{\"name\":\"The Legend of Zelda: The Wind Waker\",\"description\":\"The Legend of Zelda: The Wind Waker is the tenth main installment of The Legend of Zelda series. It is the first Zelda game for the Nintendo GameCube, and was released in Japan on December 13, 2002, in Canada and the United States on March 24, 2003, in South Korea on April 16, 2003, in Europe on May 2, 2003 and in Australia on May 7, 2003. \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" March 24, 2003\",\"id\":\"5f6ce9d805615a85623ec2bf\"},{\"name\":\"The Legend of Zelda: Twilight Princess\",\"description\":\"The Legend of Zelda: Twilight Princess is the thirteenth main installment of The Legend of Zelda series, released for both the Nintendo GameCube and Wii. It was highly anticipated by many members of the gaming community and was regarded as finally fulfilling the dreams of those who wanted a much more realistic and mature Zelda game, as seen in the SpaceWorld 2000 GameCube Tech Demo. This is the first Zelda game to be rated T by ESRB and 12+ by PEGI. The reason is probably because of violence, blood , and signs of nudity . This game is also notable for being the first console Zelda title released in the United States before Japan, as the Wii version was released in America on November 19, 2006, whereas the Japanese versions were released on December 2. Because of this, Twilight Princess was one of the launch titles for the Wii alongside Wii Sports in the United States. \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" November 19, 2006\",\"id\":\"5f6ce9d805615a85623ec2be\"},{\"name\":\"The Legend of Zelda: Oracle of Seasons\",\"description\":\"The Legend of Zelda: Oracle of Seasons is one of two The Legend of Zelda  titles released for the Game Boy Color, the other being Oracle of Ages, both representing the seventh and eighth main installments of the series. Released near the end of the system's lifespan, Oracle of Seasons and its counterpart were said to \\\"send the Game Boy Color out with a bang.\\\" In anticipation of the upcoming release of the Game Boy Color's successor, the Game Boy Advance, the games exhibited special features  when played on the new handheld system. \",\"developer\":\"Capcom\",\"publisher\":\"Nintendo\",\"released_date\":\" May 14, 2001\",\"id\":\"5f6ce9d805615a85623ec2c0\"},{\"name\":\"The Legend of Zelda: Spirit Tracks\",\"description\":\" \",\"developer\":\"Nintendo EAD\",\"publisher\":\"Nintendo\",\"released_date\":\" December 7, 2009\",\"id\":\"5f6ce9d805615a85623ec2c5\"},{\"name\":\"BS The Legend of Zelda: Ancient Stone Tablets\",\"description\":\"BS The Legend of Zelda: Ancient Stone Tablets is a downloadable four-part episodic game that was available for the BS-X Broadcasting System add-on for the Super Famicom , and the third title in the BS Zelda series. Each episode was downloaded from St. GIGA's satellite radio service and stored on memory packs but could only be played once a week during the broadcast period. \",\"developer\":\"Nintendo\",\"publisher\":\"St. GIGA\",\"released_date\":\"March 30, 1997\",\"id\":\"5f6ce9d805615a85623ec2ca\"},{\"name\":\"Hyrule Warriors\",\"description\":\"Hyrule Warriors is a Legend of Zelda spin-off game for the Wii U that was released on the 19th of September 2014, the 20th of September 2014, the 26th of September 2014 and the 14th of August 2014 in Europe, Australia, North America and Japan respectively. It combines the world of The Legend of Zelda  with the action of Koei Tecmo's Dynasty Warriors series and was developed by Team Ninja and Omega Force. \",\"developer\":\"Omega Force\",\"publisher\":\"Nintendo\",\"released_date\":\" September 26, 2014\",\"id\":\"5f6ce9d805615a85623ec2cf\"},{\"name\":\"The Legend of Zelda: Four Swords Adventures\",\"description\":\"One night, an ominous cloud covers Hyrule, throwing fear into the people. Princess Zelda calls upon her most trusted childhood friend, Link. She wants to check up on the Four Sword, fearing that the seal that imprisoned the evil wind sorcerer Vaati inside of it at the end of Four Swords might have weakened. Inside the castle they meet up with the gathered six Maidens, whose purpose is to protect Hyrule as well as the Four Sword Sanctuary. With the help of the maidens Zelda summons a portal to the sanctuary.  \",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" June 7, 2004\",\"id\":\"5f6ce9d805615a85623ec2c1\"},{\"name\":\"The Legend of Zelda: Tri Force Heroes\",\"description\":\"The Legend of Zelda: Tri Force Heroes is the eighteenth main installment of The Legend of Zelda series. The game was released on October 23, 2015 in North America and Europe, October 24 in Australia, and October 22 in Japan. It was revealed during E3 2015 on June 16. \",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" October 23, 2015\",\"id\":\"5f6ce9d805615a85623ec2d4\"},{\"name\":\"The Legend of Zelda: Breath of the Wild\",\"description\":\"The Legend of Zelda: Breath of the Wild is the nineteenth main installment of The Legend of Zelda series. It was released simultaneously worldwide for the Wii U and Nintendo Switch on March 3, 2017\",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" March 3, 2017\",\"id\":\"5f6ce9d805615a85623ec2c9\"},{\"name\":\"The Legend of Zelda: Tri Force Heroes\",\"description\":\"The Legend of Zelda: Tri Force Heroes is the eighteenth main installment of The Legend of Zelda series. The game was released on October 23, 2015 in North America and Europe, October 24 in Australia, and October 22 in Japan. It was revealed during E3 2015 on June 16. \",\"developer\":\"Nintendo\",\"publisher\":\"Nintendo\",\"released_date\":\" October 23, 2015\",\"id\":\"5f6ce9d805615a85623ec2ce\"},{\"name\":\"Zelda: The Wand of Gamelon\",\"description\":\"A product of a compromise between Nintendo and Philips due to their failure to release a CD-ROM based add-on to the Super Nintendo Entertainment System, The Wand of Gamelon, alongside the other two, are the only licensed The Legend of Zelda  games developed for and released on a non-Nintendo system. The games have been subject to much criticism, and Nintendo does not recognize them as canon to The Legend of Zelda series. \",\"developer\":\"Animation Magic\",\"publisher\":\"Philips Media\",\"released_date\":\" October 10, 1993\",\"id\":\"5f6ce9d805615a85623ec2d3\"},{\"name\":\"The Legend of Zelda: Four Swords\",\"description\":\"As part of the 25th Anniversary of The Legend of Zelda series, an enhanced port of the game was released for the Nintendo DSi and Nintendo 3DS as a limited-time free download, Four Swords Anniversary Edition. \",\"developer\":\"Capcom\",\"publisher\":\"Nintendo\",\"released_date\":\" December 2, 2002\",\"id\":\"5f6ce9d805615a85623ec2c4\"},{\"name\":\"The Legend of Zelda: The Minish Cap\",\"description\":\"The Legend of Zelda: The Minish Cap is the twelfth main installment of The Legend of Zelda series. It was released for the Game Boy Advance in 2004. \",\"developer\":\"Capcom\",\"publisher\":\"Nintendo\",\"released_date\":\" January 10, 2005\",\"id\":\"5f6ce9d805615a85623ec2c6\"}]}";

        ListGameModel mockListGameModel = convertJsonToListGameModel(mockedResponse);

        when(mockRestTemplate.getForObject("https://zelda.fanapis.com/api/games", ListGameModel.class)).thenReturn(mockListGameModel);

        List<GameModel> result = gameService.listGames();
        Assert.assertEquals(result, mockListGameModel.getData());
        Assert.assertEquals(result.size(), mockListGameModel.getData().size());
    }

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

    public static ListGameModel convertJsonToListGameModel(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, ListGameModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
