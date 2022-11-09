package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import elements.AcessoVitrineElements;

public class AcessoVitrinePage extends AcessoVitrineElements {

    public AcessoVitrinePage() {
        PageFactory.initElements(driver, this);
    }

    public void clicarBtnConhecer() throws InterruptedException {
    Thread.sleep(8000);
    WebElement element = btnConhecer;
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);

    }

    public String getTextoSucesso() throws InterruptedException {
        Thread.sleep(5000);
        return textoSucesso.getText();
    }
}
