package com.gol.ants_quests.services;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final EsitiHibService esitiSrv;

    public void findAll(Model model) {

        model.addAttribute("listaQuestionari", esitiSrv.findAll());
    }

}
