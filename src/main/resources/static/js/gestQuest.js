let indiceDomandaGlobal;

function popolaModale(elemento) {
    pulisicDomandeModifica();
    let interoElemento = elemento.closest('li');
    trovaDomandaDaModificare(interoElemento);
    let indiceArray = quest.domanda.findIndex(domanda => domanda.idQstDet === interoElemento.id);
    document.getElementById('domanda_inserita_modifica').value = quest.domanda[indiceArray].domanda;
    let risposte;
    let checkbox;
    let corretta;
    indiceDomandaGlobal = quest.domanda[indiceArray].idQstDet;

    for(let i=0;i<quest.domanda[indiceArray].risp.length;i++){
      // if(quest.domanda[indiceArray].risp[i].risposta != ""){
            aggiungiRisposta('elenco-risposte', quest.domanda[indiceArray].risp[i].idAns);
            risposte = document.querySelectorAll('.risposta_inserita');
            checkbox = document.querySelectorAll('.risp_corretta');
            risposte[risposte.length - 1].value = quest.domanda[indiceArray].risp[i].risposta;
            corretta = quest.domanda[indiceArray].risp[i].corretta;
            
            if (corretta == "true") {
                checkbox[checkbox.length - 1].checked = true;
            }
      //  }
    }

}

let contrisp = 0;
function aggiungiRisposta(elenco, idRisposta) {
    let tmpIdRisp;
    let tmpIdDom;
    if(idRisposta) {
        tmpIdRisp = `data-id-risp="${idRisposta}"`;
    } else {
        tmpIdRisp = `data-id-risp="t${contrisp++}"`;
    }

    let templateRisposta = 
                 `<div class="row" ${tmpIdRisp}>
                        <div class="col-md-6">
                            <div class="input-group input-group-sm mb-1">
                                <input type="text" class="form-control col-6 risposta_inserita" name="risposta">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                    <input class="form-check-input risp_corretta" name="corretta" type="checkbox">
                                <label class="form-check-label" for="corretta">
                                    Corretta
                                </label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-outline-danger"
                                onclick="eliminaRisposta(this)">Elimina</button>
                        </div>
                    </div>`;

    document.getElementById(elenco).insertAdjacentHTML("beforeend",templateRisposta);
  
}

function eliminaRisposta(button) {

    const row = button.closest("div.row");

    let idRisposta = row.getAttribute('data-id-risp');
    
    let idDomanda = quest.domanda.findIndex(indiceDom => indiceDom.idQstDet === indiceDomandaGlobal);


    
    let indiceArray = quest.domanda[idDomanda].risp.find(indice => indice.idAns === idRisposta);

    let indiceNumerico = quest.domanda[idDomanda].risp.findIndex(indice => indice.idAns === indiceArray.idAns);
    quest.domanda[idDomanda].risp[indiceNumerico].risposta = "";
        

    row.parentNode.removeChild(row);

    
}



let domandaDaEliminare = null;

function trovaDomanda(elemento) {
    domandaDaEliminare = elemento.closest('li');
}

function eliminaDomanda() {

    let id = domandaDaEliminare.id;
    if(id != ""){
        let domanda = quest.domanda.find(domanda => domanda.idQstDet === id);
        domanda.domanda = "";
        domanda.risp = [];
    }else{
        let indiceArray = quest.domanda.indexOf(domandaDaEliminare);
        quest.domanda.splice(indiceArray, 1);   
    }

    domandaDaEliminare.remove();

    var modal = document.getElementById('modale-elimina-domanda');
    var modalInstance = bootstrap.Modal.getInstance(modal);
    modalInstance.hide();
}


