package com.gol.ants_quests.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.error.ErrorsToasts;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorService {

    public final ErrorsToasts errorList;

    public void getToast(Model model, HashMap<String, String> params) {
        if (params.containsKey("status")) {
            String code = params.get("status");
            model.addAttribute("toastTitle", errorList.get(code).getTitle());
            model.addAttribute("toastMessage", errorList.get(code).getMessage());
            model.addAttribute("toastColor", errorList.get(code).getColor());
        }
    }

}
