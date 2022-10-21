package pageReport;

import java.io.IOException;
import java.util.LinkedHashSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;

import cucumber.api.Scenario;
import model.Evidence;
import utils.PdfGetDataUtils;

public class PaginaPrintDoTestReport {
    
   Document document;

    public void createPages(LinkedHashSet<Evidence> evidenceList, Scenario scenario, Document document) {
        
        evidenceList.forEach(evidence -> {
            document.newPage();
            this.document = document;
            
            String text = evidence.getDescription();

            byte[] image = evidence.getImage();

            addText(text + "\n");
           
            if (image != null)
                addImage(image);

        });
     
    }
    
    private void addText(String text) {
        Phrase phrase = new Phrase();
        try {
            Font fontDadoStep = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk fontStep = new Chunk(text, fontDadoStep);
            phrase.add(fontStep);

            document.add(phrase);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
    private void addImage(byte[] imgByte) {
        try {
            Image img = Image.getInstance(imgByte);
            
            img.scaleAbsolute(handleImageSize());
            document.add(img);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    private Rectangle handleImageSize() {
        float documentWidth = document.getPageSize().getWidth();
        float documentHeight = document.getPageSize().getHeight();
        float decreasePercentage = 20f / 100f;
        float imageWidth = documentWidth - (decreasePercentage * documentWidth);
        float imageHeight = documentHeight - (decreasePercentage * documentHeight);
        return new Rectangle(imageWidth, imageHeight);
    }
}
