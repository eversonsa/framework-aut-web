package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.BasePage;

public class AmazonElements extends BasePage{
    @FindBy(css = "input[name='field-keywords']")
    public static WebElement campoPesquisarItem;
}
