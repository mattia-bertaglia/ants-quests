package com.gol.ants_quests.controllers;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PdfController {

    @GetMapping(value = "/pdf/open", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<FileSystemResource> pdfOpen(@RequestParam("pathFile") String pathFile) {
        File file = new File("./doc/" + pathFile);
        /* File file = new File("./doc/3-Snoding/Logica_2024-07-11.pdf"); */

        HttpHeaders headers = new HttpHeaders();
        headers.add("attachment", file.getName());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new FileSystemResource(file));
    }

}
