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
document.addEventListener('DOMContentLoaded', function() {
  var signupForm = document.getElementById('signupForm');
  var emailInput = document.getElementById('mail');


  signupForm.addEventListener('submit', function(event) {
      event.preventDefault(); // Impedisce il submit del form

      var nome = document.querySelector('input[name="nome"]').value;
      var cognome = document.querySelector('input[name="cognome"]').value;
      var email = emailInput.value;

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

// se sbagli la password 3 volte la pagina esplode (preferenza di ela)