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

  var emailError = document.getElementById('error-mail');
  var emailCorrect = document.getElementById('correct-mail');

  emailInput.addEventListener('input', function() {
      var email = emailInput.value;
      var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (!emailPattern.test(email)) {
          emailError.classList.remove('hidden');
          emailCorrect.classList.add('hidden');
      } else {
          emailError.classList.add('hidden');
          emailCorrect.classList.remove('hidden');
      }
  });

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

      // Controllo validità email
      var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (!emailPattern.test(email)) {
          emailError.classList.remove('hidden');
          return;
      } else {
          emailError.classList.add('hidden');
          emailCorrect.classList.remove('hidden');
      }


      // Se tutti i controlli sono superati, procedere con la registrazione
      alert('Registrazione avvenuta con successo!');
      signupForm.submit(); // Invia il form
  });
});



// logica per controllare che l'email non sia già esistente all'interno del database




// logica controllo se l'utente è abilitato




// LOGIN

// controllo email



// controllo password




// cazzatine

// cambio da password a text per visualizzare la password

// se sbagli la password 3 volte la pagina esplode (preferenza di ela)