package com.gol.ants_quests.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.ExceptionConverter;

public class HeadFootPageEvent extends PdfPageEventHelper {

    private PdfTemplate total;

    private final String studente;
    private final String dataEsecuzione;
    private final String questName;

    public HeadFootPageEvent(String studente, String dataEsecuzione, String questName) {
        this.studente = "Studente: " + studente;
        this.dataEsecuzione = "Data: " + dataEsecuzione;
        this.questName = "Test: " + questName;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        final Font headerFooterFont = new Font();
        headerFooterFont.setColor(169, 169, 169);

        try {
            PdfPTable header = new PdfPTable(2);
            header.setWidths(new int[] { 5, 2 });
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(20);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);

            PdfPCell leftCell = new PdfPCell();
            leftCell.setBorder(Rectangle.BOTTOM);
            leftCell.setPhrase(new Phrase(this.studente, headerFooterFont));
            leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            header.addCell(leftCell);

            PdfPCell rightCell = new PdfPCell();
            rightCell.setBorder(Rectangle.BOTTOM);
            rightCell.setPhrase(new Phrase(this.dataEsecuzione, headerFooterFont));
            rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            header.addCell(rightCell);

            header.writeSelectedRows(0, -1, 34, 820, writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }

        PdfPTable footer = new PdfPTable(3);
        try {
            footer.setWidths(new int[] { 4, 3, 4 });
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(20);
            footer.getDefaultCell().setBorder(Rectangle.TOP);

            PdfPCell leftCell = new PdfPCell();
            leftCell.setBorder(Rectangle.TOP);
            leftCell.setPhrase(new Phrase("Ant srl", headerFooterFont));
            leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            footer.addCell(leftCell);

            PdfPCell centerCell = new PdfPCell();
            centerCell.setBorder(Rectangle.TOP);
            centerCell.setPhrase(new Phrase(this.questName, headerFooterFont));
            centerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            footer.addCell(centerCell);

            PdfPCell rightCell = new PdfPCell();
            rightCell.setBorder(Rectangle.TOP);
            rightCell.setPhrase(new Phrase(String.format("%d", writer.getPageNumber()), headerFooterFont));
            rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(rightCell);

            footer.writeSelectedRows(0, -1, 34, 50, writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }
}
