

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.QuestHibService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditQuestController {
  @Autowired
    private QuestHibService questService;

    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuestById(@PathVariable Integer id) {
        Optional<Quest> quest = questService.findById(id);
        if (quest.isPresent()) {
            return ResponseEntity.ok(quest.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Quest> getQuestsByCategoriaId(@PathVariable String categoriaId) {
        return questService.findByCategoriaId(categoriaId);
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.save(quest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quest> updateQuest(@PathVariable Integer id, @RequestBody Quest questDetails) {
        Optional<Quest> quest = questService.findById(id);
        if (quest.isPresent()) {
            Quest questToUpdate = quest.get();
            questToUpdate.setCategoriaId(questDetails.getCategoriaId());
            questToUpdate.setTitolo(questDetails.getTitolo());
            return ResponseEntity.ok(questService.save(questToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable Integer id) {
        questService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
