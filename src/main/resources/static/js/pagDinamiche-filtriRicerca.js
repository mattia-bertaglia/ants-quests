// esitiScript.js

let filters = {
    title: '',
    category: '',
    user: '',
    dateStart: '',
    dateEnd: ''
};
var current_page = 1; // Pagina corrente
var records_per_page = 10; // Record per pagina
var filteredData = []; // Dati filtrati

// Funzione per applicare i filtri
function applyFilters() {
    current_page = 1; // Ripristina alla prima pagina
    var table, tr, i;
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    filteredData = [];

    // Controlla se la data di inizio è maggiore della data di fine
    if (filters.dateStart && filters.dateEnd && new Date(filters.dateStart) > new Date(filters.dateEnd)) {
        document.getElementById("noResultsMessage").style.display = "block";
        table.style.display = "none"; // Nascondi la tabella
        return;
    }

    document.getElementById("noResultsMessage").style.display = "none"; // Nascondi il messaggio
    table.style.display = "table"; // Mostra la tabella

    // Filtra i dati in base ai criteri
    for (i = 1; i < tr.length; i++) {
        let display = true;
        let questionari = tr[i].children;

        if (filters.title) {
            if (!questionari[0].innerText.includes(filters.category) || !questionari[1].innerText.toUpperCase().includes(filters.title.toUpperCase())) {
                display = false;
            }
        }

        if (filters.category) {
            if (!questionari[0].innerText.includes(filters.category)) {
                display = false;
            }
        }

        if (filters.user) {
            if (!questionari[5].innerText.toUpperCase().includes(filters.user.toUpperCase())) {
                display = false;
            }
        }

        if (filters.dateStart || filters.dateEnd) {
            var dataEsecuzione = new Date(questionari[2].innerText.split('-').reverse().join('-'));
            if ((filters.dateStart && dataEsecuzione < new Date(filters.dateStart)) ||
                (filters.dateEnd && dataEsecuzione > new Date(filters.dateEnd))) {
                display = false;
            }
        }

        if (display) {
            filteredData.push(tr[i]); // Aggiungi la riga ai dati filtrati
        }
    }

    updateTable(); // Aggiorna la tabella
}

// Aggiungi event listener agli elementi del modulo
document.getElementById("titolo").addEventListener("change", changeVisibility);
document.getElementById("categorySelect").addEventListener("change", function () {
    filters.category = this.value;
    applyFilters();
});
document.getElementById("data_inizio").addEventListener("change", changeVisibilityDate);
document.getElementById("data_fine").addEventListener("change", changeVisibilityDate);
document.getElementById("stringa").addEventListener("keyup", myFunction);
document.getElementById("removeFiltersButton").addEventListener("click", removeFilters);

// Funzione per cambiare la visibilità in base al titolo
function changeVisibility() {
    filters.category = document.getElementById("titolo").value.split("-")[0];
    filters.title = document.getElementById("titolo").value.split("-")[1];
    applyFilters();
}

// Funzione per cambiare la visibilità in base alle date
function changeVisibilityDate() {
    var startDate = document.getElementById("data_inizio").value;
    var endDate = document.getElementById("data_fine").value;

    // Controlla se la data di inizio è maggiore della data di fine
    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
        document.getElementById("dateError").innerText = "La data di inizio non può essere successiva alla data di fine.";
        document.getElementById("myTable").style.display = "none"; // Nascondi la tabella
        document.getElementById("noResultsMessage").style.display = "block"; // Mostra il messaggio
        return;
    } else {
        document.getElementById("dateError").innerText = ""; // Rimuovi messaggio di errore
        document.getElementById("myTable").style.display = "table"; // Mostra la tabella
        document.getElementById("noResultsMessage").style.display = "none"; // Nascondi il messaggio
    }

    filters.dateStart = startDate;
    filters.dateEnd = endDate;
    applyFilters();
}

// Funzione per cercare per utente
function myFunction() {
    filters.user = document.getElementById("stringa").value;
    applyFilters();
}

// Funzione per rimuovere tutti i filtri
function removeFilters() {
    filters = {
        title: '',
        category: '',
        user: '',
        dateStart: '',
        dateEnd: ''
    };
    document.getElementById("titolo").value = '';
    document.querySelector("[name='type']").selectedIndex = 0;
    document.getElementById("data_inizio").value = '';
    document.getElementById("data_fine").value = '';
    document.getElementById("stringa").value = '';
    document.getElementById("dateError").innerText = ''; // Rimuovi messaggio errore
    document.getElementById("noResultsMessage").style.display = "none"; // Nascondi messaggio
    applyFilters();
}

// Funzioni per la paginazione
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

// Calcola il numero di pagine
function numPages() {
    return Math.ceil(filteredData.length / records_per_page);
}

// Aggiorna la visualizzazione della tabella
function updateTable() {
    var table = document.getElementById("myTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) {
        tr[i].style.display = 'none'; // Nascondi tutte le righe
    }

    for (var i = (current_page - 1) * records_per_page; i < current_page * records_per_page && i < filteredData.length; i++) {
        filteredData[i].style.display = ''; // Mostra solo le righe filtrate
    }

    document.getElementById("page").innerText = current_page;
    document.getElementById("totalPages").innerText = numPages();

    // Gestisci la visibilità dei bottoni di paginazione
    document.getElementById("btn_prev").style.visibility = current_page === 1 ? "hidden" : "visible";
    document.getElementById("btn_next").style.visibility = current_page === numPages() ? "hidden" : "visible";
}

// Inizializza i filtri al caricamento della finestra
window.onload = function () {
    applyFilters();
};
