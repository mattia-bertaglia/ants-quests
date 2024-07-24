// Controllo e-mail per messaggi di errore

document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById('email-reg');
    const errorMail = document.getElementById('error-mail');
    const correctMail = document.getElementById('correct-mail');
    const signupForm = document.querySelector('.sign-up-htm'); // Aggiungi questa riga per il controllo del form

    emailInput.addEventListener('input', function () {
        const emailValue = emailInput.value;
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Semplice regex per email

        if (emailValue === '') {
            // Campo email vuoto
            errorMail.classList.add('d-none');
            correctMail.classList.add('d-none');
        } else if (emailPattern.test(emailValue)) {
            // Email valida
            errorMail.classList.add('d-none');
            correctMail.classList.remove('d-none');
        } else {
            // Email non valida
            errorMail.classList.remove('d-none');
            correctMail.classList.add('d-none');
        }
    });

    signupForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Impedisce il submit del form

        var nome = document.getElementById('nome').value;
        var cognome = document.getElementById('cognome').value;
        var email = emailInput.value;

        // Controllo se tutti i campi sono completati
        if (!nome || !cognome || !email) {
            alert('Devi completare tutti i campi per registrarti.');
            return;
        }

        // Controllo validità email
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email)) {
            errorMail.classList.remove('d-none');
            correctMail.classList.add('d-none');
            return;
        } else {
            errorMail.classList.add('d-none');
            correctMail.classList.remove('d-none');
        }

        // Se tutti i controlli sono superati, procedere con la registrazione
        alert('Registrazione avvenuta con successo!');
        signupForm.submit(); // Invia il form
    });

    // Funzione per mostrare la password
    function showPassword(id) {
        var passwordInput = document.getElementById(id);
        var eyeIcon = document.getElementById(id + '-eye');

        passwordInput.type = 'text';
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
    }

    // Funzione per nascondere la password
    function hidePassword(id) {
        var passwordInput = document.getElementById(id);
        var eyeIcon = document.getElementById(id + '-eye');

        passwordInput.type = 'password';
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
    }

    // Aggiungi eventi ai toggle per la password
    document.querySelectorAll('.toggle-password').forEach(function (toggle) {
        toggle.addEventListener('mouseover', function () {
            var inputId = this.previousElementSibling.id;
            showPassword(inputId);
        });

        toggle.addEventListener('mouseout', function () {
            var inputId = this.previousElementSibling.id;
            hidePassword(inputId);
        });
    });
});
