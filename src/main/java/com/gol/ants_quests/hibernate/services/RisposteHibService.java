package com.gol.ants_quests.hibernate.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.repositories.RisposteRepository;

@Service
public class RisposteHibService extends GenericHibService<RispostaQuest, Integer, RisposteRepository>{

    public RisposteHibService(RisposteRepository repository) {
        super(repository);
    }

    
        @Autowired
        private RisposteRepository rispostaQuestRepository;
    
        public List<RispostaQuest> getAllRisposte() {
            return rispostaQuestRepository.findAll();
        }
    
        public Optional<RispostaQuest> getRispostaById(Integer id) {
            return rispostaQuestRepository.findById(id);
        }
    
        public RispostaQuest createRisposta(RispostaQuest rispostaQuest) {
            return rispostaQuestRepository.save(rispostaQuest);
        }
    
        public RispostaQuest updateRisposta(RispostaQuest rispostaQuest) {
            return rispostaQuestRepository.save(rispostaQuest);
        }
    
        public void deleteRisposta(Integer id) {
            rispostaQuestRepository.deleteById(id);
        }
    }
    