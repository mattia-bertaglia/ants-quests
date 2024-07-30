/* selezione nav */
document.addEventListener('DOMContentLoaded', function () {

    const currentUrl = window.location.pathname;


    const urlToLinkIdMap = {
        '/homeAdmin/': 'home-link',
        '/ges_studenti/': 'studenti-link',
        '/ges_corsi/': 'corsi-link',
        '/quest/lista': 'questionari-link',
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


