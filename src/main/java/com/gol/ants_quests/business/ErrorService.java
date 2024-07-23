package com.gol.ants_quests.business;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.util.errormsg.ErrorMessage;
import com.gol.ants_quests.util.errormsg.Message;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorService {

    private final ErrorMessage errorMessage;

    public String getErrorMessage(String code) {
        return errorMessage.get(code);
    }

    public void addErrorMessageToSession(HttpSession session, String code) {
        session.setAttribute("errorMessage", getErrorMessage(code));
    }

    public void addErrorMessageToModel(Model model, String code) {
        model.addAttribute("errorMessage", getErrorMessage(code));
    }

    public String getSuccessMessage(String code) {
        return errorMessage.get(code); // Assumendo che i messaggi di successo siano gestiti allo stesso modo
    }

    public void addSuccessMessageToSession(HttpSession session, String code) {
        session.setAttribute("successMessage", getSuccessMessage(code));
    }

    public void addSuccessMessageToModel(Model model, String code) {
        model.addAttribute("successMessage", getSuccessMessage(code));
    }
}
