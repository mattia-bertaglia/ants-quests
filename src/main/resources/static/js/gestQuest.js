function popolaModale(domanda) {
    //document.getElementById('textAreaDomanda').value = domanda.domanda;
    document.getElementById('textAreaDomanda').value = domanda.domanda;
    document.getElementById('id_domanda').value = domanda.idQstDet;

    for(let i=0;i<domanda.risp.length;i++){
        //document.getElementById('textAreaDomanda').value += domanda.risp[i].risposta;
        
    }
}


function aggiungiRisposta(elenco) {
    let templateRisposta = 
                 `<div class="row">
                        <div class="col-md-6">
                            <div class="input-group input-group-sm mb-1">
                                <input type="text" class="form-control col-6">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="corretta">
                                <label class="form-check-label" for="corretta">
                                    Corretta
                                </label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-outline-danger"
                                onclick="eliminaRisposta(this.parentNode.parentNode)">Elimina</button>
                        </div>
                    </div>`;

    document.getElementById(elenco).insertAdjacentHTML("beforeend",templateRisposta);
  
}

function eliminaRisposta(risposta) {
    domanda.parentNode.removeChild(risposta);
}

function addDomanda() {
    const textareaDomanda = document.getElementById('domanda_inserita').value;
    const lista = document.getElementById('elenco-domande');
    
    domanda = new Domanda();
    domanda.domanda = textareaDomanda;
    quest.domanda.push(domanda);

    let domandaTemplate = 
    `<li class="list-group-item">
        <details>
            <summary>` + textareaDomanda + `</summary>
            <div>
                <hr>
                <button class="btn btn-outline-primary" data-bs-toggle="modal"
                    data-bs-target="#modale-modifica-domanda">Modifica
                </button>
                <button class="btn btn-outline-danger" data-bs-toggle="modal"
                    data-bs-target="#modale-elimina-domanda">Elimina
                </button>
                <div class="mb-3 mt-3"></div>
                <ol class="list-group list-group-numbered">
                </ol>
            </div>
        </details>
    </li>`;
    
    lista.innerHTML += domandaTemplate;
    pulisicModale()
    const modaleAggiungi = document.getElementById('modale-aggiungi-domanda');
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

    function abilitaPulsanteAggiungi() {
        const pulsante = $("#btn_add_domanda");
    
        if ($("#domanda_inserita").val().trim() !== "") {
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

function pulisicModale() {
    document.getElementById('domanda_inserita').value = '';
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







