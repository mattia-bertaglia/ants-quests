$(document).ready(function () {

    // Memorizza ora apertura pagina
    const startTime = Date.now();

    $('button[data-target]').on('click', function () {
        var targetIndex = $(this).data('target');
        $('#carouselExampleControls').carousel(targetIndex);
    });


    $('#carouselExampleControls').on('slide.bs.carousel', function (e) {
        var activeIndex = $(e.relatedTarget).prop('id');
        $('button[data-target]').removeClass('active');
        $('#btn-' + activeIndex).addClass('active');
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
        var elapsedTime = Date.now() - startTime;

        // Converti in secondi
        var elapsedSeconds = elapsedTime / 1000;

        $('#tempo-quest').val(elapsedSeconds);
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