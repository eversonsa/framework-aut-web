#language: pt
#encoding: utf-8
#@run1
Funcionalidade: Carrinho

  Contexto:
    Dado que acesso o ecommerce

  @run1
  Cenario: Carrinho vazio
    Quando acesso o carrinho
    Entao verifico o carrinho vazio
