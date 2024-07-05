package com.gol.ants_quests.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.error.ErrorsToasts;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorService {

    private final ErrorsToasts errorList;

    public void getToast(Model model, HashMap<String, String> params) {
        if (params.containsKey("status")) {
            String code = params.get("status");
            model.addAttribute("toastTitle", errorList.get(code).getTitle());
            model.addAttribute("toastMessage", errorList.get(code).getMessage());
            model.addAttribute("toastColor", errorList.get(code).getColor());
        }
    }

    public void getToast(HttpSession session, String code) {
            session.setAttribute("toastTitle", errorList.get(code).getTitle());
            session.setAttribute("toastMessage", errorList.get(code).getMessage());
            session.setAttribute("toastColor", errorList.get(code).getColor());
        
    }

}
