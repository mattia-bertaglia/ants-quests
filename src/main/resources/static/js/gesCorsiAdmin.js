/*  modale dettaglio corso */
function dettaglioCorso(corso) {
    document.getElementById('idCorso').value = corso.idCorso;
    document.getElementById('courseName').value = corso.nome;
    document.getElementById('startDate').value = corso.dataInizio;
    document.getElementById('endDate').value = corso.dataFine;
}

/* modale vedi studenti */
function vediStudenti(corso) {

    document.getElementById("add-new-studente").setAttribute("data-corso", corso.idCorso);
    document.getElementById("vedi-studs-label").innerHTML = "Studenti del <strong>" + corso.nome + "</strong>";

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
                    className: 'rubberBand animated',
                    buttons: {
                        ok: {
                            className: 'btn-success'
                        }
                    }
                });
                row.remove();

            } else if (data == "KO") {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Qualcosa è andato storto, Studente NON rimosso dal Corso !!',
                    className: 'shake animated',
                    buttons: {
                        ok: {
                            className: 'btn-success'
                        }
                    }
                });
            }
        });

    }

    $("#add-new-studente").on("click", function () {

        let idCorsoADD = $(this).data("corso");
        let idStudenteADD;

        const container = $("#add-studente");
        let idStudente = container.find("input[name=id-studente]").val();
        let nome = container.find("input[name=nome]").val();
        let cognome = container.find("input[name=cognome]").val();

        $.post("/ges_corsi/find-studs", { "idStudente": idStudente, "nome": nome, "cognome": cognome }).done(function (data) {
            if (data.length == 0) {
                bootbox.alert({
                    title: 'OPS !!',
                    message: 'Nessuno Studente trovato o già presente in un Corso !!',
                    className: 'shake animated',
                    buttons: {
                        ok: {
                            className: 'btn-success'
                        }
                    }
                });
            } else {
                let promptText = [];
                for (const stud of data) {
                    promptText.push({ text: stud.idStudente + " | " + stud.cognome + "  " + stud.nome, value: stud.idStudente, className: 'radio-color' });
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
                                        className: 'rubberBand animated',
                                        buttons: {
                                            ok: {
                                                className: 'btn-success'
                                            }
                                        }
                                    });

                                } else if (data2 == "KO") {
                                    bootbox.alert({
                                        title: 'OPS !!',
                                        message: 'Qualcosa è andato storto, Studente NON aggiunto al Corso !!',
                                        className: 'shake animated',
                                        buttons: {
                                            ok: {
                                                className: 'btn-success'
                                            }
                                        }
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

/* pagine dinamiche */
var current_page = 1;
var records_per_page = 10;

document.addEventListener("DOMContentLoaded", function () {
    var tableBody = document.querySelector("#myTable tbody");
    var rows = Array.from(tableBody.querySelectorAll("tr"));
    var filteredData = rows.map(function (row) {
        var cells = row.querySelectorAll("td");
        return {
            nome: cells[0].innerText,
            dataInizio: cells[1].innerText,
            dataFine: cells[2].innerText,
            dettaglio: cells[3].innerHTML,
            studenti: cells[4].innerHTML
        };
    });

    function prevPage() {
        if (current_page > 1) {
            current_page--;
            updateTable();
        }
    }

    function nextPage() {
        if (current_page < numPages()) {
            current_page++;
            updateTable();
        }
    }

    function numPages() {
        return Math.ceil(filteredData.length / records_per_page);
    }

    function updateTable() {
        tableBody.innerHTML = '';

        var start = (current_page - 1) * records_per_page;
        var end = start + records_per_page;
        var pageData = filteredData.slice(start, end);

        pageData.forEach(function (data) {
            var row = document.createElement("tr");
            row.innerHTML = `
                <td>${data.nome}</td>
                <td>${data.dataInizio}</td>
                <td>${data.dataFine}</td>
                <td class="text-center">${data.dettaglio}</td>
                <td class="text-center">${data.studenti}</td>
            `;
            tableBody.appendChild(row);
        });

        document.getElementById('page').innerText = current_page;
        document.getElementById('totalPages').innerText = numPages();

        document.getElementById('btn_prev').disabled = current_page == 1;
        document.getElementById('btn_next').disabled = current_page == numPages();
    }

    document.getElementById("btn_prev").addEventListener("click", prevPage);
    document.getElementById("btn_next").addEventListener("click", nextPage);

    updateTable();
});




/*  filtri ricerca */
function filterTable() {
    var nomeInput = document.getElementById("myInput").value.toUpperCase();
    var inizioInput = document.getElementById("inizio").value;
    var fineInput = document.getElementById("fine").value;
    var table = document.getElementById("myTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) {
        var nomeTd = tr[i].getElementsByTagName("td")[0];
        var inizioTd = tr[i].getElementsByTagName("td")[1];
        var fineTd = tr[i].getElementsByTagName("td")[2];
        var nomeValue = nomeTd ? nomeTd.textContent || nomeTd.innerText : "";
        var inizioValue = inizioTd ? inizioTd.textContent || inizioTd.innerText : "";
        var fineValue = fineTd ? fineTd.textContent || fineTd.innerText : "";

        var nomeMatch = nomeValue.toUpperCase().indexOf(nomeInput) > -1;
        var inizioMatch = !inizioInput || inizioValue >= inizioInput;
        var fineMatch = !fineInput || fineValue <= fineInput;

        if (nomeMatch && inizioMatch && fineMatch) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}


