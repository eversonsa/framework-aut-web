package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.BasePage;

public class AcessoVitrineElements extends BasePage{
    
    @FindBy(xpath = "//*[@id='sk-BradescoHomePage-Buttons-ProdutosBotoesHTMLJS']/div/div/a[1]")
    protected WebElement btnConhecer;
    
    @FindBy(xpath = "//*[@id='sk-HomePageGeneric-PackageMessage']/div/div[2]/div[2]/div[1]/div/a/div/div[1]/p")
    protected WebElement textoSucesso;
  
    @FindBy(xpath = "//*[@id=\"sk-AttendanceGeneric-AttendanceHTMLJS\"]/div/div/div[1]/h3")
    protected WebElement textoSucessoAlternativo;
}
