document.addEventListener('DOMContentLoaded', function () {
    const passInput = document.getElementById('pass');
    const checkPassInput = document.getElementById('checkpass');
    const submitBtn = document.getElementById('submit-btn');
    const errorPass = document.getElementById('error-pass');
    const correctPass = document.getElementById('correct-pass');
    const errorCheckPass = document.getElementById('error-checkpass');
    const correctCheckPass = document.getElementById('correct-checkpass');
    const capInput = document.getElementById('cap');
    const errorCap = document.getElementById('error-cap');
    const phoneInput = document.getElementById('phone');
    const errorPhone = document.getElementById('error-phone');
    const dataNascitaInput = document.getElementById('dataNascita');
    const errorDataNascita = document.getElementById('error-dataNascita');

    function checkPasswords() {
        const passValue = passInput.value;
        const checkPassValue = checkPassInput.value;

        const passValid = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/.test(passValue);
        if (passValid) {
            errorPass.classList.add('hidden');
            correctPass.classList.remove('hidden');
        } else {
            errorPass.classList.remove('hidden');
            correctPass.classList.add('hidden');
        }

        if (passValue === checkPassValue && passValue !== '') {
            errorCheckPass.classList.add('hidden');
            correctCheckPass.classList.remove('hidden');
        } else {
            errorCheckPass.classList.remove('hidden');
            correctCheckPass.classList.add('hidden');
        }

        checkFormValidity();
    }

    function checkCap() {
        const capValue = capInput.value;
        const capValid = /^\d{5}$/.test(capValue);

        if (capValid) {
            errorCap.classList.add('hidden');
        } else {
            errorCap.classList.remove('hidden');
        }

        checkFormValidity();
    }

    function checkPhone() {
        const phoneValue = phoneInput.value;
        const phoneValid = /^\d{10}$/.test(phoneValue);

        if (phoneValid) {
            errorPhone.classList.add('hidden');
        } else {
            errorPhone.classList.remove('hidden');
        }

        checkFormValidity();
    }

    function checkDataNascita() {
        const dataNascitaValue = new Date(dataNascitaInput.value);
        const today = new Date();
        const dataNascitaValid = dataNascitaValue < today;

        if (dataNascitaValid) {
            errorDataNascita.classList.add('hidden');
        } else {
            errorDataNascita.classList.remove('hidden');
        }

        checkFormValidity();
    }

    function checkFormValidity() {
        const isFormValid = passInput.value === checkPassInput.value &&
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/.test(passInput.value) &&
            /^\d{5}$/.test(capInput.value) &&
            /^\d{10}$/.test(phoneInput.value) &&
            dataNascitaInput.value !== '' &&
            new Date(dataNascitaInput.value) < new Date();

        submitBtn.disabled = !isFormValid;
    }

    passInput.addEventListener('input', checkPasswords);
    checkPassInput.addEventListener('input', checkPasswords);
    capInput.addEventListener('input', checkCap);
    phoneInput.addEventListener('input', checkPhone);
    dataNascitaInput.addEventListener('input', checkDataNascita);
});