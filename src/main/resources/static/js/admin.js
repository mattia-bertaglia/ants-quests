/* selezione nav */
document.addEventListener('DOMContentLoaded', function () {

    const currentUrl = window.location.pathname;


    const urlToLinkIdMap = {
        '/homeAdmin/': 'home-link',
        '/ges_studenti/': 'studenti-link',
        '/ges_corsi/': 'corsi-link',
        '/quest/lista': 'questionari-link',
        '/quest/gestione': 'questionari-link',
        '/quest/esiti': 'esiti-link',
    };

    const activeLinkId = urlToLinkIdMap[currentUrl];
    if (activeLinkId) {
        const activeLink = document.getElementById(activeLinkId);
        if (activeLink) {
            activeLink.classList.add('active');
        }
    }
});

/* filtri ricerca */
document.addEventListener('DOMContentLoaded', function () {
    // Funzione per aggiungere il comportamento di apri/chiudi a tendina
    function initializeDropdowns() {
        const filterCards = document.querySelectorAll('.filter-card');

        filterCards.forEach(card => {
            const cardHeader = card.querySelector('.card-header');
            const cardBody = card.querySelector('.card-body');

            if (cardHeader && cardBody) {

                cardHeader.style.cursor = 'pointer'; // Indica che è cliccabile


                cardHeader.addEventListener('click', function () {
                    const isVisible = cardBody.style.display === 'block';
                    cardBody.style.display = isVisible ? 'none' : 'block';
                    cardHeader.querySelector("#arrow").innerHTML = !isVisible ?
                        `<svg width="16" height="16" fill="currentColor" class="bi bi-arrow-up"
                    viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                        d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5" />
                </svg>`: `<svg width="16" height="16" fill="currentColor" class="bi bi-arrow-down"
                        viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                            d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1" />
                    </svg>`

                });
            }
        });
    }

    // Inizializza i dropdowns
    initializeDropdowns();
});

