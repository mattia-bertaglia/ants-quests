/*  modale vedi quest */
function vediQuest(stud) {

    document.getElementById("questionariModalLabel").innerHTML = "Esiti questionari <strong>" + stud.nome + " " + stud.cognome + "</strong>";



    const tbodyEsiti = document.getElementById("lista-esiti");
    tbodyEsiti.innerHTML = "";

    if (stud.esquestionari.length == 0) {
        const row = document.createElement('tr');
        row.innerHTML = `<td class="text-center" colspan="6">Nessun Elemento Recente</td>`;
        tbodyEsiti.appendChild(row);
    }

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

/* pagine dinamiche */
var current_page = 1;
var records_per_page = 10;

document.addEventListener("DOMContentLoaded", function () {
    var tableBody = document.querySelector("#myTable tbody");
    var rows = Array.from(tableBody.querySelectorAll("tr"));
    var filteredData = rows.map(function (row) {
        var cells = row.querySelectorAll("td");
        return {
            ruolo: cells[0].innerText,
            nominativo: cells[1].innerText,
            email: cells[2].innerText,
            corso: cells[3].innerText,
            dettaglio: cells[4].innerHTML,
            questionari: cells[5].innerHTML
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
                <td>${data.ruolo}</td>
                <td>${data.nominativo}</td>
                <td>${data.email}</td>
                <td>${data.corso}</td>
                <td class="text-center">${data.dettaglio}</td>
                <td class="text-center">${data.questionari}</td>
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

/* filtri ricerca */
document.addEventListener("DOMContentLoaded", function () {
    function filterTable() {

        var nominativoInput = document.getElementById("nominativo").value.toUpperCase();
        var ruoloSelect = document.getElementById("ruolo").value;
        var corsoSelect = document.getElementById("corso").value;


        var table = document.getElementById("myTable");
        var tr = table.getElementsByTagName("tr");


        for (var i = 1; i < tr.length; i++) {
            var nominativoTd = tr[i].getElementsByTagName("td")[1];
            var ruoloTd = tr[i].getElementsByTagName("td")[0];
            var corsoTd = tr[i].getElementsByTagName("td")[3];

            var nominativoValue = nominativoTd ? nominativoTd.textContent || nominativoTd.innerText : "";
            var ruoloValue = ruoloTd ? ruoloTd.textContent || ruoloTd.innerText : "";
            var corsoValue = corsoTd ? corsoTd.textContent || corsoTd.innerText : "";


            var nominativoMatch = nominativoValue.toUpperCase().indexOf(nominativoInput) > -1;
            var ruoloMatch = ruoloSelect === "" || ruoloValue.trim() === ruoloSelect;
            var corsoMatch = corsoSelect === "" || corsoValue.indexOf(corsoSelect) > -1;

            if (nominativoMatch && ruoloMatch && corsoMatch) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }

    document.getElementById("nominativo").addEventListener("input", filterTable);
    document.getElementById("ruolo").addEventListener("change", filterTable);
    document.getElementById("corso").addEventListener("change", filterTable);
});




