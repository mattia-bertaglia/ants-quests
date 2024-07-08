package com.gol.ants_quests.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class PdfService {

    public byte[] generatePdfFromHtml(String htmlPath) throws IOException {
        htmlPath = htmlPath.replace("%2F", "/");
        File htmlFile = new File(htmlPath);
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

            savePdfToFile(outputStream.toByteArray(), "./doc/ciao.pdf");

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

    private void savePdfToFile(byte[] pdfBytes, String filePath) {
        File file = Paths.get(filePath).toFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
