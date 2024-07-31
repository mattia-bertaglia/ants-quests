$(document).ready(function () {
    // Selettori jQuery
    const email = $(".email"); // E-mail
    const password = $(".password"); // Password
    const confirm_password = $(".conferma-password"); // Conferma Password
    const show_password = $(".toggle-password"); // Occhietto show/hide password 
    const data_nascita = $(".data-nascita"); // Data di nascita
    const telefono = $(".telefono"); // Numero di telefono
    const provincia = $(".provincia"); // Provincia
    const cap = $(".cap"); // Codice Postale

    // Messaggi di errore/successo
    const errorEmail = $(".error-email");
    const correctEmail = $(".correct-email");
    const errorPassword = $(".error-pass");
    const correctPassword = $(".correct-pass");
    const errorConfirm_Password = $(".error-checkpass");
    const correctConfirm_Password = $(".correct-checkpass");
    const errorDataNascita = $(".error-dataNascita");
    const errorTelefono = $(".error-phone");
    const errorProvincia = $(".error-provincia");
    const errorCap = $(".error-cap");

    // Elenco di messaggi di errore per la password
    const errorLowercase = $(".error-lowercase"); // minuscole
    const errorUppercase = $(".error-uppercase"); // maiuscole
    const errorNumber = $(".error-number"); // numeri
    const errorLength = $(".error-length"); // lunghezza

    // Aggiungere gli event listener agli elementi della pagina
    email.on('input', checkEmail);
    password.on('input', checkPassword);
    confirm_password.on('input', checkPassword);
    data_nascita.on('change', checkDataNascita);
    telefono.on('input', checkTelefono);
    provincia.on('input', checkProvincia);
    cap.on('input', checkCAP);
    show_hidePassword(".toggle-password");

    // Funzioni di controllo degli input
    function checkEmail() {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        email.each(function () {
            const emailValue = $(this).val();
            console.log(`Email Value: ${emailValue}`); // Log del valore dell'email
            if (emailValue === '') {
                correctEmail.addClass('d-none');
                errorEmail.addClass('d-none');
            } else if (emailPattern.test(emailValue)) {
                errorEmail.addClass('d-none');
                correctEmail.removeClass('d-none');
            } else {
                correctEmail.addClass('d-none');
                errorEmail.removeClass('d-none');
            }
        });
    }

    function checkPassword() {
        const passValue = password.val();
        const confirmPassValue = confirm_password.val();

        if (passValue === '') {
            correctPassword.addClass('d-none');
            errorPassword.addClass('d-none');
            hidePasswordErrors();
            return;
        }

        if (validatePassword(passValue)) {
            errorPassword.addClass('d-none');
            correctPassword.removeClass('d-none');
            hidePasswordErrors();
        } else {
            correctPassword.addClass('d-none');
            errorPassword.removeClass('d-none');

            // Verifica dei singoli criteri della password
            if (!/[a-z]/.test(passValue)) {
                errorLowercase.removeClass('d-none');
            } else {
                errorLowercase.addClass('d-none');
            }

            if (!/[A-Z]/.test(passValue)) {
                errorUppercase.removeClass('d-none');
            } else {
                errorUppercase.addClass('d-none');
            }

            if (!/\d/.test(passValue)) {
                errorNumber.removeClass('d-none');
            } else {
                errorNumber.addClass('d-none');
            }

            if (passValue.length < 8) {
                errorLength.removeClass('d-none');
            } else {
                errorLength.addClass('d-none');
            }
        }

        if (confirmPassValue === '') {
            errorConfirm_Password.addClass('d-none');
            correctConfirm_Password.addClass('d-none');
        } else if (confirmPassword(passValue, confirmPassValue)) {
            errorConfirm_Password.addClass('d-none');
            correctConfirm_Password.removeClass('d-none');
        } else {
            correctConfirm_Password.addClass('d-none');
            errorConfirm_Password.removeClass('d-none');
        }
    }

    function show_hidePassword(toggleSelector) {
        $(toggleSelector).on('mouseover', function () {
            const inputId = $(this).siblings('input').attr('id');
            const passwordInput = $(`#${inputId}`);
            const eyeIcon = $(this).find('i');
            passwordInput.attr('type', 'text');
            eyeIcon.removeClass('fa-eye-slash').addClass('fa-eye');
        });

        $(toggleSelector).on('mouseout', function () {
            const inputId = $(this).siblings('input').attr('id');
            const passwordInput = $(`#${inputId}`);
            const eyeIcon = $(this).find('i');
            passwordInput.attr('type', 'password');
            eyeIcon.removeClass('fa-eye').addClass('fa-eye-slash');
        });
    }

    function checkDataNascita() {
        const dataOggi = new Date(); // Ottiene la data e ora corrente
        if (new Date(data_nascita.val()) >= dataOggi) { // Confronta la data inserita con la data odierna
            errorDataNascita.removeClass('d-none'); // Mostra il messaggio di errore se la data è oggi o futura
        } else {
            errorDataNascita.addClass('d-none'); // Nasconde il messaggio di errore se la data è passata
        }
    }


    function checkTelefono() {
        const regexTel = /^\d{10}$/;
        if (regexTel.test(telefono.val())) {
            errorTelefono.addClass('d-none');
        } else {
            errorTelefono.removeClass('d-none');
        }
    }

    function checkProvincia() {
        const regexProvincia = /^[a-zA-Z]{2}$/;
        if (provincia.val() === '') {
            errorProvincia.addClass('d-none');
        } else if (regexProvincia.test(provincia.val())) {
            errorProvincia.addClass('d-none');
        } else {
            errorProvincia.removeClass('d-none');
        }
    }

    function checkCAP() {
        const regexCap = /^\d{5}$/;
        if (regexCap.test(cap.val())) {
            errorCap.addClass('d-none');
        } else {
            errorCap.removeClass('d-none');
        }
    }

    // Funzioni ausiliarie
    function validatePassword(password) {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
        return passwordPattern.test(password);
    }

    function confirmPassword(password, confirmPassword) {
        return password === confirmPassword;
    }

    function hidePasswordErrors() {
        errorLowercase.addClass('d-none');
        errorUppercase.addClass('d-none');
        errorNumber.addClass('d-none');
        errorLength.addClass('d-none');
    }
});