function addDomanda() {
    const textareaDomanda = document.getElementById('domanda_inserita').value;
    const lista = document.getElementById('elenco-domande');
    
    domanda = new Domanda();
    domanda.domanda = textareaDomanda;
    quest.domanda.push(domanda);
    risposteTemplate = ""


    var modaleElement = document.getElementById('modale-aggiungi-domanda');
    var listaRisposte = modaleElement.querySelectorAll(".risposta_inserita");
    var listaRispCorretta = modaleElement.querySelectorAll(".risp_corretta");

    for (let i = 0; i < listaRisposte.length; i++) {

        ris = new Risposta();
        ris.risposta =  listaRisposte[i].value;
        ris.corretta = listaRispCorretta[i].checked;
        domanda.risp.push(ris);

        risposteTemplate += `
             <li class="${ris.corretta  ? 'list-group-item list-group-item-success' 
                : 'list-group-item list-group-item-danger'}">
             ${ris.risposta}
            </li>`;
    }

    let domandaTemplate = 
    `<li class="list-group-item">
        <details>
            <summary>` + textareaDomanda + `</summary>
            <div>
                <hr>
                <button class="btn btn-outline-primary" data-bs-toggle="modal"
                    data-bs-target="#modale-modifica-domanda" onclick="popolaModale(this)">Modifica
                </button>
                <button class="btn btn-outline-danger" data-bs-toggle="modal"
                    data-bs-target="#modale-elimina-domanda" onclick="trovaDomanda(this)">Elimina
                </button>
                <div class="mb-3 mt-3"></div>
                <ol class="list-group list-group-numbered">${risposteTemplate}</ol>
            </div>
        </details>
    </li>`;
    
    lista.innerHTML += domandaTemplate;

    pulisicModaleAggiungi()
    const modaleAggiungi = document.getElementById('modale-aggiungi-domanda');
    const modale = bootstrap.Modal.getInstance(modaleAggiungi);
    modale.hide();
}


let domandaDaModificare = null;

function trovaDomandaDaModificare(elemento) {
    domandaDaModificare = elemento;
}

function modDomanda() {

    let indiceArray = quest.domanda.findIndex(domanda => domanda.idQstDet === domandaDaModificare.id);
    quest.domanda[indiceArray].domanda = document.getElementById('domanda_inserita_modifica').value;
    let summaryElement = domandaDaModificare.querySelector('summary');
    let olElement =domandaDaModificare.querySelector('ol');
    summaryElement.textContent = document.getElementById('domanda_inserita_modifica').value;
    let modaleModificaId = document.getElementById("modale-modifica-domanda");

    
        let risposte = document.querySelectorAll('.risposta_inserita');
        let checkbox = document.querySelectorAll('.risp_corretta');
        let idRisposte = modaleModificaId.querySelectorAll('[data-id-risp]');;

        for (let i = 0; i < risposte.length-1; i++) {

            if(quest.domanda[indiceArray].risp[i] != null){

                ris.idAns = idRisposte[i].getAttribute('data-id-risp');
                quest.domanda[indiceArray].risp[i].risposta = risposte[i+1].value;
        
                if(checkbox[i+1].checked){
                    quest.domanda[indiceArray].risp[i].corretta = "true";
 
                }else{
                    quest.domanda[indiceArray].risp[i].corretta = "false";
                }

                
            }else{
                ris = new Risposta();
                ris.idAns = idRisposte[i].getAttribute('data-id-risp');
                ris.risposta = risposte[i+1].value;
                if(checkbox[i+1].checked){
                    ris.corretta = "true"
                }else{
                    ris.corretta = "false"
                }
                quest.domanda[indiceArray].risp.push(ris);

            }
        }
 
        let idRisposta; 
        for(let i=0;i<quest.domanda.length;i++){
            for(let y=0;y<quest.domanda[i].risp.length;y++){

                if ((quest.domanda[i].risp[y].idAns).startsWith("t")) {
                    quest.domanda[i].risp[y].idAns = "";
                       
                }
            }
        }



        olElement.innerHTML = "";
        for(let i = 0; i < quest.domanda[indiceArray].risp.length; i++){
            //if(quest.domanda[indiceArray].risp[i].risposta != ""){        
                let domandaTemplate = 
                `<li class="` + (quest.domanda[indiceArray].risp[i].corretta == 'true' ? 'list-group-item list-group-item-success' 
                        : 'list-group-item list-group-item-danger') + `">` + quest.domanda[indiceArray].risp[i].risposta + `</li>`
        
               olElement.innerHTML += domandaTemplate;
            //}
        }

    const modaleAggiungi = document.getElementById('modale-modifica-domanda');
    const modale = bootstrap.Modal.getInstance(modaleAggiungi);
    modale.hide();
}


