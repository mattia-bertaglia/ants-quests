document.addEventListener('DOMContentLoaded', function () {
    function controlloEmail() {
        const emailInput = document.getElementById('email-reg');
        const errorMail = document.getElementById('error-mail');
        const correctMail = document.getElementById('correct-mail');
        const signupForm = document.querySelector('.sign-up-htm');

        if (emailInput && errorMail && correctMail && signupForm) {
            emailInput.addEventListener('input', function () {
                const emailValue = emailInput.value;
                const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

                if (emailValue === '') {
                    errorMail.classList.add('d-none');
                    correctMail.classList.add('d-none');
                } else if (emailPattern.test(emailValue)) {
                    errorMail.classList.add('d-none');
                    correctMail.classList.remove('d-none');
                } else {
                    errorMail.classList.remove('d-none');
                    correctMail.classList.add('d-none');
                }
            });

            signupForm.addEventListener('submit', function (event) {
                event.preventDefault();

                var nome = document.getElementById('nome').value;
                var cognome = document.getElementById('cognome').value;
                var email = emailInput.value;

                if (!nome || !cognome || !email) {
                    alert('Devi completare tutti i campi per registrarti.');
                    return;
                }

                var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                if (!emailPattern.test(email)) {
                    errorMail.classList.remove('d-none');
                    correctMail.classList.add('d-none');
                    return;
                } else {
                    errorMail.classList.add('d-none');
                    correctMail.classList.remove('d-none');
                }

                alert('Registrazione avvenuta con successo!');
                signupForm.submit();
            });
        }
    }

    function controlloTelefono() {
        const telefonoInput = document.getElementById('telefono');
        const errorPhone = document.getElementById('error-phone');
        const correctPhone = document.getElementById('correct-phone');

        if (telefonoInput && errorPhone && correctPhone) {
            telefonoInput.addEventListener('input', function () {
                const isValidPhone = /^\d{10}$/.test(telefonoInput.value);

                if (isValidPhone) {
                    correctPhone.classList.remove('d-none');
                    errorPhone.classList.add('d-none');
                } else {
                    errorPhone.classList.remove('d-none');
                    correctPhone.classList.add('d-none');
                }
            });
        }
    }

    function controlloPassword() {
        var passwordInput = document.getElementById('password');
        var confirmPasswordInput = document.getElementById('conferma-password');
        var signupForm = document.getElementById('signupForm');

        if (passwordInput && confirmPasswordInput && signupForm) {
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

                var showError = false;
                if (!hasLowercase) lowercaseError.classList.remove('d-none');
                else lowercaseError.classList.add('d-none');

                if (!hasUppercase) uppercaseError.classList.remove('d-none');
                else uppercaseError.classList.add('d-none');

                if (!hasNumber) numberError.classList.remove('d-none');
                else numberError.classList.add('d-none');

                if (!isLengthValid) lengthError.classList.remove('d-none');
                else lengthError.classList.add('d-none');

                if (!hasLowercase || !hasUppercase || !hasNumber || !isLengthValid) {
                    passwordError.classList.remove('d-none');
                    passwordCorrect.classList.add('d-none');
                } else {
                    passwordError.classList.add('d-none');
                    passwordCorrect.classList.remove('d-none');
                }
            }

            function checkPasswordMatch() {
                var password = passwordInput.value;
                var confirmPassword = confirmPasswordInput.value;
                if (password !== confirmPassword) {
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
                event.preventDefault();

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
                signupForm.submit();
            });
        }
    }

    function togglePasswordVisibility() {
        document.querySelectorAll('.toggle-password').forEach(function (toggle) {
            toggle.addEventListener('mouseover', function () {
                var inputId = this.previousElementSibling.id;
                var passwordInput = document.getElementById(inputId);
                var eyeIcon = this.querySelector('i');
                passwordInput.type = 'text';
                eyeIcon.classList.remove('fa-eye-slash');
                eyeIcon.classList.add('fa-eye');
            });

            toggle.addEventListener('mouseout', function () {
                var inputId = this.previousElementSibling.id;
                var passwordInput = document.getElementById(inputId);
                var eyeIcon = this.querySelector('i');
                passwordInput.type = 'password';
                eyeIcon.classList.remove('fa-eye');
                eyeIcon.classList.add('fa-eye-slash');
            });
        });
    }

    function init() {
        controlloEmail();
        controlloTelefono();
        controlloPassword();
        togglePasswordVisibility();
    }

    init();
});
