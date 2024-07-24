$(document).ready(function () {
    // Nascondi il messaggio di attenzione all'inizio
    $("#accept-message").addClass("hidden");

    // Mostra i campi modificabili e i pulsanti quando si clicca su "Modifica"
    $("#btn-mod-profilo").on("click", function () {
        $("#submit-btn").removeClass("hidden").removeAttr("disabled");
        $("#btn-close-mod").removeClass("hidden");
        $("#btn-mod-profilo").addClass("hidden");
        $("textarea, input.form-control-plaintext").each(function () {
            $(this).prop("readonly", false);
            $(this).removeClass("form-control-plaintext").addClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").removeClass("hidden");
        $("#message-box").addClass("hidden"); // Nasconde il messaggio N.B.
        $("#accept-message").removeClass("hidden"); // Mostra il messaggio di attenzione
    });

    // Nasconde i campi modificabili e i pulsanti quando si clicca su "Annulla"
    $("#btn-close-mod").on("click", function () {
        $("#submit-btn").addClass("hidden").attr("disabled", "disabled");
        $("#btn-close-mod").addClass("hidden");
        $("#btn-mod-profilo").removeClass("hidden");
        $("textarea, input.form-control").each(function () {
            $(this).prop("readonly", true);
            $(this).addClass("form-control-plaintext").removeClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").addClass("hidden");
        $("#message-box").removeClass("hidden"); // Mostra il messaggio N.B.
        $("#accept-message").addClass("hidden"); // Nasconde il messaggio di attenzione
    });

    // Controlla che la data di nascita non sia oggi o nel futuro
    $("#dataNascita").on("change", function () {
        var selectedDate = new Date($(this).val());
        var today = new Date();
        today.setHours(0, 0, 0, 0);

        if (selectedDate >= today) {
            $("#error-dataNascita").removeClass("hidden");
        } else {
            $("#error-dataNascita").addClass("hidden");
        }
        validateForm();
    });

    // Controlla se il telefono è valido (solo numeri e lunghezza di 10)
    $("#phone").on("input", function () {
        var phone = $(this).val();
        var phonePattern = /^\d{1,10}$/;

        if (!phonePattern.test(phone)) {
            $("#error-phone").removeClass("hidden");
        } else {
            $("#error-phone").addClass("hidden");
        }
        validateForm();
    });

    // Controlla se il CAP è valido (solo numeri e lunghezza di 5)
    $("#cap").on("input", function () {
        var cap = $(this).val();
        var capPattern = /^\d{1,5}$/;

        if (!capPattern.test(cap)) {
            $("#error-cap").removeClass("hidden");
        } else {
            $("#error-cap").addClass("hidden");
        }
        validateForm();
    });

    // Controlla se la provincia è valida (solo lettere e lunghezza di 2)
    $("#provincia").on("input", function () {
        var provincia = $(this).val();
        var provinciaPattern = /^[A-Za-z]{2}$/;

        if (!provinciaPattern.test(provincia)) {
            $("#error-provincia").removeClass("hidden");
        } else {
            $("#error-provincia").addClass("hidden");
        }
        validateForm();
    });

    // Mostra le istruzioni della password quando il campo è attivo
    $("#passkey").on("focus", function () {
        if (!$(this).is("[readonly]")) {
            $("#error-passkey, #correct-passkey").show();
        }
    }).on("blur", function () {
        $("#error-passkey, #correct-passkey").hide();
    });

    // Confronta le password e mostra il messaggio corrispondente
    $("#passkey, #checkpass").on("keyup", function () {
        var passkey = $("#passkey").val();
        var checkPass = $("#checkpass").val();
        if (checkPass && passkey !== checkPass) {
            $("#error-checkpass").show();
            $("#correct-checkpass").hide();
        } else if (checkPass && passkey === checkPass) {
            $("#error-checkpass").hide();
            $("#correct-checkpass").show();
        } else {
            $("#error-checkpass, #correct-checkpass").hide();
        }
        validateForm();
    });

    // Funzione di validazione generale
    function validateForm() {
        var isValid = true;

        // Controlla se ci sono errori di data, telefono, CAP, provincia e password
        if ($("#error-dataNascita").is(":visible") ||
            $("#error-phone").is(":visible") ||
            $("#error-cap").is(":visible") ||
            $("#error-provincia").is(":visible") ||
            $("#error-checkpass").is(":visible")) {
            isValid = false;
        }

        // Abilita o disabilita il pulsante di salvataggio
        $("#submit-btn").prop("disabled", !isValid);
    }
});
