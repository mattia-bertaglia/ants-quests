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
document.getElementById('registrationForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Impedisce il submit del form

  var nome = document.getElementById('nome').value;
  var cognome = document.getElementById('cognome').value;
  var email = document.getElementById('email').value;
  var password = document.getElementById('password').value;
  var confirmPassword = document.getElementById('confirmPassword').value;
  var errorMessage = document.getElementById('errorMessage');

  // Reset del messaggio di errore
  errorMessage.textContent = '';

  // Controllo se tutti i campi sono completati
  if (!nome || !cognome || !email || !password || !confirmPassword) {
      errorMessage.textContent = 'Devi completare tutti i campi per registrarti.';
      return;
  }

  // Controllo validità email
  var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailPattern.test(email)) {
      errorMessage.textContent = 'Inserisci un indirizzo email valido.';
      return;
  }

  // Controllo complessità password
  var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;
  if (!passwordPattern.test(password)) {
      errorMessage.textContent = 'La password deve contenere almeno un carattere minuscolo, un carattere maiuscolo, un carattere numerico e deve essere lunga almeno 6 caratteri.';
      return;
  }

  // Controllo corrispondenza password
  if (password !== confirmPassword) {
      errorMessage.textContent = 'Le password non corrispondono.';
      return;
  }

  // Se tutti i controlli sono superati, procedere con la registrazione
  alert('Registrazione avvenuta con successo!');
  // A questo punto puoi inviare i dati al server per completare la registrazione
});


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