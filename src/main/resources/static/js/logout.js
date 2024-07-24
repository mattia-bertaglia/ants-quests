// Metodo per bottone di Logout

document.addEventListener('DOMContentLoaded', (event) => {
    const logoutBtn = document.getElementById('logout-btn');

    logoutBtn.addEventListener('click', () => {
        fetch('/logout', {
            method: 'GET',
            credentials: 'include' // Include cookies in the request
        })
            .then(response => {
                if (response.ok) {
                    // Redirect to the login page or home page after successful logout
                    window.location.href = '/login'; // Cambia '/login' con la pagina desiderata
                } else {
                    // Gestione degli errori
                    console.error('Logout fallito:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Errore durante il logout:', error);
            });
    });
});
