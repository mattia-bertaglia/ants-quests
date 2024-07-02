package com.gol.ants_quests.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.services.EditQuestServices;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EditQuestController {

    @Autowired
    private EditQuestServices editQuestServices;

    @GetMapping
    public List<Quest> getAllQuests() {
        return editQuestServices.getAllQuests();
    }

    @GetMapping("")
    public ResponseEntity<Quest> getQuestById(@PathVariable Integer id) {
        Optional<Quest> quest = editQuestServices.getQuestById(id);
        if (quest.isPresent()) {
            return ResponseEntity.ok(quest.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria")
    public List<Quest> getQuestsByCategoriaId(@PathVariable String categoriaId) {
        return editQuestServices.getQuestsByCategoriaId(categoriaId);
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return editQuestServices.createQuest(quest);
    }

    @PutMapping("")
    public ResponseEntity<Quest> updateQuest(@PathVariable Integer id, @RequestBody Quest questDetails) {
        Optional<Quest> updatedQuest = editQuestServices.updateQuest(id, questDetails);
        if (updatedQuest.isPresent()) {
            return ResponseEntity.ok(updatedQuest.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteQuest(@PathVariable Integer id) {
        editQuestServices.deleteQuest(id);
        return ResponseEntity.noContent().build();
    }
}
