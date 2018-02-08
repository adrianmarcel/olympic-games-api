[![Build Status](https://travis-ci.org/adrianmarcel/olympic-games-api.svg?branch=master)](https://travis-ci.org/adrianmarcel/olympic-games-api)
[![Build Status](https://travis-ci.org/adrianmarcel/olympic-games-api.svg?branch=master)](https://travis-ci.org/adrianmarcel/olympic-games-api)

# API dos Jogos Olímpicos Tokyo 2020
API RESTful para gerir dados dos Jogos Olímpicos Tokyo 2020 em Java 8 e Spring Boot.

Para a construção dessa API optei por utilizar um banco de dados MySQL, pois foi um dos quais eu trabalhei por mais tempo em minhas experiências profissionais, e também para a construção de testes unitários utilzei um banco em memória, o H2, onde apesar de não dominar, acabei conhecendo através de cursos e tutoriais que busco sempre me atualizar.
A escolha dos frameworks foi com base nos últimos estudos que tenho aplicado, cursos e tecnologias das quais tenho curiosidade em aprender, posso ter me esquecido de alguma implementação ou algo do tipo, mas procuro sempre aprimorar os conhecimentos. Estou utilizando também, algo que achei muito interessante, uma ferramenta de integração contínua gratuita, o TravisCI, onde a cada versionamento que realizo no GitHub, ele dispara um build da minha aplicação, onde posso também monitorar os erros que estão ocorrendo.
Consegui realizar a construção de alguns testes unitários, não todos, pois dei preferência em finalizar a construção da API, e deixar para que futuramente possa desenvolver os testes.
Utilizei também para versionamento de banco, o Flyway, onde quando disparo um build do SpringBoot ele cria as tabelas, e as primeiras inserções no banco de dados, para os testes unitários, esse framework está desativado.