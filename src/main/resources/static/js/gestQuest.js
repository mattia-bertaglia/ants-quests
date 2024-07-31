let indiceDomandaGlobal;
let contDom = 0;
function firstDomanda() {
    if (contDom == 0) {
        creaIdDomanda();
    }
}
function creaIdDomanda() {
    let domanda = new Domanda();
    domanda.idQstDet = "t" + contDom++;
    quest.domanda.push(domanda);
    indiceDomandaGlobal = domanda.idQstDet;

}

function popolaModale(elemento) {
    listaRispDaEliminare = [];
    pulisicDomandeModifica();
    let interoElemento = elemento.closest('li');
    trovaDomandaDaModificare(interoElemento);
    let idDomanda = interoElemento.getAttribute('id');
    let indiceArray = quest.domanda.findIndex(domanda => domanda.idQstDet === idDomanda);
    document.getElementById('domanda_inserita_modifica').value = quest.domanda[indiceArray].domanda;
    let risposte;
    let checkbox;
    let corretta;
    indiceDomandaGlobal = quest.domanda[indiceArray].idQstDet;

    for (let i = 0; i < quest.domanda[indiceArray].risp.length; i++) {
        if (quest.domanda[indiceArray].risp[i].risposta != null) {
            aggiungiRisposta('elenco-risposte', quest.domanda[indiceArray].risp[i].idAns);
            risposte = document.querySelectorAll('.risposta_inserita');
            checkbox = document.querySelectorAll('.risp_corretta');
            risposte[risposte.length - 1].value = quest.domanda[indiceArray].risp[i].risposta;
            corretta = quest.domanda[indiceArray].risp[i].corretta;

            if (corretta == 'true') {
                checkbox[checkbox.length - 1].checked = true;
            }
        }
    }

}

let contrisp = 0;
function aggiungiRisposta(elenco, idRisposta) {
    let tmpIdRisp;

    if (idRisposta) {
        tmpIdRisp = `data-id-risp="${idRisposta}"`;
    } else {
        let ris = new Risposta();
        ris.idAns = "t" + contrisp;
        let indiceArray = quest.domanda.findIndex(domanda => domanda.idQstDet === indiceDomandaGlobal);
        quest.domanda[indiceArray].risp.push(ris);
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
                                    Corretta
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-outline-danger"
                                onclick="eliminaRisposta(this)">Elimina</button>
                        </div>
                    </div>`;

    document.getElementById(elenco).insertAdjacentHTML("beforeend", templateRisposta);

}


let listaRispDaEliminare = [];
function eliminaRisposta(button) {

    const row = button.closest("div.row");

    let idRisposta = row.getAttribute('data-id-risp');
    listaRispDaEliminare.push(idRisposta);
    row.parentNode.removeChild(row);

}



let domandaDaEliminare = null;

function trovaDomanda(elemento) {
    domandaDaEliminare = elemento.closest('li');
}

function eliminaDomanda() {

    let id = domandaDaEliminare.id;
    if (!id.startsWith("t")) {
        let domanda = quest.domanda.find(domanda => domanda.idQstDet === id);
        domanda.domanda = null;
        domanda.risp = null;
    } else {
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

    risposteTemplate = ""

    let idDomanda = quest.domanda.findIndex(indiceDom => indiceDom.idQstDet === indiceDomandaGlobal);
    quest.domanda[idDomanda].domanda = textareaDomanda;

    var modaleElement = document.getElementById('modale-aggiungi-domanda');
    var listaRisposte = modaleElement.querySelectorAll(".risposta_inserita");
    var listaRispCorretta = modaleElement.querySelectorAll(".risp_corretta");

    for (let i = 0; i < listaRisposte.length; i++) {

        //ris = new Risposta();
        quest.domanda[idDomanda].risp[i].risposta = listaRisposte[i].value;
        quest.domanda[idDomanda].risp[i].corretta = listaRispCorretta[i].checked + '';
        //quest.domanda[idDomanda].risp.push(ris);

        risposteTemplate += `
             <li class="${listaRispCorretta[i].checked ? 'list-group-item list-group-item-success'
                : 'list-group-item list-group-item-danger'}">
             ${listaRisposte[i].value}
            </li>`;
    }

    let tmpIdDom = 'id="' + indiceDomandaGlobal + '"';

    let domandaTemplate =
        `<li class="list-group-item" ${tmpIdDom}>
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


    bootbox.alert({
        title: 'Fatto !!',
        message: 'Domanda Aggiunta',
        centerVertical: true,
        className: 'rubberBand animated',
        callback: function () {
            pulisicModaleAggiungi();
            creaIdDomanda();
        }
    });

}


let domandaDaModificare = null;

function trovaDomandaDaModificare(elemento) {
    domandaDaModificare = elemento;
}

