package com.projetofinal.zeldaservice.models.classes;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListGameModel {
    private Boolean success;
    private Integer count;
    private List<GameModel> data;
}
