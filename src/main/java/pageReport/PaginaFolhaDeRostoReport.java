package pageReport;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import cucumber.api.Scenario;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes.FaasDocumentOperationValues;
import utils.PdfGetDataUtils;

public class PaginaFolhaDeRostoReport {

   String imageFileLogo = "bradesco-seguros-logo.png";
   Document document;
   
    public void criarCapaRelatorio(Document document, Scenario scenario)
            throws MalformedURLException, IOException, BadElementException, DocumentException {
        
        document.newPage();
        this.document = document;
        String imageFile = imageFileLogo;

        addImagemRelatorioCapa(imageFile);
        
        tableRelatorioCapa(scenario);

    }

    private void addImagemRelatorioCapa(String imageFile)
            throws BadElementException, MalformedURLException, IOException, DocumentException {
        
        Image image = Image.getInstance(imageFile);
        
        float imageWidth = 300;
        float imageHeight = 120;

        image.scaleAbsolute(imageWidth, imageHeight);
        image.setAlignment(Image.ALIGN_CENTER);

        document.add(image);
        
    }

    private void tableRelatorioCapa( Scenario scenario) throws DocumentException {

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
        
        table.addCell("0.23s");

        PdfPCell c4 = new PdfPCell(new Phrase("Satus:"));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        c4.setPadding(5);
        table.addCell(c4);
        table.addCell(scenario.getStatus().toString());
        
 
        document.add(new Paragraph("\n"));
        document.add(table);
    }
}