function modDomanda() {

    let indiceArray = quest.domanda.findIndex(domanda => domanda.idQstDet === domandaDaModificare.id);
    quest.domanda[indiceArray].domanda = document.getElementById('domanda_inserita_modifica').value;
    let summaryElement = domandaDaModificare.querySelector('summary');
    let olElement = domandaDaModificare.querySelector('ol');
    summaryElement.textContent = document.getElementById('domanda_inserita_modifica').value;
    let modaleModifica = document.getElementById("modale-modifica-domanda");

    listaRispDaEliminare.forEach((elemento) => {
        let index = quest.domanda[indiceArray].risp.find(indice => indice.idAns === elemento);

        let indiceNumerico = quest.domanda[indiceArray].risp.findIndex(indice => indice.idAns === index.idAns)
        if ((quest.domanda[indiceArray].risp[indiceNumerico].idAns).startsWith("t")) {
            quest.domanda[indiceArray].risp.splice(indiceNumerico, 1);
        } else {
            quest.domanda[indiceArray].risp[indiceNumerico].risposta = null;
            quest.domanda[indiceArray].risp[indiceNumerico].corretta = null;
        }
    });

    let risposte = modaleModifica.querySelectorAll('.risposta_inserita');
    let checkbox = modaleModifica.querySelectorAll('.risp_corretta');

    let indiceRisposteElemento = 0;

    for (let i = 0; i < quest.domanda[indiceArray].risp.length; i++) {

        if (quest.domanda[indiceArray].risp[i].risposta != null) {
            if (risposte[indiceRisposteElemento].value != null) {
                quest.domanda[indiceArray].risp[i].risposta = risposte[indiceRisposteElemento].value;

                if (checkbox[indiceRisposteElemento].checked) {
                    quest.domanda[indiceArray].risp[i].corretta = 'true';

                } else {
                    quest.domanda[indiceArray].risp[i].corretta = 'false';
                }
                indiceRisposteElemento++;

            } else {
                indiceRisposteElemento = indiceRisposteElemento + i;
            }

        }
    }

    olElement.innerHTML = "";
    for (let i = 0; i < quest.domanda[indiceArray].risp.length; i++) {

        if (quest.domanda[indiceArray].risp[i].risposta != null) {
            let domandaTemplate =
                `<li class="` + (quest.domanda[indiceArray].risp[i].corretta == 'true' ? 'list-group-item list-group-item-success'
                    : 'list-group-item list-group-item-danger') + `">` + quest.domanda[indiceArray].risp[i].risposta + `</li>`

            olElement.innerHTML += domandaTemplate;
        }
    }

    const modaleAggiungi = document.getElementById('modale-modifica-domanda');
    const modale = bootstrap.Modal.getInstance(modaleAggiungi);
    modale.hide();

    bootbox.alert({
        title: 'Fatto !!',
        message: 'Domanda Modificata',
        centerVertical: true,
        className: 'rubberBand animated'
    });

}


$(document).ready(function () {

    var elementoInput = $("#titolo");
    var elementoOption = $("#type");
    var bottone = $("#btn_salva_test");

    elementoInput.on("input", function () {
        controllaCampiTest();
    });

    elementoOption.on("change", function () {
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


    $("#domanda_inserita").on("input", function () {
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
                bootbox.alert({
                    title: 'Successo !!',
                    message: 'Questionario salvato con successo !!',
                    centerVertical: true,
                    className: 'rubberBand animated'
                });
                quest.idQst = id_nuovo_quest;
                quest.attivo = true;
                $("#quest-attivo").html('Stato: <strong>Attivo</strong>');
                $("#btn_aggiungi_domanda").prop("disabled", false);
                $("#btn_salva_domanda").prop("disabled", false);
            } else {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto, Questionario non salvato !!',
                    centerVertical: true,
                    className: 'shake animated'
                });
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto !!',
                    centerVertical: true,
                    className: 'shake animated'
                });
            }
        });
    }

    window.salvaDomande = function () {
        const oggettoQuest = JSON.stringify(quest);

        $.post({
            url: "/quest/gestionedomande",
            contentType: "application/json",
            data: oggettoQuest
        }).done(function (esito) {
            if (esito == "OK") {
                bootbox.alert({
                    title: 'Successo !!',
                    message: 'Elenco Domande aggiornato !!<br> Si consiglia di ricaricare la pagina Confermando il prossimo popup',
                    centerVertical: true,
                    className: 'rubberBand animated',
                    callback: function () {
                        location.reload();
                        /* $.post("/quest/gestione", { "id_quest": quest.idQst }); */
                    }
                });
            } else {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto, salvataggio non avvenuto',
                    centerVertical: true,
                    className: 'shake animated'
                });
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto',
                    centerVertical: true,
                    className: 'shake animated'
                });
            }
        });
    }

});

function gestisciDomande() {
    for (let i = 0; i < quest.domanda.length; i++) {

        if ((quest.domanda[i].idQstDet).startsWith("t")) {
            quest.domanda[i].idQstDet = null;
        }

        if (quest.domanda[i].risp != null) {
            for (let y = 0; y < quest.domanda[i].risp.length; y++) {

                if ((quest.domanda[i].risp[y].idAns).startsWith("t")) {
                    quest.domanda[i].risp[y].idAns = null;
                }
            }
        }
    }

    if (quest.domanda[quest.domanda.length - 1].domanda == '') {
        quest.domanda.pop();
    }

    salvaDomande();
}

function pulisicModaleAggiungi() {

    document.getElementById('domanda_inserita').value = '';
    document.getElementById("nuovo-elenco-risposte").innerHTML = '';
    $("#btn_add_domanda").prop("disabled", true);
}

/*
function disabilitaBottone(bottone){
    $('#'+bottone).prop("disabled", true);
}*/

function pulisicDomandeModifica() {
    document.getElementById('domanda_inserita_modifica').value = '';
    document.getElementById('elenco-risposte').innerHTML = "";
    //l'elenco risposte nell'oggetto javascript deve essere svuotato
}


window.onload = function verificaCampiInit() {
    if (document.getElementById("titolo").value != "") {
        document.getElementById("btn_aggiungi_domanda").disabled = false;
        document.getElementById("btn_salva_test").disabled = false;
        document.getElementById("btn_salva_domanda").disabled = false;
        document.getElementById("btn_attiva_test").disabled = false;
    }
}