package report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


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
import com.itextpdf.text.pdf.PdfWriter;

import cucumber.api.Scenario;
import model.Evidence;
import model.Platform;
import pageReport.PaginaFolhaDeRostoReport;
import pageReport.PaginaPrintDoTestReport;
import utils.PdfGetDataUtils;

public class PdfGenerator {

    public Document document;
    
    public PaginaFolhaDeRostoReport paginaFolhaRostoReport;
    public PaginaPrintDoTestReport paginaPrintTesteReport;
    
    public PdfGenerator()
    {
        this.paginaFolhaRostoReport = new PaginaFolhaDeRostoReport();
        this.paginaPrintTesteReport = new PaginaPrintDoTestReport();
    }
    
    public void generate(String filename, LinkedHashSet<Evidence> evidenceList, Platform platform, Scenario scenario)
            throws DocumentException, MalformedURLException, IOException {
        
        document = createDocument(filename, platform);
        
        this.paginaFolhaRostoReport.criarCapaRelatorio(document, scenario);
        this.paginaPrintTesteReport.createPages(evidenceList, scenario, document);
        
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
