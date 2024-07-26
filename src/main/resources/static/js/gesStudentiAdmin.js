/* inizio script per modale vedi quest */
function vediQuest(stud) {

    document.getElementById("questionariModalLabel").innerHTML = "Esiti questionari <strong>" + stud.nome + " " + stud.cognome + "</strong>";



    const tbodyEsiti = document.getElementById("lista-esiti");
    tbodyEsiti.innerHTML = "";

    for (const esiti of stud.esquestionari) {

        const row = document.createElement('tr');
        row.id = esiti.idEstQst;
        row.innerHTML = `

        <td>${esiti.categoriaQuest}</td>  
        <td>${esiti.titoloQuest}</td> 
        <td>${esiti.dataEsecuzione}</td>
        <td>${esiti.punteggio}</td>
        <td>${esiti.tempo}</td>
        <td class="text-center">
            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#pdfModal"
                onclick="showPdf('/pdf/open?pathFile=${esiti.pathPdf}')">PDF</button>
        </td>`;

        tbodyEsiti.appendChild(row);

    }
}
/* fine script per modale vedi quest */


function showPdf(pdfFile) {
    document.getElementById('pdfIframe').src = pdfFile;
}



function dettaglioStudente(studente) {
    document.getElementById('mod-studId').value = studente.idStudente;
    document.getElementById('mod-ruolo').value = studente.ruolo;
    document.getElementById('mod-nome').value = studente.nome;
    document.getElementById('mod-cognome').value = studente.cognome;
    document.getElementById('mod-dataNascita').value = studente.dataNascita;
    document.getElementById('mod-email').value = studente.usernameEmail;
    document.getElementById('mod-corso').value = studente.corsoId != null ? studente.corsoId : '';
    document.getElementById('mod-cap').value = studente.cap;
    document.getElementById('mod-provincia').value = studente.provincia;
    document.getElementById('mod-telefono').value = studente.telefono;
    document.getElementById('mod-note').value = studente.note;
}