$(document).ready(function(){

    var elementoInput = $("#titolo");
    var elementoOption = $("#type");
    var bottone = $("#btn_salva_test");

    elementoInput.on("input", function() {
        controllaCampiTest();
    });

    elementoOption.on("change", function() {
        controllaCampiTest();   
    });

    function controllaCampiTest() {
        var valoreInput = elementoInput.val();
        var valoreOption = elementoOption.val();

        if (valoreInput.trim() !== "" && valoreOption.trim() !== "") {
            bottone.prop("disabled", false);
        } else {
            bottone.prop("disabled", true);
        }
    }


    $("#domanda_inserita").on("input", function() {
        abilitaPulsanteAggiungi();
    });

    $("#risposta_inserita").on("input", function() {
        abilitaPulsanteAggiungi();
    });

    function abilitaPulsanteAggiungi() {
        const pulsante = $("#btn_add_domanda");
    
        if ($("#domanda_inserita").val().trim() !== "" && $("#risposta_inserita").val().trim() !== "") {
            pulsante.prop("disabled", false);
        } else {
            pulsante.prop("disabled", true);
        }
    }

    window.salvaTest = function (id_quest) {
        $.post("/quest/savetest", {
            "type": document.getElementById("type").value,
            "titolo": document.getElementById("titolo").value,
            "id_quest": id_quest
        }).done(function (id_nuovo_quest) {

            if (id_nuovo_quest != "") {
                mostraToast('salvaOk');
                quest.idQst = id_nuovo_quest;
                $("#btn_aggiungi_domanda").prop("disabled", false);
                $("#btn_salva_domanda").prop("disabled", false);
            }else{
                mostraToast('salvaErr');
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                mostraToast('errorDB');
            }
        });
    }

    window.gestisciDomande = function () {
        const oggettoQuest = JSON.stringify(quest);

        $.post({
            url: "/quest/gestionedomande",
            contentType: "application/json",
            data: oggettoQuest
        }).done(function (esito) {
            if (esito == "OK") {
                mostraToast('salvaOk');
            } else{
                mostraToast('salvaErr');
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                mostraToast('errorDB');
            }
        });
    }

});

function pulisicModaleAggiungi() {
    document.getElementById('domanda_inserita').value = '';
    document.getElementById('corretta').checked = false;
    document.getElementById('risposta_inserita').value =  '';
    document.getElementById("nuovo-elenco-risposte").innerHTML = '';
    $("#btn_add_domanda").prop("disabled", true);
}

function disabilitaBottone(bottone){
    $('#'+bottone).prop("disabled", true);
}

function pulisicDomandeModifica(){
    document.getElementById('domanda_inserita_modifica').value = '';
    document.getElementById('elenco-risposte').innerHTML = "";
}

function mostraToast(nomeToast){
    const toastElemento = document.getElementById(nomeToast)
    const toast = bootstrap.Toast.getOrCreateInstance(toastElemento);
    toast.show()
}


window.onload = function verificaCampiInit(){
    if(document.getElementById("titolo").value != ""){
        document.getElementById("btn_aggiungi_domanda").disabled = false;
        document.getElementById("btn_salva_test").disabled = false;
        document.getElementById("btn_salva_domanda").disabled = false;
    }     
}

function aggiungiRispostaObbligatoria(elenco) {
    let templateRisposta = 
                 `<div class="row">
                        <div class="col-md-6">
                            <div class="input-group input-group-sm mb-1">
                                <input type="text" class="form-control col-6 risposta_inserita">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                    <input class="form-check-input risp_corretta" type="checkbox">
                                <label class="form-check-label" for="corretta">
                                    Corretta
                                </label>
                            </div>
                        </div>
                    </div>`;

    document.getElementById(elenco).insertAdjacentHTML("beforeend",templateRisposta); 
}







