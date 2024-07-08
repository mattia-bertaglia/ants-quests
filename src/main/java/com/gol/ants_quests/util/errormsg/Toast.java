package com.gol.ants_quests.util.errormsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toast {

    private String code;
    private String title;
    private String message;
    private String color;

}