package com.gol.ants_quests.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CvController {

    @Value("${main-dir-quests}")
    private String mainDirQuests;

    @GetMapping(value = "/pdf/open", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<FileSystemResource> downloadCV(@RequestParam("pathFile") String pathFile) throws IOException {
        log.info("Invio per visualizzazione CV: " + pathFile);

        File file = new File(mainDirQuests + pathFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, file.getName());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new FileSystemResource(file));
    }
}