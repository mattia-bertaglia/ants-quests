package com.gol.ants_quests.util.errormsg;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.error")
public class ErrorsToasts {

    private List<Toast> toasts;

    public ErrorsToasts(List<Toast> toasts) {
        this.toasts = toasts;
    }

    // Getters and setters for 'toasts' list

    public Toast get(String code) {
        for (Toast t : toasts) {
            if (code.equals(t.getCode())) {
                return t;
            }
        }
        // ho creato questo if perchè il programma non riesce a prendere i dati da
        // inserire nel messaggio
        // di errore da error.yml
        if (code.equals("usernameExists")) {
            return new Toast("usernameExists", "username già esistente", "The e-mail already exists, try logging in",
                    "bg-warning");
        }
        return new Toast("generico", "Errore Generico", "E' successo qualcosa", "bg-warning");
    }
}
