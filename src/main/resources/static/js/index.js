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

    // Controllo Telefono

    document.getElementById('telefono').addEventListener('input', function () {
        const telefonoInput = this.value;
        const errorPhone = document.getElementById('error-phone');
        const correctPhone = document.getElementById('correct-phone');

        // Verifica se il numero è composto esattamente da 10 caratteri numerici
        const isValidPhone = /^\d{10}$/.test(telefonoInput);

        if (isValidPhone) {
            // Mostra il messaggio di successo e nasconde quello di errore
            correctPhone.classList.remove('d-none');
            errorPhone.classList.add('d-none');
        } else {
            // Mostra il messaggio di errore e nasconde quello di successo
            errorPhone.classList.remove('d-none');
            correctPhone.classList.add('d-none');
        }
    });





    // Controllo della password

    document.addEventListener('DOMContentLoaded', function () {
        var signupForm = document.getElementById('signupForm');
        var passwordInput = document.getElementById('password');
        var confirmPasswordInput = document.getElementById('conferma-password');

        var passwordError = document.getElementById('error-pass');
        var lowercaseError = document.getElementById('error-lowercase');
        var uppercaseError = document.getElementById('error-uppercase');
        var numberError = document.getElementById('error-number');
        var lengthError = document.getElementById('error-length');
        var passwordCorrect = document.getElementById('correct-pass');
        var confirmPasswordError = document.getElementById('error-checkpass');
        var confirmPasswordCorrect = document.getElementById('correct-checkpass');

        function checkPasswordComplexity() {
            var password = passwordInput.value;
            var hasLowercase = /[a-z]/.test(password);
            var hasUppercase = /[A-Z]/.test(password);
            var hasNumber = /\d/.test(password);
            var isLengthValid = password.length >= 8;

            if (password === "") {
                passwordError.classList.add('d-none');
                passwordCorrect.classList.add('d-none');
                lowercaseError.classList.add('d-none');
                uppercaseError.classList.add('d-none');
                numberError.classList.add('d-none');
                lengthError.classList.add('d-none');
            } else {
                var showError = false;

                // Messaggi di errore per password

                if (!hasLowercase) {
                    lowercaseError.classList.remove('d-none');
                    showError = true;
                } else {
                    lowercaseError.classList.add('d-none');
                }

                if (!hasUppercase) {
                    uppercaseError.classList.remove('d-none');
                    showError = true;
                } else {
                    uppercaseError.classList.add('d-none');
                }

                if (!hasNumber) {
                    numberError.classList.remove('d-none');
                    showError = true;
                } else {
                    numberError.classList.add('d-none');
                }

                if (!isLengthValid) {
                    lengthError.classList.remove('d-none');
                    showError = true;
                } else {
                    lengthError.classList.add('d-none');
                }

                if (showError) {
                    passwordError.classList.remove('d-none');
                    passwordCorrect.classList.add('d-none');
                } else {
                    passwordError.classList.add('d-none');
                    passwordCorrect.classList.remove('d-none');
                }
            }
        }

        function checkPasswordMatch() {
            var password = passwordInput.value;
            var confirmPassword = confirmPasswordInput.value;
            if (password === "" || confirmPassword === "") {
                confirmPasswordError.classList.add('d-none');
                confirmPasswordCorrect.classList.add('d-none');
            } else if (password !== confirmPassword) {
                confirmPasswordError.classList.remove('d-none');
                confirmPasswordCorrect.classList.add('d-none');
            } else {
                confirmPasswordError.classList.add('d-none');
                confirmPasswordCorrect.classList.remove('d-none');
            }
        }

        passwordInput.addEventListener('input', function () {
            checkPasswordComplexity();
            checkPasswordMatch();
        });

        confirmPasswordInput.addEventListener('input', checkPasswordMatch);

        signupForm.addEventListener('submit', function (event) {
            event.preventDefault(); // Impedisce il submit del form

            var password = passwordInput.value;
            var confirmPassword = confirmPasswordInput.value;

            // To-Do aggiungere controllo per tutti i campi di firstTime



            if (!password || !confirmPassword) {
                alert('Devi completare tutti i campi per registrarti.');
                return;
            }

            var hasLowercase = /[a-z]/.test(password);
            var hasUppercase = /[A-Z]/.test(password);
            var hasNumber = /\d/.test(password);
            var isLengthValid = password.length >= 8;

            if (!hasLowercase || !hasUppercase || !hasNumber || !isLengthValid) {
                passwordError.classList.remove('d-none');
                return;
            } else {
                passwordError.classList.add('d-none');
                passwordCorrect.classList.remove('d-none');
            }

            if (password !== confirmPassword) {
                confirmPasswordError.classList.remove('d-none');
                return;
            } else {
                confirmPasswordError.classList.add('d-none');
                confirmPasswordCorrect.classList.remove('d-none');
            }

            alert('Registrazione avvenuta con successo!');
            signupForm.submit(); // Invia il form
        });
    });


    // Occhietto show/hide password

    document.addEventListener('DOMContentLoaded', function () {
        // Funzione per mostrare la password
        function showPassword(id) {
            var passwordInput = document.getElementById(id);
            var eyeIcon = document.getElementById(id === 'password' ? 'password-eye' : 'confirm-eye');

            passwordInput.type = 'text';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }

        // Funzione per nascondere la password
        function hidePassword(id) {
            var passwordInput = document.getElementById(id);
            var eyeIcon = document.getElementById(id === 'password' ? 'password-eye' : 'confirm-eye');

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
    })
});
