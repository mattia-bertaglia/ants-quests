// Seleziona i pulsanti e gli elementi necessari
const signinbtn = document.querySelector('.signinbtn');
const signupbtn = document.querySelector('.signupbtn');
const formbox = document.querySelector('.form-box'); // Corretto da .formbox a .form-box
const body = document.querySelector('body'); // Corretto da .body a body

// Aggiunge un evento di click al pulsante "Registrati"
signupbtn.onclick = function() {
  formbox.classList.add('active');
  body.classList.add('active');
};

// Aggiunge un evento di click al pulsante "Accedi"
signinbtn.onclick = function() {
  formbox.classList.remove('active');
  body.classList.remove('active');
};

// REGISTRAZIONE
const email = document.getElementById("mail");
const errormail = document.getElementById("error-mail");
const correctmail = document.getElementById("correct-mail");
let emailok = false;
const pass = document.getElementById("pass");
const checkpass = document.getElementById("checkpass");
const errorpass = document.getElementById("error-pass");
const correctpass = document.getElementById("correct-pass");
const errorcheckpass = document.getElementById("error-checkpass");
const correctcheckpass = document.getElementById("correct-checkpass");
let passok = false;

email.addEventListener("input", function(){
    checkEmail();
});

pass.addEventListener("input", function(){
    checkPass();
});

checkpass.addEventListener("input", function(){
    checkPass();
});

function signup() {
    checkEmail();
    checkPass();
    
    if (!emailok || !passok) {
        alert("NON PUOI REGISTRARTI");
        return false;
    } else {
        alert("PUOI REGISTRARTI");
        return true;
    }
}

function checkEmail() {
    if (isValidEmail(email.value)) {
        errormail.classList.add("hidden");
        correctmail.classList.remove("hidden");
        emailok = true;
    } else {
        correctmail.classList.add("hidden");
        errormail.classList.remove("hidden");
        emailok = false;
    }
}

function isValidEmail(email) {
    const regex = /^[\w-+.]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

function checkPass() {
    if (isValidPass(pass.value) && pass.value.length > 5) {
        errorpass.classList.add("hidden");
        correctpass.classList.remove("hidden");
    } else {
        correctpass.classList.add("hidden");
        errorpass.classList.remove("hidden");
    }

    if (pass.value === checkpass.value && pass.value != "" && pass.value.length > 5) {
        errorcheckpass.classList.add("hidden");
        correctcheckpass.classList.remove("hidden");
        passok = (isValidPass(pass.value) && (pass.value.length > 5));
    } else {
        correctcheckpass.classList.add("hidden");
        errorcheckpass.classList.remove("hidden");
        passok = false;
    }
}

function isValidPass(pass) {
    const regex = /^[a-zA-Z0-9]+$/;
    return regex.test(pass);
}

// logica per controllare che l'email non sia già esistente all'interno del database
// logica controllo se l'utente è abilitato



// controllo password
// pass e checkpass
// error-pass e error-checkpass



// LOGIN

// controllo email



// controllo password




// cazzatine

// cambio da password a text per visualizzare la password

// se sbagli la password 3 volte la pagina esplode (preferenza di ela)