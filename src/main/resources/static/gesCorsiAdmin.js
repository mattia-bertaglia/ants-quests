/* inizio script per modale dettaglio corso */
function dettaglioCorso(corso) {
    document.getElementById('idCorso').value = corso.idCorso;
    document.getElementById('courseName').value = corso.nome;
    document.getElementById('startDate').value = corso.dataInizio;
    document.getElementById('endDate').value = corso.dataFine;
}

/* inizio script per modale vedi studenti */
function vediStudenti(corso) {
    // aggiungo anche idCorso al bottone per aggiungere un nuovo studente
    document.getElementById("add-new-studente").setAttribute("data-corso", corso.idCorso);
    document.getElementById("vedi-studs-label").innerHTML = "Studenti del Corso: <strong>" + corso.nome + "</strong>";

    const tbodyStudenti = document.getElementById("lista-studenti");
    tbodyStudenti.innerHTML = "";

    for (const stud of corso.studenti) {
        const row = document.createElement('tr');
        row.id = stud.idStudente;
        row.innerHTML = `
        <td>${stud.idStudente}</td>
        <td>${stud.nome} ${stud.cognome}</td>
          
        <td class="text-center"><button class="btn btn-danger" onclick="eliminaStudenteDalCorso(${stud.idStudente}, this)">
            <svg width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
            </svg>
        </button></td>`;
        tbodyStudenti.appendChild(row);



    }
}

$(document).ready(function () {


    window.eliminaStudenteDalCorso = function (idStudente, button) {
        // Ottieni la riga corrente
        const row = button.closest('tr');

        // Invia la richiesta al backend per rimuovere lo studente dal corso
        $.post("/ges_corsi/eliminaStudenteDalCorso", { "idStudente": idStudente }).done(function (data) {
            if (data == "OK") {
                bootbox.alert({
                    title: 'Successo !!',
                    message: 'Studente rimosso dal Corso !!',
                    className: 'rubberBand animated'
                });

                // Rimuovi visivamente la riga visivamente qui in caso di successo 
                row.remove();

            } else if (data == "KO") {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto, Studente NON rimosso dal Corso !!',
                    className: 'shake animated'
                });
            }
        });

    }

    $("#add-new-studente").on("click", function () {

        let idCorsoADD = $(this).data("corso");
        let idStudenteADD; // verra aggiunto dal find-studs e dopo averlo selezionato 

        const container = $("#add-studente");
        let idStudente = container.find("input[name=id-studente]").val();
        let nome = container.find("input[name=nome]").val();
        let cognome = container.find("input[name=cognome]").val();

        $.post("/ges_corsi/find-studs", { "idStudente": idStudente, "nome": nome, "cognome": cognome }).done(function (data) {
            if (data.length == 0) {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Nessuno Studente trovato o già presente in un Corso !!',
                    className: 'shake animated'
                });
            } else {
                let promptText = [];
                for (const stud of data) {
                    promptText.push({ text: stud.idStudente + " | " + stud.cognome + "  " + stud.nome, value: stud.idStudente });
                }

                bootbox.prompt({
                    size: 'large',
                    title: 'Seleziona o Conferma lo Studente:',
                    inputType: 'radio',
                    inputOptions: promptText,
                    callback: function (result) {

                        if (result != null) {

                            for (const studData of data) {
                                if (studData.idStudente == result) {
                                    idStudenteADD = studData.idStudente;
                                    const row = document.createElement('tr');
                                    row.id = studData.idStudente;
                                    row.innerHTML = `
                                    <td>${studData.idStudente}</td>
                                    <td>${studData.nome} ${studData.cognome}</td>
                                    
                                    <td class="text-center"><button class="btn btn-danger" onclick="eliminaStudenteDalCorso(${studData.idStudente}, this)">
                                        <svg width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                            </svg>
                                    </button></td>`;
                                    document.getElementById("lista-studenti").appendChild(row);
                                }
                            }

                            // Invia la richiesta al backend per aggiungere lo studente
                            $.post("/ges_corsi/aggiungiStudenteAlCorso", { "idCorso": idCorsoADD, "idStudente": idStudenteADD }).done(function (data2) {
                                if (data2 == "OK") {
                                    bootbox.alert({
                                        title: 'Successo !!',
                                        message: 'Studente aggiunto al Corso !!',
                                        className: 'rubberBand animated'
                                    });

                                } else if (data2 == "KO") {
                                    bootbox.alert({
                                        title: 'OPS !!',
                                        message: 'Qualcosa è andato storto, Studente NON aggiunto al Corso !!',
                                        className: 'shake animated'
                                    });
                                }
                            });
                        }
                    }
                });

            }

        });

    });







});





// BUG NON CERCA CON CAMPI MULTIPLI
/* inizio script per filtro ricerca nome */
function myFunction() {

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("tab-last-quest");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
// BUG NON CERCA CON CAMPI MULTIPLI
/* inizio script per filtro ricerca data inizio */
function myFunction1() {

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("inizio");
    filter = input.value.toUpperCase();
    table = document.getElementById("tab-last-quest");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
// BUG NON CERCA CON CAMPI MULTIPLI
/* inizio script per filtro ricerca data fine */
function myFunction2() {

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("fine");
    filter = input.value.toUpperCase();
    table = document.getElementById("tab-last-quest");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


