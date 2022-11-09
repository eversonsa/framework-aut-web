#language: pt
#encoding: utf-8
#@livroAmazonCenario
Funcionalidade: Pesquisa na Amazon

Contexto:
   Dado que acesso Amazon
@livroAmazonCenario
Cenario: pesquiso o livro
   Quando pesquiso o livro "livro Selenium webdriver"
   Entao verifico os livros
