
let filters = {
    title: '',
    category: '',
    user: '',
    dateStart: '',
    dateEnd: ''
};
var current_page = 1;
var records_per_page = 10;
var filteredData = [];

function applyFilters() {
    current_page = 1;
    var table, tr, i;
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    filteredData = [];

    if (filters.dateStart && filters.dateEnd && new Date(filters.dateStart) > new Date(filters.dateEnd)) {
        document.getElementById("noResultsMessage").style.display = "block";
        table.style.display = "none";
        return;
    }

    document.getElementById("noResultsMessage").style.display = "none";
    table.style.display = "table";


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
            filteredData.push(tr[i]);
        }
    }

    updateTable();
}


document.getElementById("titolo").addEventListener("change", changeVisibility);
document.getElementById("categorySelect").addEventListener("change", function () {
    filters.category = this.value;
    applyFilters();
});
document.getElementById("data_inizio").addEventListener("change", changeVisibilityDate);
document.getElementById("data_fine").addEventListener("change", changeVisibilityDate);
document.getElementById("stringa").addEventListener("keyup", myFunction);
document.getElementById("removeFiltersButton").addEventListener("click", removeFilters);

function changeVisibility() {
    filters.category = document.getElementById("titolo").value.split("-")[0];
    filters.title = document.getElementById("titolo").value.split("-")[1];
    applyFilters();
}


function changeVisibilityDate() {
    var startDate = document.getElementById("data_inizio").value;
    var endDate = document.getElementById("data_fine").value;


    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
        document.getElementById("dateError").innerText = "La data di inizio non può essere successiva alla data di fine.";
        document.getElementById("myTable").style.display = "none";
        document.getElementById("noResultsMessage").style.display = "block";
        return;
    } else {
        document.getElementById("dateError").innerText = "";
        document.getElementById("myTable").style.display = "table";
        document.getElementById("noResultsMessage").style.display = "none";
    }

    filters.dateStart = startDate;
    filters.dateEnd = endDate;
    applyFilters();
}


function myFunction() {
    filters.user = document.getElementById("stringa").value;
    applyFilters();
}

// Funzione per rimuovere tutti i filtri (non utilizzata, lascio se si vuole aggiungere pulsante)
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
    document.getElementById("dateError").innerText = '';
    document.getElementById("noResultsMessage").style.display = "none";
    applyFilters();
}


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
    var table = document.getElementById("myTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) {
        tr[i].style.display = 'none';
    }

    for (var i = (current_page - 1) * records_per_page; i < current_page * records_per_page && i < filteredData.length; i++) {
        filteredData[i].style.display = '';
    }

    document.getElementById("page").innerText = current_page;
    document.getElementById("totalPages").innerText = numPages();


    document.getElementById("btn_prev").style.visibility = current_page === 1 ? "hidden" : "visible";
    document.getElementById("btn_next").style.visibility = current_page === numPages() ? "hidden" : "visible";
}


window.onload = function () {
    applyFilters();
};

/* PDF */
document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.view-pdf-btn').forEach(button => {
        button.addEventListener('click', function () {
            var pdfUrl = this.getAttribute('data-pdf-url');
            var iframe = document.getElementById('pdfIframe');
            iframe.src = pdfUrl;
            $('#pdfModal').modal('show');
        });
    });
});
