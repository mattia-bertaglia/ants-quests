package com.gol.ants_quests.controllers;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.gol.ants_quests.business.PdfService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    /* @PostMapping("/generate-pdf") */
    public void generatePdfUrl(@RequestBody String percorsofile, HttpServletResponse response) throws IOException {
        percorsofile = percorsofile.replace("percorsofile=", "");
        byte[] pdfBytes = pdfService.generatePdfFromHtml(null, percorsofile, ""); // cambiati i parametri

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=generated.pdf");
        response.setContentLength(pdfBytes.length);

        try (OutputStream os = response.getOutputStream()) {
            os.write(pdfBytes);
            os.flush();
        }
    }
}
