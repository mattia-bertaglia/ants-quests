
$(document).ready(function () {

    $("#btn-mod-profilo").on("click", function () {
        $("#submit-btn").removeClass("d-none").removeAttr("disabled");
        $("#btn-close-mod").removeClass("d-none");
        $("#btn-mod-profilo").addClass("d-none");
        $("textarea, input.form-control-plaintext").each(function () {
            $(this).prop("readonly", false);
            $(this).removeClass("form-control-plaintext").addClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").removeClass("d-none");
    });


    $("#btn-close-mod").on("click", function () {
        $("#submit-btn").addClass("d-none").attr("disabled", "disabled");
        $("#btn-close-mod").addClass("d-none");
        $("#btn-mod-profilo").removeClass("d-none");
        $("textarea, input.form-control").each(function () {
            $(this).prop("readonly", true);
            $(this).addClass("form-control-plaintext").removeClass("form-control");
        });
        $("#new-pass-group, #confirm-pass-group").addClass("d-none");
    });


    $("#newpass").on("focus", function () {
        if (!$(this).is("[readonly]")) {
            $("#error-newpass, #correct-newpass").show();
        }
    }).on("blur", function () {
        $("#error-newpass, #correct-newpass").hide();
    });


    $("#newpass, #checkpass").on("keyup", function () {
        var newPass = $("#newpass").val();
        var checkPass = $("#checkpass").val();
        if (checkPass && newPass !== checkPass) {
            $("#error-checkpass").show();
            $("#correct-checkpass").hide();
        } else if (checkPass && newPass === checkPass) {
            $("#error-checkpass").hide();
            $("#correct-checkpass").show();
        } else {
            $("#error-checkpass, #correct-checkpass").hide();
        }
    });
});
