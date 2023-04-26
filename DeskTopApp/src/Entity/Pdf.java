/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import services.ContratsService;

/**
 *
 * @author yasmine
 */
public class Pdf {
    public void GeneratePdf(String filename, Contrats f, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        ContratsService sf = new ContratsService();
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
        document.add(new Paragraph("            Offre :"+f.getNom_offre()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("                      "));
        document.add(new Paragraph("CIN :" + f.getCin()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Date Creation:" + f.getDate()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Description :" + f.getDescription()));
        document.add(new Paragraph("                      "));

       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("           * MERCI DE SIGNER CE CONTRAT A LA MUNICIPAL             "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}
