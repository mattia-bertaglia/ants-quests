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
                                <input type="text" class="form-control col-6" name="risposta"
                                    value="" required>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="1" name="corretta" id="corretta">
                                <label class="form-check-label" for="flexCheckChecked">
                                    Corretta
                                </label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-outline-danger"
                                onclick="eliminaDomanda(this.parentNode.parentNode)">Elimina</button>
                        </div>
                    </div>`;

    document.getElementById(elenco).insertAdjacentHTML("beforeend",templateRisposta);
  
}

function eliminaDomanda(domanda) {
    domanda.parentNode.removeChild(domanda);
}

function inviaCheckbox(){
    if(document.getElementById("corretta").checked) {
        document.getElementById('checkboxHidden').disabled = true;
    }
}





$(document).ready(function(){

    var elementoInput = $("#titolo");
    var elementoOption = $("#type");
    var bottone = $("#btn_salva_test");

    elementoInput.on("input", function() {
        controllaCampi();
    });

    elementoOption.on("change", function() {
        controllaCampi();   
    });

    function controllaCampi() {
        var valoreInput = elementoInput.val();
        var valoreOption = elementoOption.val();

        if (valoreInput.trim() !== "" && valoreOption.trim() !== "") {
            bottone.prop("disabled", false);
        } else {
            bottone.prop("disabled", true);
        }
    }

    const oggettoQuest = JSON.stringify(Quest);
    

/*
    window.salvaTest = function () {
        
        $.post("/quest/savetest", {
            jsonOggetto: JSON.stringify(oggettoQuest)
        }).done(function (id_nuovo_quest) {
            if (id_nuovo_quest != "") {
                mostraToast('salvaOk');
            } else {
                mostraToast('salvaErr');
            }
        }).fail(function () {
            mostraToast('salvaErr');
        });
    }*/

    window.salvaTest = function (id_quest) {
        $.post("/quest/savetest", {
            "type": document.getElementById("type").value,
            "titolo": document.getElementById("titolo").value,
            "id_quest": id_quest
        }).done(function (id_nuovo_quest) {

            if (id_nuovo_quest != "") {
                mostraToast('salvaOk');
            }else{
                mostraToast('salvaErr');
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                mostraToast('salvaErr');
            }
        });
    }


    window.gestisciDomande = function () {
        $.post("/quest/gestionedomande",{
            
        }).done(function (esito) {

            if (esito == "") {
                mostraToast('salvaOk');
            }else{
                mostraToast('salvaErr');
            }
        }).fail(function (errore) {
            if (errore.status != 0) {
                mostraToast('salvaErr');
            }
        });
    }




});

function mostraToast(nomeToast){
    const toastElemento = document.getElementById(nomeToast)
    const toast = bootstrap.Toast.getOrCreateInstance(toastElemento);
    toast.show()
}


window.onload = function verificaCampiInit(){
    if(document.getElementById("titolo").value != ""){
        var button = document.getElementById("bottone_aggiungi_domanda").disabled = false;
        var button = document.getElementById("btn_salva_test").disabled = false;
    }     
}







