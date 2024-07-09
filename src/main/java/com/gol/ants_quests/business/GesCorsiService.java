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

    public List<Corso> findAllCorsi() {
        return corsiHibSrv.findAll(Sort.by(Direction.DESC, "dataInizio"));
    }

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
