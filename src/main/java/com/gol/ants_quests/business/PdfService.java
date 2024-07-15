package com.gol.ants_quests.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.util.HeadFootPageEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
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

    public void generatePdfiText(Quest quest, HashMap<Long, Boolean> risposte, String dirPath,
            String fileName, String studente, String dataEsecuzione, String questName) {
        log.info("Start Generate PDF idQuest=" + quest.getIdQst() + ", Studente=" + studente);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            log.info("Creazione Documento");
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, out)
                    .setPageEvent(new HeadFootPageEvent(studente, dataEsecuzione, questName));
            document.open();
            log.info("Documento Aperto");

            PdfPTable intestazione = new PdfPTable(2);
            PdfPCell cell;

            Image image = Image.getInstance(new ClassPathResource("antlogo.png").getURL());
            image.scaleToFit(150, 100);
            cell = new PdfPCell(image);
            cell.setBorder(PdfPCell.NO_BORDER);
            intestazione.addCell(cell);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            cell = new PdfPCell(new Paragraph(quest.getTitolo(), titleFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(PdfPCell.NO_BORDER);
            intestazione.addCell(cell);

            document.add(intestazione);

            int numDom = 1;
            for (DomandaQuest domanda : quest.getDomanda()) {
                buildDomandaParagraph(document, domanda, risposte);
                if (numDom % 5 == 0) {
                    document.newPage();
                    document.add(intestazione);
                }
                numDom++;
            }

            document.close();
            log.info("Documento chiuso");
        } catch (Exception e) {
            log.error("Creazione PDF fallita", e);
        }

        savePdfToFile(out.toByteArray(), dirPath, fileName);
        log.info("End Generate PDF idQuest=" + quest.getIdQst() + ", Studente=" + studente);
    }

    private void buildDomandaParagraph(Document document, DomandaQuest domanda,
            HashMap<Long, Boolean> risposte) {
        log.info("Start Creazione Blocco Domanda=" + domanda.getIdQstDet());

        try {

            PdfPTable bloccoDomanda = new PdfPTable(1); // Una colonna
            bloccoDomanda.setWidthPercentage(90);
            bloccoDomanda.setSpacingBefore(30);

            // Aggiungi la domanda
            Font questionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph questionParagraph = new Paragraph(domanda.getDomanda(), questionFont);
            questionParagraph.setAlignment(Element.ALIGN_CENTER);
            PdfPCell headCell = new PdfPCell(questionParagraph);
            headCell.setPadding(10);
            headCell.setBorderColorBottom(new BaseColor(0, 0, 0));

            bloccoDomanda.addCell(headCell);

            // Aggiungi le risposte

            PdfPCell rispostaCell;
            Font answerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            for (RispostaQuest risposta : domanda.getRisp()) {
                log.info("Start Creazione Risposta=" + risposta.getIdAns());

                rispostaCell = new PdfPCell(new Paragraph(risposta.getRisposta(), answerFont));
                rispostaCell.setPadding(5);
                rispostaCell.setPaddingLeft(20);
                rispostaCell.setBorder(PdfPCell.NO_BORDER);

                if (risposte.containsKey(risposta.getIdAns())) {
                    if (risposte.get(risposta.getIdAns())) {
                        // Risposta Corretta Studente
                        rispostaCell.setBackgroundColor(new BaseColor(144, 238, 144));
                        log.info("Studente: Risposta Corretta");
                    } else {
                        // Risposta Errata Studente
                        rispostaCell.setBackgroundColor(new BaseColor(255, 182, 193));
                        log.info("Studente: Risposta Errata");
                    }
                } else if (risposta.getCorretta()) {
                    // Risposta Corretta della Domanda
                    rispostaCell.setBackgroundColor(new BaseColor(255, 255, 102));
                    log.info("Domanda: Risposta Corretta");
                }

                bloccoDomanda.addCell(rispostaCell);
                log.info("End Creazione Risposta=" + risposta.getIdAns());
            }

            document.add(bloccoDomanda);
        } catch (DocumentException e) {
            log.error("Build Blocco Domanda fallito, idDomanda=" + domanda.getIdQstDet(), e);
        }

        log.info("End Creazione Blocco Domanda=" + domanda.getIdQstDet());

    }

    private void savePdfToFile(byte[] pdfBytes, String dirPath, String filePath) {
        log.info("Start Salvataggio PDF studenteDir= " + dirPath + filePath);

        String completePath = mainDirQuests + dirPath;
        File dir = new File(completePath);
        if (!dir.exists()) {
            log.info("Cartella Studente NON presente, creazione");
            dir.mkdirs();
        }

        File file = Paths.get(completePath + filePath).toFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        } catch (IOException e) {
            log.error("Salvataggio PDF fallito", e);
        }
        log.info("End Salvataggio PDF studenteDir= " + dirPath + filePath);
    }
}
