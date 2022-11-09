package steps;

import org.junit.Assert;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import main.Variaveis;
import pages.AcessoVitrinePage;
public class AcessoVitrineSteps {
    
    @Dado("que estou na página principal")
    public void que_estou_na_página_principal() throws InterruptedException {
        String url = Variaveis.get().asString("url");
        BaseSteps.openBrowser(url);
        Thread.sleep(6000);
    }

    @Entao("clico no botão Conhecer")
    public void clico_no_botão_Conhecer() throws Exception {
        AcessoVitrinePage acessoVitrinePage = new AcessoVitrinePage();
        acessoVitrinePage.clicarBtnConhecer();
    }

    @Entao("é exibida a Vitrine do Dental")
    public void é_exibida_a_Vitrine_do_Dental() throws InterruptedException {
       AcessoVitrinePage acessoVitrinePage = new AcessoVitrinePage();
       Assert.assertEquals("Planos odontológicos", acessoVitrinePage.getTextoSucesso());
    }

}
