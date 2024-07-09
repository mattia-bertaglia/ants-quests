// REGISTRAZIONE
document.addEventListener('DOMContentLoaded', function() {
    var signupForm = document.getElementById("registration-form"); // Correggi l'id del form

    signupForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Impedisce il submit del form
        

        if(true){

        } else {
            
        }
    
    });

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




// CONTROLLO DATI PERSONALI DELL'UTENTE



// controllo del cap inserito

const cap = document.getElementById("cap");
const caperror = document.getElementById("cap-error");
const capcorrect = document.getElementById("cap-correct");


cap.addEventListener("onchange", function(){
    const capRegex = /^(0?[1-9]{5})$/;

    if(capRegex.test(cap)){
        caperror.classList.add("hidden");
        capcorrect.classList.remove("hidden");

    } else {
        caperror.classList.remove("hidden");
        capcorrect.classList.add("hidden");
    }

});



// controllo del numero di telefono

const phone = document.getElementById("");
const phoneerror = document.getElementById("");
const phonecorrect = document.getElementById("");

phone.addEventListener("onchage", function(){
    
    const phoneRegex = /^[1-9]{10}$/;

    if(phoneRegex.test(phone)){
        phoneerror.classList.add("hidden");
        phonecorrect.classList.remove("hidden");
    } else {
        phoneerror.classList.remove("hidden");
        phonecorrect.classList.add("hidden");
    }


});







