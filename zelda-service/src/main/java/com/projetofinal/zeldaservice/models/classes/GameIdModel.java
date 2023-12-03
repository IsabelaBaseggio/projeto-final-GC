package com.projetofinal.zeldaservice.models.classes;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameIdModel {
    private Boolean success;
    private GameModel data;
}
