/* nav */
document.addEventListener('DOMContentLoaded', function () {
    // Ottieni l'URL della pagina corrente
    const currentUrl = window.location.pathname;

    // Mappa di URL a ID dei link
    const urlToLinkIdMap = {
        '/homeAdmin': 'home-link',
        '/ges_studenti/': 'studenti-link',
        '/ges_corsi/': 'corsi-link',
        '/quest/lista': 'questionari-link',
        '/quest/esiti': 'esiti-link',
    };

    // Trova il link corrispondente alla pagina corrente e aggiungi la classe 'active'
    const activeLinkId = urlToLinkIdMap[currentUrl];
    if (activeLinkId) {
        const activeLink = document.getElementById(activeLinkId);
        if (activeLink) {
            activeLink.classList.add('active');
        }
    }
});


