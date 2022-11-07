package report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import cucumber.api.Scenario;
import model.Evidence;
import model.Platform;
import pageReport.PageReportFrontHeader;
import pageReport.PageReportTestContent;

public class PdfGenerator {

    public Document document;
    
    public PageReportFrontHeader pageReportFrontHeader;
    public PageReportTestContent pageReportTestContent;
    
    public PdfGenerator()
    {
        this.pageReportFrontHeader = new PageReportFrontHeader();
        this.pageReportTestContent = new PageReportTestContent();
    }
    
    public void generate(String filename, LinkedHashSet<Evidence> evidenceList, Platform platform, Scenario scenario)
            throws DocumentException, MalformedURLException, IOException {
        
        document = createDocument(filename, platform);
        
        this.pageReportFrontHeader.createReportHeader(document, scenario);
        this.pageReportTestContent.createPages(evidenceList, scenario, document);
        
       document.close();
      

    }

    private Document createDocument(String filename, Platform platform) {
        document = new Document(platform.getScreenshotSize());
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return document;
    }
    
}
