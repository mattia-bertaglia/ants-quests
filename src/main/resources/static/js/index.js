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
  var passwordInput = document.getElementById('pass');
  var confirmPasswordInput = document.getElementById('checkpass');

  var emailError = document.getElementById('error-mail');
  var emailCorrect = document.getElementById('correct-mail');
  var passwordError = document.getElementById('error-pass');
  var passwordCorrect = document.getElementById('correct-pass');
  var confirmPasswordError = document.getElementById('error-checkpass');
  var confirmPasswordCorrect = document.getElementById('correct-checkpass');

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

      var nome = document.querySelector('input[name="nome"]').value;
      var cognome = document.querySelector('input[name="cognome"]').value;
      var email = emailInput.value;
      var password = passwordInput.value;
      var confirmPassword = confirmPasswordInput.value;

      // Controllo se tutti i campi sono completati
      if (!nome || !cognome || !email || !password || !confirmPassword) {
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



// logica per controllare che l'email non sia già esistente all'interno del database




// logica controllo se l'utente è abilitato




// LOGIN

// controllo email



// controllo password




// cazzatine

// cambio da password a text per visualizzare la password

// se sbagli la password 3 volte la pagina esplode (preferenza di ela)