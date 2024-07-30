/* selezione nav Studente */
document.addEventListener('DOMContentLoaded', function () {

    const currentUrl = window.location.pathname;


    const urlToLinkIdMap = {
        '/homeStud/': 'home-studenti-link',
        '/homeStud/profilo': 'profilo-studenti-link',


    };

    const activeLinkId = urlToLinkIdMap[currentUrl];
    if (activeLinkId) {
        const activeLink = document.getElementById(activeLinkId);
        if (activeLink) {
            activeLink.classList.add('active');
        }
    }
});

/* homestud.html */
document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.view-pdf-btn').forEach(button => {
        button.addEventListener('click', function () {
            var pdfUrl = this.getAttribute('data-pdf-url');
            var iframe = document.getElementById('pdfIframe');
            iframe.src = pdfUrl;

            $('#pdfModal').modal('show');
        });
    });

});

/* profiloStud.html */
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

/* doQuest.html */
$(document).ready(function () {

    // Memorizza ora apertura pagina
    const startTime = Date.now();

    let seconds = 0;
    let minutes = 0;
    const chronometerDisplay = $("#chronometer");

    function updateChronometer() {
        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minutes++;
        }

        chronometerDisplay.text(
            (minutes < 10 ? "0" + minutes : minutes) + ":" +
            (seconds < 10 ? "0" + seconds : seconds)
        );
    }

    let interval = setInterval(updateChronometer, 1000);

    $('button[data-target]').on('click', function () {
        var targetIndex = $(this).data('target');
        $('#carouselExampleControls').carousel(targetIndex);
    });


    $('#carouselExampleControls').on('slide.bs.carousel', function (e) {
        var activeIndex = $(e.relatedTarget).prop('id');
        $('button[data-target]').removeClass('active');
        $('#btn-' + activeIndex).addClass('active');
    });

    $("#concludi-test").on("click", function () {




    });

    $('#testForm').on('submit', function (event) {

        let countDom = $(this).data('count-dom') + 2;
        const formValues = $(this).serializeArray();

        if (formValues.length < countDom) {
            bootbox.alert({
                title: 'ATTENZIONE !!',
                message: 'Non hai risposto a tutte le domande !!',
                centerVertical: true,
                className: 'tada animated',
                buttons: {
                    ok: {
                        className: 'btn-success'
                    }
                }
            });

            $('.legenda-domande').children().each(function () {
                let idDom = $(this).prop('id');
                let trovato = false;
                for (const risposta of formValues) {
                    if (risposta.name == idDom.substring(4)) {
                        trovato = true;
                    }
                }

                if (!trovato) {
                    $('#' + idDom).removeClass('btn-outline-primary');
                    $('#' + idDom).addClass('btn-danger');
                }
            });
            return false;
        }

        // Calcola il tempo trascorso in millisecondi
        clearInterval(interval);
        var elapsedTime = Date.now() - startTime;
        $('#tempo-quest').val(elapsedTime);

        bootbox.dialog({
            title: '<strong class="text-center">Congratulazioni !!</strong>',
            message: '<img src="/img/loading.gif" alt="Loading..." />' +
                '<br><h4><strong>Test Concluso, attendi per i risultati.</strong></h4>',
            centerVertical: true,
            className: 'tada animated text-center',
        });


    });

    $('#testForm').on('reset', function () {
        $('.legenda-domande').children().each(function () {
            $(this).removeClass('btn-success');
            $(this).removeClass('btn-danger');
            $(this).addClass('btn-outline-primary');
        });
        $('.form-check').each(function () {
            $(this).removeClass('active');
        })
    });

    $('.legenda-domande').children().first().addClass('active');

    $('.form-check').on('click', function () {
        $(this).find('.form-radio-input').prop('checked', true);
        $(this).find('.form-radio-input').trigger('change');
    });

    $('.form-radio-input').on('change', function () {

        const list = $(this).parents('ul');
        list.children().each(function () {
            const item = $(this).children();
            item.removeClass('active');
        });

        $(this).parent().addClass('active');

        $('#btn-' + $(this).prop('name')).removeClass('btn-outline-primary');
        $('#btn-' + $(this).prop('name')).removeClass('btn-danger');
        $('#btn-' + $(this).prop('name')).addClass('btn-success');

    });





});