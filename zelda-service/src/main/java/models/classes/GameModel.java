package models.classes;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameModel {
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private String released_date;
    private String id;
}
