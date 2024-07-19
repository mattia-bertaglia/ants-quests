    document.addEventListener('DOMContentLoaded', function() {
        var signupForm = document.querySelector('.sign-up-htm');
        var emailInput = document.getElementById('email-reg');
        var emailError = document.getElementById('error-mail');
        var emailCorrect = document.getElementById('correct-mail');

        // Inizialmente nasconde i messaggi di errore e di successo
        emailError.classList.add('hidden');
        emailCorrect.classList.add('hidden');

        emailInput.addEventListener('input', function() {
            var email = emailInput.value;
            var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (email) {
                // Controlla se la email è valida
                if (!emailPattern.test(email)) {
                    emailError.classList.remove('hidden');
                    emailCorrect.classList.add('hidden');
                } else {
                    emailError.classList.add('hidden');
                    emailCorrect.classList.remove('hidden');
                }
            } else {
                // Nasconde i messaggi se il campo email è vuoto
                emailError.classList.add('hidden');
                emailCorrect.classList.add('hidden');
            }
        });

        signupForm.addEventListener('submit', function(event) {
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
                emailError.classList.remove('hidden');
                emailCorrect.classList.add('hidden');
                return;
            } else {
                emailError.classList.add('hidden');
                emailCorrect.classList.remove('hidden');
            }

            // Se tutti i controlli sono superati, procedere con la registrazione
            alert('Registrazione avvenuta con successo!');
            signupForm.submit(); // Invia il form
        });
    });




// logica per controllare che l'email non sia già esistente all'interno del database




// logica controllo se l'utente è abilitato




// LOGIN

// controllo email



// controllo password




// cazzatine

// cambio da password a text per visualizzare la password

// se sbagli la password 3 volte la pagina esplode (preferenza di ela)