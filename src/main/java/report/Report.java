package report;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import cucumber.api.Scenario;
import model.Evidence;
import model.Platform;
import pageReport.PageReportFrontHeader;

public class Report {
    private static LinkedHashSet<Evidence> evidenceList;
    private static WebDriver webDriver;
    private static Platform platformEnum;
    
    protected static long startReportTimeExecution;
    protected static long endReportTimeExecution;

    public static void init(WebDriver driver, Platform platform) {
        evidenceList = new LinkedHashSet<>();
        webDriver = driver;
        platformEnum = platform;
        startReportTimeExecution = System.currentTimeMillis();
    }

    public static void add(String description, boolean withScreenshot) throws IOException {
        byte[] image = null;
        if (withScreenshot)
            image = getByteImage();
        Evidence evidence = new Evidence(image, description);
        evidenceList.add(evidence);
    }

    public static void generatePDF(Scenario scenario) throws DocumentException {
        endReportTimeExecution = System.currentTimeMillis();
        long reportTotalTime = endReportTimeExecution - startReportTimeExecution;
        try {
            String filePath = null;
            filePath = getFilePath(scenario);
            PageReportFrontHeader.setTimeExecution(reportTotalTime);
            new PdfGenerator().generate(filePath, evidenceList, platformEnum, scenario);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    private static String getFilePath(Scenario scenario) throws IOException {
        String scenarioStatus = scenario.getStatus().name();
        String scenarioName = scenario.getName();
        String filename;
       
            filename = "[" + scenarioStatus.toUpperCase() + "] " +
            new SimpleDateFormat("- dd-MM-yyyy").format(new Date()) + " - " + scenarioName + ".pdf";
       

        Path resourceDirectory = Paths.get("src","test", "resources","test-results");
        String path = resourceDirectory.toFile().getAbsolutePath();
        Files.createDirectories(Paths.get(path));

        return path + File.separator + filename;
    }

    private static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
 

    private static byte[] getByteImage() {
        byte[] image = null;
        try {
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            image = FileUtils.readFileToByteArray(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
