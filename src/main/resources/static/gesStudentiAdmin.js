/* inizio script per modale vedi quest */
function vediQuest(stud) {

    document.getElementById("questionariModalLabel").innerHTML = "Esiti questionari";

    const tbodyEsiti = document.getElementById("lista-esiti");
    tbodyEsiti.innerHTML = "";

    for (const esiti of stud) {
        console.log(esiti);

        const row = document.createElement('tr');
        row.id = esiti.idEstQst;
        row.innerHTML = `

       <td>${esiti.categoriaQuest}</td>  <!-- Aggiungi il categoria(I/C) nella tabella, dopo database -->
    <td>${esiti.titoloQuest}</td> <!-- Aggiungi il titolo nella tabella, dopo datbase -->
    <td>${esiti.dataEsecuzione}</td>
    <td>${esiti.punteggio}</td>
    <td>${esiti.tempo}</td>
   <td class="text-center">
                            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#pdfModal"
                                onclick="showPdf('questionario1.pdf')">PDF</button>
                        </td>`;

        tbodyEsiti.appendChild(row);

    }
}
/* fine script per modale vedi quest */

/* inizio script reset- pulsante tolto in modifica */
function resetCampi() {
    $('#resetConfirmModal').modal('show');
}

document.getElementById('confirmReset').addEventListener('click', function () {
    document.querySelector("#modDetailModal input[name=nome]").value = '';
    document.querySelector("#modDetailModal input[name=cognome]").value = '';
    document.querySelector("#modDetailModal input[name=email]").value = '';
    document.querySelector("#modDetailModal input[name=dataNascita]").value = '';
    document.querySelector("#modDetailModal select[name=ruolo]").selectedIndex = 0; // Reset to default
    $('#resetConfirmModal').modal('hide'); // Chiudi il modal di conferma
});
/* fine script reset- pulsante tolto in modifica */

function showPdf(pdfFile) {
    document.getElementById('pdfFrame').src = pdfFile;
}

/* SCRIPT DA RIVEDERE */

function populateModal(button) {
    document.querySelector("#modDetailModal input[name=studId]").value = button.getAttribute('data-student-id');
    document.querySelector("#modDetailModal input[name=nome]").value = button.getAttribute('data-student-nome');
    document.querySelector("#modDetailModal input[name=cognome]").value = button.getAttribute('data-student-cognome');
    document.querySelector("#modDetailModal input[name=email]").value = button.getAttribute('data-student-email');
    document.querySelector("#modDetailModal input[name=dataNascita]").value = button.getAttribute('data-student-datanascita');
    const ruolo = button.getAttribute('data-student-ruolo')  '';
    document.querySelector("#modDetailModal select[name=ruolo]").value = ruolo;
    const corso = button.getAttribute('data-student-corso')  '';
    document.querySelector("#modDetailModal select[name=corso]").value = corso;
}

/* th:onclick="dettaglioStudente([[${studente}]])"*/
function dettaglioStudente(studente) {
    document.getElementById('studId').value = studente.idStudente;
    document.getElementById('nome').value = studente.nome;
    document.getElementById('cognome').value = studente.cognome;
    document.getElementById('dataNascita').value = studente.dataNascita;
    document.getElementById('cap').value = studente.cap;
    document.getElementById('provincia').value = studente.provincia;
    document.getElementById('telefono').value = studente.telefono;
    document.getElementById('note').value = studente.note;

    document.getElementById('corso').value = studente.corso.nome;

    document.getElementById('email').value = studente.user.usernameEmail;
    document.getElementById('ruolo').value = studente.user.ruolo;
}
