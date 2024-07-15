package com.gol.ants_quests.util.errormsg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "error-messages")
public class ErrorMessage {

    private final HashMap<String, String> messages;

    public String get(String code) {

        String message = messages.get(code);

        if (message == null) {
            message = "qualcosa è andato storto";
        }

        return message;
    }
}
