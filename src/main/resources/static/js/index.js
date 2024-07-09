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

      // Controllo se tutti i campi sono completati
      if (!nome || !cognome || !email) {
          alert('Devi completare tutti i campi per registrarti.');
          return;
      }
      // se tutti i campi sono stati compilati invia il form
      signupForm.submit();
  });
});




// LOGIN



// cazzatine

// cambio da password a text per visualizzare la password
let pass = document.getElementById("pass");
const showPassword = document.getElementById("toggle-eye");

showPassword.addEventListener('mouseover', function(){
    showPassword.setAttribute("value", "hide");
    pass.setAttribute("type", "text");
});

showPassword.addEventListener('mouseout', function(){
    showPassword.setAttribute("value", "show");
    pass.setAttribute("type", "password");
});
