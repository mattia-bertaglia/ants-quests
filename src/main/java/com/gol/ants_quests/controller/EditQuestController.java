package com.gol.ants_quests.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.services.EditQuestServices;
import lombok.RequiredArgsConstructor;


import java.util.List;


@Controller
@RequiredArgsConstructor
public class EditQuestController {

    @Autowired
    private EditQuestServices editQuestServices;

    



    @GetMapping
    public List<Quest> getAllQuests() {
        return editQuestServices.getAllQuests();
    }






}
