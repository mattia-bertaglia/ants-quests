document.addEventListener('DOMContentLoaded', function() {
    const signinbtn = document.querySelector('.signinbtn');
    const signupbtn = document.querySelector('.signupbtn');
    const formbox = document.querySelector('.form-box');
    const body = document.querySelector('body');
    const signInContainer = document.querySelector('.box.signin');
    const signUpContainer = document.querySelector('.box.signup');
    const signInForm = document.querySelector('.signinform');
    const signUpForm = document.querySelector('.signupform');
    const signupForm = document.getElementById('signupForm');
    const emailInput = document.getElementById('mail');
    const emailError = document.getElementById('error-mail');
    const emailCorrect = document.getElementById('correct-mail');

    // Evento click sul pulsante "Accedi"
    signinbtn.addEventListener('click', function() {
        formbox.classList.remove('active');
        body.classList.remove('active');
    });

    // Evento click sul pulsante "Registrati"
    signupbtn.addEventListener('click', function() {
        formbox.classList.add('active');
        body.classList.add('active');
    });

    // Evento click sul div contenente il form "Accedi"
    signInContainer.addEventListener('click', function(e) {
        if (!e.target.closest('.signinbtn')) {
            formbox.classList.remove('active');
            body.classList.remove('active');
        }
    });

    // Evento click sul div contenente il form "Registrati"
    signUpContainer.addEventListener('click', function(e) {
        if (!e.target.closest('.signupbtn')) {
            formbox.classList.remove('active');
            body.classList.remove('active');
        }
    });

    // Evento click sul pulsante "Registrati" nel form di registrazione
    signupbtn.addEventListener('click', function() {
        formbox.classList.add('active');
        body.classList.add('active');
    });

    // Gestione del form di registrazione
    emailInput.addEventListener('input', function() {
        var email = emailInput.value;
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email)) {
            emailError.classList.remove('hidden');
            emailCorrect.classList.add('hidden');
        } else {
            emailError.classList.add('hidden');
            emailCorrect.classList.remove('hidden');
        }
    });

    signupForm.addEventListener('submit', function(event) {
        event.preventDefault();

        var nome = document.querySelector('input[name="nome"]').value;
        var cognome = document.querySelector('input[name="cognome"]').value;
        var email = emailInput.value;

        if (!nome || !cognome || !email) {
            alert('Devi completare tutti i campi per registrarti.');
            return;
        }

        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email)) {
            emailError.classList.remove('hidden');
            return;
        } else {
            emailError.classList.add('hidden');
            emailCorrect.classList.remove('hidden');
        }

        alert('Registrazione avvenuta con successo!');
        signupForm.submit();
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