package com.gol.ants_quests.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PdfService {

    @Value("${template-pdf}")
    private String templatePDF;

    @Value("${main-dir-quests}")
    private String mainDirQuests;

    /*
     * public byte[] generatePdfFromHtml(Quest quest, String studDir, String
     * fileName) throws IOException {
     * // htmlPath = htmlPath.replace("%2F", "/");
     * File htmlFile = new File(templatePDF);
     * 
     * // TODO: come mettiamo nel template i dati del questionario appena svolto ??
     * // parametro quest per prendere le domande del questionario svolto.
     * 
     * Document document = getDocument(htmlFile);
     * String xhtmlContent = convertToXhtml(document);
     * try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
     * ITextRenderer renderer = new ITextRenderer();
     * String baseUrl = FileSystems.getDefault()
     * .getPath("src/main/resources/")
     * .toUri().toURL().toString();
     * renderer.setDocumentFromString(xhtmlContent, baseUrl);
     * renderer.layout();
     * renderer.createPDF(outputStream, true);
     * 
     * savePdfToFile(outputStream.toByteArray(), mainDirQuests + "/" + studDir,
     * fileName);
     * 
     * return outputStream.toByteArray();
     * } catch (Exception e) {
     * e.printStackTrace();
     * throw new IOException("Error generating PDF from HTML", e);
     * }
     * }
     * 
     * public Document getDocument(File htmlFile) throws IOException {
     * return Jsoup.parse(htmlFile, "UTF-8");
     * }
     * 
     * 
     * private String convertToXhtml(Document document) {
     * document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
     * return document.html();
     * }
     */

    public ByteArrayInputStream generatePdfiText(Quest quest, HashMap<String, String> risposte, String dirPath,
            String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph(quest.getTitolo(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            for (DomandaQuest domanda : quest.getDomanda()) {
                for (Entry<String, String> risposta : risposte.entrySet()) {
                    if (risposta.getKey().endsWith(String.valueOf(domanda.getIdQstDet()))) {
                        buildDomandaParagraph(document, domanda, risposta.getValue().split("-")[1]);
                    }
                }
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        savePdfToFile(out.toByteArray(), dirPath, fileName);

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void buildDomandaParagraph(com.itextpdf.text.Document document, DomandaQuest domanda, String idRisposta) {

        try {
            // Aggiungi la domanda
            Font questionFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph questionParagraph = new Paragraph(domanda.getDomanda(), questionFont);
            questionParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(questionParagraph);

            // Aggiungi le opzioni
            Font optionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            PdfPTable table = new PdfPTable(1); // Una colonna
            table.setWidthPercentage(100);

            for (RispostaQuest risposta : domanda.getRisp()) {
                PdfPCell cell = new PdfPCell(new Paragraph(risposta.getRisposta(), optionFont));
                cell.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell);
            }

            document.add(table);
        } catch (DocumentException e) {
            log.error("Build Paragrafo domanda pdf", e);
        }

    }

    private void savePdfToFile(byte[] pdfBytes, String dirPath, String filePath) {

        String completePath = mainDirQuests + dirPath;
        File dir = new File(completePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = Paths.get(completePath + filePath).toFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
