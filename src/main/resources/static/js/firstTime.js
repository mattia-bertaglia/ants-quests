document.addEventListener('DOMContentLoaded', function () {
    var signupForm = document.getElementById('signupForm');
    var passwordInput = document.getElementById('password');
    var confirmPasswordInput = document.getElementById('conferma-password');

    var passwordError = document.getElementById('error-pass');
    var errorList = document.getElementById('error-list');
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
});
