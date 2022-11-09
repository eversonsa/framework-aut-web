package steps;

import static steps.BaseSteps.openBrowser;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.AmazonPage;

public class PesquisaAmazonSteps {
    
    @Dado("que acesso Amazon")
    public void que_acesso_Amazon() {
        String amazonUrl = "https://www.amazon.com.br/";
        openBrowser(amazonUrl);
    }

    @Quando("pesquiso o livro {string}")
    public void pesquiso_o_livro(String livro) {
        AmazonPage amazonPage = new AmazonPage();
        amazonPage.pesquisarItem(livro);
    }

    @Entao("verifico os livros")
    public void verifico_os_livros() {
        AmazonPage amazonPage = new AmazonPage();
        amazonPage.verificoLivros();
    }
}
