// pagine dinamiche
let currentPage = 1;
let totalPages = 0;

function fetchTotalPages() {
    // Esempio di chiamata API per ottenere il numero totale di pagine
    // Puoi sostituire questa funzione con la tua logica di recupero
    return fetch('/api/totalPages')
        .then(response => response.json())
        .then(data => {
            totalPages = data.totalPages;
            document.getElementById('totalPages').innerText = totalPages;
            updatePagination();
        })
        .catch(error => {
            console.error('Errore nel recupero del numero totale di pagine:', error);
        });
}

function updatePagination() {
    const pagination = document.getElementById('pagination');
    const prevPage = document.getElementById('prevPage');
    const nextPage = document.getElementById('nextPage');

    // pulisce i numeri delle pagine esistenti
    const pages = pagination.querySelectorAll('.page-number');
    pages.forEach(page => page.remove());

    // aggiorna lo stato del bottone prev
    if (currentPage === 1) {
        prevPage.classList.add('disabled');
    } else {
        prevPage.classList.remove('disabled');
    }

    // aggiorna lo stato del bottone next
    if (currentPage === totalPages) {
        nextPage.classList.add('disabled');
    } else {
        nextPage.classList.remove('disabled');
    }

    // aggiunge nuovi numeri di pagina
    const maxPagesToShow = 5; // NumberO DI PAGINE PRESENTI NELL'IMPAGINAZIONE
    const halfRange = Math.floor(maxPagesToShow / 2);
    let startPage = Math.max(1, currentPage - halfRange);
    let endPage = Math.min(totalPages, currentPage + halfRange);

    if (currentPage - halfRange < 1) {
        endPage = Math.min(totalPages, endPage + (halfRange - (currentPage - 1)));
    }

    if (currentPage + halfRange > totalPages) {
        startPage = Math.max(1, startPage - (currentPage + halfRange - totalPages));
    }

    for (let i = startPage; i <= endPage; i++) {
        const li = document.createElement('li');
        li.classList.add('page-item', 'page-number');
        if (i === currentPage) {
            li.classList.add('active');
        }
        const a = document.createElement('a');
        a.classList.add('page-link');
        a.href = "#";
        a.innerText = i;
        a.addEventListener('click', (e) => {
            e.preventDefault();
            goToPage(i);
        });
        li.appendChild(a);
        nextPage.parentNode.insertBefore(li, nextPage);
    }

    document.getElementById('currentPage').innerText = currentPage;
}

function goToPage(page) {
    if (page < 1 || page > totalPages) return;
    currentPage = page;
    // Logica per aggiornare il contenuto della pagina può essere aggiunta qui
    updatePagination();
}

document.getElementById('prevPage').addEventListener('click', (e) => {
    e.preventDefault();
    if (currentPage > 1) {
        goToPage(currentPage - 1);
    }
});

document.getElementById('nextPage').addEventListener('click', (e) => {
    e.preventDefault();
    if (currentPage < totalPages) {
        goToPage(currentPage + 1);
    }
});

// Inizializza la paginazione recuperando il numero totale di pagine
fetchTotalPages();
