$(document).ready(function () {
    $("#accept-message").addClass("hidden");

    $("#btn-mod-profilo").on("click", function () {
        $("#submit-btn").removeClass("hidden").removeAttr("disabled");
        $("#btn-close-mod").removeClass("hidden");
        $("#btn-mod-profilo").addClass("hidden");
        $("textarea, input.form-control-plaintext").each(function () {
            $(this).prop("readonly", false);
            $(this).removeClass("form-control-plaintext").addClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").removeClass("hidden");
        $("#message-box").addClass("hidden");
        $("#accept-message").removeClass("hidden");
    });

    $("#btn-close-mod").on("click", function () {
        $("#submit-btn").addClass("hidden").attr("disabled", "disabled");
        $("#btn-close-mod").addClass("hidden");
        $("#btn-mod-profilo").removeClass("hidden");
        $("textarea, input.form-control").each(function () {
            $(this).prop("readonly", true);
            $(this).addClass("form-control-plaintext").removeClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").addClass("hidden");
        $("#message-box").removeClass("hidden");
        $("#accept-message").addClass("hidden");
    });

    $("#passkey").on("focus", function () {
        if (!$(this).is("[readonly]")) {
            $("#error-passkey, #correct-passkey").show();
        }
    }).on("blur", function () {
        $("#error-passkey, #correct-passkey").hide();
    });

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

    function validateForm() {
        var isValid = true;

        // Validazione Password
        var passkey = $("#passkey").val();
        var passkeyPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
        if (passkey && !passkeyPattern.test(passkey)) {
            $("#error-passkey").show();
            isValid = false;
        } else {
            $("#error-passkey").hide();
        }

        // Validazione Conferma Password
        var checkPass = $("#checkpass").val();
        if (checkPass && passkey !== checkPass) {
            $("#error-checkpass").show();
            isValid = false;
        } else {
            $("#error-checkpass").hide();
        }

        // Validazione Data di Nascita
        var dataNascita = $("#dataNascita").val();
        var today = new Date();
        var selectedDate = new Date(dataNascita);
        if (dataNascita && (selectedDate >= today)) {
            $("#error-dataNascita").show();
            isValid = false;
        } else {
            $("#error-dataNascita").hide();
        }

        // Validazione Telefono
        var telefono = $("#phone").val();
        var telefonoPattern = /^\d{10}$/;
        if (telefono && !telefonoPattern.test(telefono)) {
            $("#error-phone").show();
            isValid = false;
        } else {
            $("#error-phone").hide();
        }

        // Validazione CAP
        var cap = $("#cap").val();
        var capPattern = /^\d{5}$/;
        if (cap && !capPattern.test(cap)) {
            $("#error-cap").show();
            isValid = false;
        } else {
            $("#error-cap").hide();
        }

        // Validazione Provincia
        var provincia = $("#provincia").val();
        var provinciaPattern = /^[A-Za-z]{2}$/;
        if (provincia && !provinciaPattern.test(provincia)) {
            $("#error-provincia").show();
            isValid = false;
        } else {
            $("#error-provincia").hide();
        }

        if (!isValid) {
            $("#general-error-message").removeClass("hidden");
            $('html, body').animate({ scrollTop: 0 }, 'slow');
        } else {
            $("#general-error-message").addClass("hidden");
        }

        $("#submit-btn").prop("disabled", !isValid);
    }

    // Validazione alla submit
    $("#profilo-form").on("submit", function (e) {
        validateForm();
        if ($("#submit-btn").is(":disabled")) {
            e.preventDefault(); // Non inviare il modulo se non è valido
        }
    });
});
