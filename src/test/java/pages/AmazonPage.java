package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import elements.AmazonElements;

public class AmazonPage extends AmazonElements{
    
    public AmazonPage(){
        PageFactory.initElements(driver, this);
    }

    public void pesquisarItem(String item){
        campoPesquisarItem.sendKeys(item);
        campoPesquisarItem.sendKeys(Keys.ENTER);
    }

    public void verificoLivros(){
        System.out.println("Imagens de livross ...");
    }
}
