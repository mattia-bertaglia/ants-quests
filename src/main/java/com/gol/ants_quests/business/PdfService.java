package com.gol.ants_quests.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.gol.ants_quests.hibernate.entities.Quest;

@Service
public class PdfService {

    @Value("${template-pdf}")
    private String templatePDF;

    @Value("${main-dir-quests}")
    private String mainDirQuests;

    public byte[] generatePdfFromHtml(Quest quest, String studDir, String fileName) throws IOException {
        /* htmlPath = htmlPath.replace("%2F", "/"); */
        File htmlFile = new File(templatePDF);

        // TODO: come mettiamo nel template i dati del questionario appena svolto ??
        // parametro quest per prendere le domande del questionario svolto.

        Document document = getDocument(htmlFile);
        String xhtmlContent = convertToXhtml(document);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            String baseUrl = FileSystems.getDefault()
                    .getPath("src/main/resources/")
                    .toUri().toURL().toString();
            renderer.setDocumentFromString(xhtmlContent, baseUrl);
            renderer.layout();
            renderer.createPDF(outputStream, true);

            savePdfToFile(outputStream.toByteArray(), mainDirQuests + "/" + studDir, fileName);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error generating PDF from HTML", e);
        }
    }

    public Document getDocument(File htmlFile) throws IOException {
        return Jsoup.parse(htmlFile, "UTF-8");
    }

    private String convertToXhtml(Document document) {
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    private void savePdfToFile(byte[] pdfBytes, String dirPath, String filePath) {

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = Paths.get(dirPath + "/" + filePath).toFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
