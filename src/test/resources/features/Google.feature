#language: pt
#encoding: utf-8
#@run
Funcionalidade: Google

Contexto:
   Dado que acesso Google
@run
Cenario: pesquiso o item
   Quando pesquiso o item "Gatinhos"
   Entao verifico as imagens