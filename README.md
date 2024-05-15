<!DOCTYPE html>
<html

<body>
  <h1 align="center">API - E-commerce Grupo 5</h1>
  <div align="center">
    <img src="https://github.com/BernardoChrist/api_ecommerce/assets/141193350/0a910d75-a304-4838-ba58-8ae00970408e"alt="E-commerce Grupo 5">
  </div>
</br>
  <p> Bem-vindo ao repositório do projeto de conclusão da disciplina "API RESTful" na Residência de Software. Este projeto consiste na criação de uma API RESTful para um sistema de E-commerce com base nas regras de negócio e requisitos especificados neste documento.</p>

  <h2>Descrição</h2>
O objetivo deste projeto é desenvolver uma API RESTful para um E-commerce, seguindo as regras de negócio e requisitos fornecidos. A API permitirá a gestão de produtos, pedidos e clientes, além de implementar funcionalidades adicionais, como autenticação, controle de acesso, e envio de e-mails com relatórios de pedido.

  <h2>Requisitos</h2>
  <h3>Banco de Dados</h3>
  <li>O banco de dados e as tabelas devem ser criados de acordo com o DER (Diagrama de Entidade-Relacionamento) fornecido.</li>
  <li>Não é permitido alterar a estrutura ou relacionamento entre as entidades no DER.</li>

  <h3>Funcionalidades da API</h3>
  <li>Todos os endpoints disponibilizados pela API funcionam corretamente e realizam as operações para as quais foram desenvolvidas.</li>
  <li>Foi implementado um conjunto de métodos CRUD para todos os recursos da API.</li>
  <li>Foi implementado um Relatório de Pedido, contendo informações como id do pedido, data do pedido, valor total, e a lista de itens do pedido com detalhes.</li>
  <li>Todas as exceções de "item não encontrado" foram identificadas e tratadas, exibindo mensagens personalizadas</li>

  <h3>E-mails</h3>
  <li>Um e-mail contendo o Relatório de Pedido deve ser enviado após o cadastro de um novo pedido.</li>

  <h3>Autenticação e Controle de Acesso</h3>
  <li>A API tem uma implementação de autenticação e controle de acesso usando o módulo de segurança do Spring + JWT.</li>

  <h2>Regras de Negócio da API</h2>
  
  <h3>Cálculos de Pedido</h3>
  <li>Ao cadastrar um novo pedido, os valores bruto e líquido dos produtos são calculados: valor bruto (preço de venda * quantidade) e valor líquido (valor bruto - valor do desconto, calculado a partir do percentual de desconto sobre o valor bruto).</li>
  <li>O valor total do pedido é calculado e armazenado como a soma dos valores líquidos dos itens do pedido.</li>

  <h3>Consulta de Endereço do Cliente</h3>
  <li>Os dados do endereço do cliente são obtidos a partir de uma API externa de consulta de CEP.</li>

  <h2>Tecnologias Utilizadas</h2>
 <img src="https://github.com/eduardocs90/POO2023_Grupo6/assets/141193350/da426343-ad69-4070-af0e-b81ac4303722" height="40" width="40"> <a href="https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html">Java 17</a> 

 <img src="https://github.com/eduardocs90/POO2023_Grupo6/assets/141193350/0ae95b40-0108-4e12-9b64-b1c8d75ce611" height="30" width="30"> <a href="https://spring.io/tools">Spring Tools Suite 4</a> 
 
 <img src="https://github.com/BernardoChrist/api_ecommerce/assets/141193350/95c4a61e-d63c-45db-9780-ed28bc64b5ff" height="40" width="40"> <a href="https://start.spring.io">Spring Boot</a> 

<img src="https://github.com/BernardoChrist/api_ecommerce/assets/141193350/1796ae01-ef02-4c15-8408-d1aa55e43736" height="40" width="40"> <a href="https://maven.apache.org">Maven</a> 

<img src="https://github.com/BernardoChrist/api_ecommerce/assets/141193350/ffb0b7a3-afec-44e5-a5bc-6075fe585e0d" height="40" width="40"> <a href="https://www.postgresql.org">PostgreSQL</a> 

<h2>Autores</h2>  
<div align="center">
<img src="Imagens Readme/github.svg" height="100" width="100"> <br /><a href="https://github.com/BernardoChrist">Bernardo Christ</a> | <a href="https://github.com/leignel">Cristian Leignel</a> | <a href="https://github.com/bordeguilherme">Gulherme Borde</a> | <a href="https://github.com/Igor-Arauj0">Igor Araujo</a> | <a href="https://github.com/PalomaPts">Paloma Tavares</a> | <a href="https://github.com/sh9bba">Felipe de Moura</a> 
  </div>


