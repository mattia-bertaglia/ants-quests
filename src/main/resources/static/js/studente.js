/* selezione nav Studente */
document.addEventListener('DOMContentLoaded', function () {

    const currentUrl = window.location.pathname;


    const urlToLinkIdMap = {
        '/homeStud/': 'home-studenti-link',
        '/homeStud/profilo': 'profilo-studenti-link',


    };

    const activeLinkId = urlToLinkIdMap[currentUrl];
    if (activeLinkId) {
        const activeLink = document.getElementById(activeLinkId);
        if (activeLink) {
            activeLink.classList.add('active');
        }
    }
});

/* homestud.html */
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


