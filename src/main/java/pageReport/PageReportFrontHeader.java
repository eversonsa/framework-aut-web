package pageReport;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import cucumber.api.Scenario;
import utils.PdfGetDataUtils;

public class PageReportFrontHeader {

    String imageFileLogo = "src\\test\\resources\\images\\bradesco-seguros-logo.png";

    Document document;

    static double timesOfExecution;
    
    public static void setTimeExecution(Long time){
        convertTimeMillsToSeconds(time);
    }

    
    public void createReportHeader(Document document, Scenario scenario)
            throws MalformedURLException, IOException, BadElementException, DocumentException {

        document.newPage();
        this.document = document;
        String imageFile = imageFileLogo;

        spaceLine();
       
        addImageReportHeader(imageFile);

        tableReportHeader(scenario);

    }

    private void addImageReportHeader(String imageFile)
            throws BadElementException, MalformedURLException, IOException, DocumentException {

        Image image = Image.getInstance(imageFile);

        float imageWidth = 300;
        float imageHeight = 120;

        image.scaleAbsolute(imageWidth, imageHeight);
        image.setAlignment(Image.ALIGN_CENTER);

        document.add(image);

    }

    private  void tableReportHeader(Scenario scenario)
            throws DocumentException {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(40);

        PdfPCell c1 = new PdfPCell(new Phrase("Nome do cenário:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPadding(5);
        table.addCell(c1);

        table.addCell(scenario.getName());

        PdfPCell c2 = new PdfPCell(new Phrase("Data:"));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        c2.setPadding(5);
        table.addCell(c2);
        table.addCell(PdfGetDataUtils.getCurrentDate());

        PdfPCell c3 = new PdfPCell(new Phrase("Tempo de Execução:"));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        c3.setPadding(5);
        table.addCell(c3);
        table.addCell(timesOfExecution  + ".s");
        

        PdfPCell c4 = new PdfPCell(new Phrase("Satus:"));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        c4.setPadding(5);
        c4.setBackgroundColor(BaseColor.GREEN);
        table.addCell(c4);

        PdfPCell c5 = new PdfPCell(new Phrase(statusColorTable(scenario)));
        c5.setHorizontalAlignment(Element.ALIGN_LEFT);
        c5.setPadding(5);
        c5.setBackgroundColor(
                scenario.getStatus().toString() == "PASSED" ? BaseColor.GREEN : BaseColor.RED);

        table.addCell(c5);

        document.add(new Paragraph("\n"));
        document.add(table);
    }

    private String statusColorTable(Scenario scenario) {
        return scenario.getStatus().toString() == "PASSED" ? "Passou" : "Não Passou";
    }
    
    private static void convertTimeMillsToSeconds(Long time) {
        double time_converted = time / 1000.0;
        timesOfExecution = time_converted;
    }

    private void spaceLine() throws DocumentException {
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
    }


    public static String setTimeExecution(String currentDate) {
        // TODO Auto-generated method stub
        return currentDate;
    }


}