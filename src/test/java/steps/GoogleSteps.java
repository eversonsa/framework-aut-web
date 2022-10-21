package steps;

import cucumber.api.java.es.Dado;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;
import pages.GooglePage;

import static steps.BaseSteps.openBrowser;

public class GoogleSteps{

	@Dado("que acesso Google")
	public void que_acesso_Google() {
		String GoogleUrl = "https://www.google.com/";
		openBrowser(GoogleUrl);
	}

	@Quando("pesquiso o item {string}")
	public void pesquiso_o_item(String item) {
		GooglePage googleMainPage = new GooglePage();
		googleMainPage.pesquisarItem(item);
	}

	@Entao("verifico as imagens")
	public void verifico_as_imagens() {
		GooglePage googleMainPage = new GooglePage();
		googleMainPage.verificoImagens();
	}

}
