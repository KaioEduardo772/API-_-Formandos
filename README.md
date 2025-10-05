# 🤏API _ Formandos 👍
Projeto 1 - API - da matéria Projetos de Linguagem de Programação. 

# API

Projeto desenvolvido em Java Spring Boot que fornece uma API para gerenciar dados de alunos, cursos e campi.

Possui um seeder que preenche automaticamente o banco de dados com informações anonimizadas, tornando mais simples a inicialização e os testes da aplicação.

## Funcionalidades

- Permite o cadastro e gerenciamento de entidades como alunos, cursos e campi.

- Inclui um mecanismo de população automática do banco de dados a partir do CSV PDA_2022-2024_1.2_Alunos_Anonimo.csv.

- Disponibiliza uma API RESTful pronta para integração com front-end ou serviços externos.

-Utiliza H2 Database para um ambiente de desenvolvimento ágil e simplificado.

- Adota o fluxo de versionamento GitFlow, com as branches principais: test, homol e main.

## Tecnologias

- Java 17+
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Maven
- Lombok

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Git

## Instalação

1. Clone o repositório:

```bash
git clone git@github.com:JoaoPedroAmaral/formandos-api.git
cd formandos-api

2. Build e rodar a aplicação:

mvn clean install
mvn spring-boot:run

3. Acesse o H2 Console:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:~/formandos
User: sa
Password: (vazio)

● Estrutura do Banco

- Aluno: informações do aluno (nome, matrícula, situação, data de ingresso, curso)

- Curso: nome, modalidade e campus

- Campus: nome do campus

● Endpoints

- GET /alunos – lista todos os alunos
- GET /cursos – lista todos os cursos
- GET /campi – lista todos os campi
- POST /alunos – cadastra novo aluno
- PUT /alunos/{id} – atualiza aluno existente
- DELETE /alunos/{id} – remove aluno

● GitFlow

• Branches utilizadas:

- test – desenvolvimento inicial e testes rápidos.
- homol – homologação e integração.
- main – produção.

• Regras sugeridas:

- Todos os Merge Requests devem ser aprovados antes do merge.
- Proteger branches homol e main para evitar pushes diretos.
