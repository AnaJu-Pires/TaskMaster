# TaskMaster API

## Sobre o Projeto

TaskMaster é uma API RESTful robusta, escalável e de fácil manutenção para uma plataforma de gestão de tarefas. Este projeto serve como o backend para futuros aplicativos web e mobile, oferecendo funcionalidades completas de gerenciamento de tarefas.
O desenvolvimento foi guiado por diretrizes técnicas que garantem a qualidade e manutenibilidade do código, incluindo arquitetura em camadas, uso de DTOs e documentação com Swagger.

## Funcionalidades

A API implementa as seguintes funcionalidades:

* **Gerenciamento Completo de Tarefas (CRUD):**
    * Criar uma nova tarefa (`POST /tasks`). 
    * Listar tarefas com suporte a **paginação** e **ordenação** (`GET /tasks`).
    * Buscar uma tarefa específica por seu ID (`GET /tasks/{id}`).
    * Atualizar uma tarefa por completo com `PUT` (`PUT /tasks/{id}`). 
    * Atualizar uma tarefa parcialmente com `PATCH` (`PATCH /tasks/{id}`).
    * Excluir uma tarefa (`DELETE /tasks/{id}`). 
* **Filtro de Tarefas:**
    * Filtrar a lista de tarefas por categoria (`GET /tasks?categoria=...`). 
* **Validação de Dados:**
    * Validação de dados de entrada para garantir a integridade das informações.
* **Tratamento de Erros:**
    * Respostas de erro padronizadas e claras para uma melhor experiência do desenvolvedor.

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA / Hibernate
* Maven
* MySQL (ou outro banco de dados relacional)
* Lombok
* Springdoc OpenAPI (Swagger)

##  Como Executar o Projeto

### Pré-requisitos

* Java 17 ou superior
* Maven
* Um banco de dados **MySQL** em execução.

### Configuração

1.  Clone o repositório.
2.  Crie um banco de dados no MySQL chamado `taskmaster_db` (ou o nome que preferir).
3.  Configure a conexão com o banco de dados no arquivo `src/main/resources/application.properties`:

```properties
# Configuração do Banco de Dados (MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/taskmaster_db
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
```
###  Configuração do JPA/Hibernate

- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.format_sql=true

###  Porta da Aplicação

server.port=8081

### Executando a Aplicação

Na raiz do projeto, execute o seguinte comando no terminal:
```bash
./mvnw spring-boot:run
```


## Documentação da API (Swagger)

###### A API é documentada utilizando o padrão OpenAPI 3, com a interface do Swagger UI, conforme a Diretriz Técnica 6.
###### Após iniciar a aplicação, a documentação interativa estará disponível no seguinte endereço:
###### [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
###### Nesta página, você pode visualizar todos os endpoints, seus detalhes, e testá-los diretamente.


##  Autores

* Ana Júlia Pires Oliveira
* Lucas Bento da Silva Batista
