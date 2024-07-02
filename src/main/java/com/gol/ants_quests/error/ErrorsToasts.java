package com.gol.ants_quests.error;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.error")
public class ErrorsToasts {

    private List<Toast> toasts;

    public Toast get(String code) {
        for (Toast t : toasts) {
            if (code.equals(t.getCode())) {
                return t;
            }
        }
        return new Toast("generico", "Errore Generico", "E successo qualcosa", "bg-warning");
    }

}
