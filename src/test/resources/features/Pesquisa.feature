#language: pt
#encoding: utf-8

Funcionalidade: Pesquisa

  Contexto:
    Dado que acesso o ecommerce

  @run1
  Cenario: Pesquisar produto
    Quando pesquiso o produto "t-shirt"
    Entao a pesquisa deve retornar resultados

  @run1
  Esquema do Cenario: Pesquisar produtos
    Quando pesquiso o produto <nome_produto>
    Entao a pesquisa deve retornar resultados
    Exemplos:
      | nome_produto |
      | "t-shirt"    |
      | "short"      |
      | "shoes"      |