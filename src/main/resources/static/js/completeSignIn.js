// REGISTRAZIONE
document.addEventListener('DOMContentLoaded', function() {
    var signupForm = document.getElementById('signupForm');
    var passwordInput = document.getElementById('pass');
    var confirmPasswordInput = document.getElementById('checkpass');
  
    var passwordError = document.getElementById('error-pass');
    var passwordCorrect = document.getElementById('correct-pass');
    var confirmPasswordError = document.getElementById('error-checkpass');
    var confirmPasswordCorrect = document.getElementById('correct-checkpass');
  
    passwordInput.addEventListener('input', function() {
        var password = passwordInput.value;
        var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;
        if (!passwordPattern.test(password)) {
            passwordError.classList.remove('hidden');
            passwordCorrect.classList.add('hidden');
        } else {
            passwordError.classList.add('hidden');
            passwordCorrect.classList.remove('hidden');
        }
        checkPasswordMatch();
    });
  
    confirmPasswordInput.addEventListener('input', checkPasswordMatch);
  
    function checkPasswordMatch() {
        var password = passwordInput.value;
        var confirmPassword = confirmPasswordInput.value;
        if (password !== confirmPassword) {
            confirmPasswordError.classList.remove('hidden');
            confirmPasswordCorrect.classList.add('hidden');
        } else {
            confirmPasswordError.classList.add('hidden');
            confirmPasswordCorrect.classList.remove('hidden');
        }
    }
  
    signupForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Impedisce il submit del form

        var password = passwordInput.value;
        var confirmPassword = confirmPasswordInput.value;
  
        // Controllo se tutti i campi sono completati
        if (!password || !confirmPassword) {
            alert('Devi completare tutti i campi per registrarti.');
            return;
        }

        // Controllo complessità password
        var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;
        if (!passwordPattern.test(password)) {
            passwordError.classList.remove('hidden');
            return;
        } else {
            passwordError.classList.add('hidden');
            passwordCorrect.classList.remove('hidden');
        }
  
        // Controllo corrispondenza password
        if (password !== confirmPassword) {
            confirmPasswordError.classList.remove('hidden');
            return;
        } else {
            confirmPasswordError.classList.add('hidden');
            confirmPasswordCorrect.classList.remove('hidden');
        }
  
        // Se tutti i controlli sono superati, procedere con la registrazione
        alert('Registrazione avvenuta con successo!');
        signupForm.submit(); // Invia il form
    });
  });