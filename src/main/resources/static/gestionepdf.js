$(document).ready(function () {
    $('#generatePdfButton').click(function () {
        var percorsofile = "src/main/resources/templates/ciao.html";

        $.ajax({
            url: '/generate-pdf',
            type: 'POST',
            data: {
                percorsofile: percorsofile
            },
            xhrFields: {
                responseType: 'blob'
            },
            success: function (data) {
                var blob = new Blob([data], { type: 'application/pdf' });
                var url = window.URL.createObjectURL(blob);

                // visualizzazione del pdf
                $('#pdfFrame').attr('src', url);
                $('#pdfModal').modal('show');
            },
            error: function (xhr, status, error) {
                console.error('Error generating PDF:', error);
            }
        });
    });
});
