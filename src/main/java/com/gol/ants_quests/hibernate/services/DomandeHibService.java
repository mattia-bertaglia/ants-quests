package com.gol.ants_quests.hibernate.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.repositories.DomandeRepository;

@Service
public class DomandeHibService extends GenericHibService<DomandaQuest, Integer, DomandeRepository>{

    public DomandeHibService(DomandeRepository repository) {
        super(repository);
    }
    
        @Autowired
        private DomandeRepository domandaQuestRepository;
    
        public List<DomandaQuest> getAllDomande() {
            return domandaQuestRepository.findAll();
        }
    
        public Optional<DomandaQuest> getDomandaById(Integer id) {
            return domandaQuestRepository.findById(id);
        }
    
        public DomandaQuest createDomanda(DomandaQuest domandaQuest) {
            return domandaQuestRepository.save(domandaQuest);
        }
    
        public DomandaQuest updateDomanda(DomandaQuest domandaQuest) {
            return domandaQuestRepository.save(domandaQuest);
        }
    
        public void deleteDomanda(Integer id) {
            domandaQuestRepository.deleteById(id);
        }
    }
    
