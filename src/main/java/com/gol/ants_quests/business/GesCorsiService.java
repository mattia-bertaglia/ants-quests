package com.gol.ants_quests.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.services.CorsiHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GesCorsiService {

    private final CorsiHibService corsiHibSrv;
    private final ErrorService errorService;

    public List<Corso> findAllCorsi() {
        return corsiHibSrv.findAll(Sort.by(Direction.DESC, "dataInizio"));
    }

    /*
     * ESEMPIO DA PROVARE
     * SBLOCCARE GESCORSICONTROLLER.JAVA
     * 
     * public Optional<Corso> findByNome(String nome) {
     * return corsiHibSrv.findByNome(nome);
     * }
     * public Optional<Corso> findByDataInizioOrDataFine(Date dataInizio, Date
     * dataFine) {
     * return corsiHibSrv.findByDataInizioOrDataFine(dataInizio, dataFine);
     * 
     * public Corso saveCorso(HashMap<String, String> corsoData, Model model){
     * String nome = corsoData.get("nome");
     * Date inizio = corsoData.get("dataInizio");
     * Date fine = corsoData.get("dataFine");
     * 
     * if(inizio == null || fine == null || corsoExists(nome)){
     * erroreService.getToast(model, "registrationError");
     * return null;
     * }
     * 
     * Corso corso = new Corso();
     * corso.setNome(nome);
     * corso.setDataInizio(inizio);
     * corso.setDataFine(fine);
     * 
     * return corsiHibSrv.save(corso);
     * 
     * }
     * 
     * public boolean corsoExists(String nome) {
     * return corsiHibSrv.findByNome(nome).isPresent();
     * }
     * 
     * SOTTO: Se esiste un ID di corso nei parametri, imposta l'ID del corso
     * VALUTARE SE SI POSSONO UNIRE E PROVARLI ENTRAMBI
     */

    public Corso saveCorso(HashMap<String, String> params) {
        Corso corso = new Corso();

        if (params.get("idcorso") != null && !"".equals(params.get("idcorso")))
            corso.setIdCorso(Long.parseLong(params.get("corso")));
        corso.setNome(params.get("nome"));
        if (params.get("datainizio") != null && !"".equals(params.get("datainizio")))
            corso.setDataInizio(Date.valueOf(params.get("datainizio")));
        if (params.get("datafine") != null && !"".equals(params.get("datafine")))
            corso.setDataFine(Date.valueOf(params.get("datafine")));

        return corsiHibSrv.save(corso);
    }

}
