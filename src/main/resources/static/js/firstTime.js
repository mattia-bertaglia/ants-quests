// REGISTRAZIONE
document.addEventListener('DOMContentLoaded', function() {
    var signupForm = document.getElementById("registration-form"); // Correggi l'id del form

    var passwordInput = document.getElementById("pass");
    var confirmPasswordInput = document.getElementById("checkpass");
    var errorPass = document.getElementById("error-pass");
    var correctPass = document.getElementById("correct-pass");
    var errorCheckPass = document.getElementById("error-checkpass");
    var correctCheckPass = document.getElementById("correct-checkpass");

    confirmPasswordInput.addEventListener('input', function() {
        var password = passwordInput.value;
        var confirmPassword = confirmPasswordInput.value;

        // Controllo corrispondenza password
        if (password !== confirmPassword) {
            errorCheckPass.classList.remove("hidden");
            correctCheckPass.classList.add("hidden");
        } else {
            errorCheckPass.classList.add("hidden");
            correctCheckPass.classList.remove("hidden");
        }
    });

    signupForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Impedisce il submit del form

        var password = passwordInput.value;

        // Controllo complessità password
        var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        if (!passwordPattern.test(password)) {
            errorPass.classList.remove("hidden");
            correctPass.classList.add("hidden");
            return;
        } else {
            errorPass.classList.add("hidden");
            correctPass.classList.remove("hidden");
        }

        var confirmPassword = confirmPasswordInput.value;

        // Controllo corrispondenza password
        if (password !== confirmPassword) {
            errorCheckPass.classList.remove("hidden");
            correctCheckPass.classList.add("hidden");
            return;
        } else {
            errorCheckPass.classList.add("hidden");
            correctCheckPass.classList.remove("hidden");
        }

        // Se tutti i controlli sono superati, procedere con la registrazione
        alert('Registrazione avvenuta con successo!');
        signupForm.submit(); // Invia il form
    });
});
