# 👥  Client Management API - Spring Boot REST API

API RESTful desenvolvida com Spring Boot para gerenciamento de clientes e usuários.
A aplicação permite realizar operações completas de cadastro, consulta, atualização e remoção de clientes, seguindo boas práticas de arquitetura em camadas.

O projeto conta com autenticação baseada em JWT (JSON Web Token), controle de permissões por perfis de usuário (ADMIN e USER) utilizando Spring Security, além de uma estrutura organizada e escalável.

### Core & Frameworks
* **Java 17+** (Linguagem Principal da aplicação)
* **Spring Boot 3.x** (Framework para desenvolvimento da API REST)
* **Spring Security** & **JWT** (Autenticação e controle de acesso)
* **Jakarta Validation** (Validação de dados de entrada)

### Persistência & Dados
* **MySQL** (Banco de Dados Relacional)
* **Spring Data JPA** & **Hibernate** (Persistência e mapeamento objeto-relacional)
* **Pagination with Pageable** (Paginação de resultados)
* **Dynamic Queries with Specification Pattern** (Filtros dinâmicos e consultas flexíveis)

### Arquitetura & API Design
* **RESTful API** (Padronização dos endpoints HTTP)
* **DTO Pattern** (Transferência segura de dados entre camadas)
* **Layered Architecture** (Separação de responsabilidades da aplicação)
* **RBAC - Role-Based Access Control** (Controle de permissões por tipo de usuário)

### Produtividade & Qualidade
* **Lombok** (Redução de código boilerplate)
* **MapStruct** (Mapeamento automático entre DTOs e entidades)
* **Swagger / OpenAPI 3** (Documentação interativa da API)
* **JUnit 5** & **Mockito** (Testes Unitários e mocks)

### Infraestrutura & Ferramentas
* **Maven** (Gerenciador de Dependências e build)
* **Docker** (Containerização da aplicação)
* **Git & GitHub** (Versionamento de código)
* **Postman** (Testes e validação de endpoints)

---

## ⚙️ Funcionalidades

### 🔐 Autenticação e Segurança
- **Registro de usuários:** Cadastro de novos usuários no sistema.
- **Autenticação com JWT:** Geração de tokens para acesso seguro aos endpoints.
- **Controle de acesso por Roles:** Permissões baseadas em perfis (**ADMIN** e **USER**).
- **Spring Security:** Proteção e autorização dos endpoints da aplicação.
- **Validação de dados:** Verificação automática de requisições utilizando Jakarta Validation.

### 👤 Gerenciamento de Usuários

- **Cadastro de usuários:** Registro de novos usuários no sistema.
- **Autenticação com JWT:** Login e geração de token para acesso seguro.
- **Controle de permissões:** Diferenciação de acesso entre usuários ADMIN e USER.
- **Criptografia de senhas:** Armazenamento seguro utilizando BCrypt.
  
### 👥 Gerenciamento de Clientes
- **Cadastro de clientes:** Criação de novos registros com validações.
- **Listagem paginada:** Consulta de clientes com suporte a paginação e ordenação.
- **Filtros dinâmicos:** Busca de clientes por parâmetros opcionais utilizando Specification.
- **Busca por ID:** Consulta específica de clientes cadastrados.
- **Atualização de dados:** Edição de informações existentes.
- **Remoção de clientes:** Exclusão de registros do banco de dados.
  
### 🔒 Controle de Permissões
- **ADMIN:** Acesso total às operações da aplicação (CRUD completo).
- **USER:** Permissão apenas para visualização e consulta de clientes.

### 🏗️ Diferenciais Técnicos
- **API RESTful:** Endpoints seguindo padrões HTTP e boas práticas REST.
- **Arquitetura em Camadas:** Separação de responsabilidades entre Controller, Service e Repository.
- **DTO Pattern:** Transferência segura de dados entre camadas da aplicação.
- **Persistência com JPA/Hibernate:** Integração com banco de dados relacional utilizando ORM.
- **Documentação Swagger/OpenAPI:** Interface interativa para testes e documentação da API.

---
  
## ▶️ Como executar o projeto

### 📋 Pré-requisitos

Antes de iniciar, você precisará ter instalado:

- Java 17+
- Maven
- MySQL
- Git

Ou utilizar Docker para subir o banco de dados (Ainda está em desenvolvimento no momento).

---

## 💻 Executando localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/client-management-api.git
````

### 2. Acessar a branch de desenvolvimento
````bash
cd client-management-api
````
````bash
git checkout development
````

### 3. Configurar o banco de dados:
Edite o arquivo:
````
src/main/resources/application.properties
````
Configure suas credenciais do MySQL:
````properties
spring.datasource.url=jdbc:mysql://localhost:3306/client_management
spring.datasource.username=root
spring.datasource.password=sua_senha
````

### 4. Executar a aplicação 
````bash
mvn spring-boot:run
````

### 🌐 A aplicação estará disponível em
````
http://localhost:8080
````

---

## 📚 Documentação da API

A documentação interativa da API está disponível através do Swagger UI.

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```
OU
```text
http://localhost:8080/swagger-ui/index.html
````
(depende da versão do SpringDoc)

## 🔍 Recursos disponíveis no Swagger

- Visualização de todos os endpoints da API
- Teste de requisições diretamente pelo navegador
- Autenticação utilizando JWT
- Consulta de parâmetros, respostas e códigos HTTP

## 🔐 Autenticação no Swagger

1. Realize login no endpoint:
````
 /auth/login
````
2. Copie o token JWT retornado
3. Clique no botão **Authorize** no Swagger
4. Insira o token no formato:
````
Bearer SEU_TOKEN
````

---

## 📍 Endpoints da API

Abaixo estão os principais recursos da aplicação.

> ⚠️ Endpoints protegidos requerem autenticação via JWT.
>
> **Authorization:** `Bearer <seu_token>`

---

<details>
  <summary>🔑 Autenticação</summary>

### Login

`POST /auth/login`

#### Request Body

```json
{
  "username": "admin",
  "password": "123456"
}
````
#### Response
````json
{
  "token": "JWT_TOKEN"
}
````
Retorna um token JWT para autenticação nas demais requisições.
</details>

<details> 
  <summary>👤 Usuários</summary>
  
### Criar Usuário

`POST /users`

#### Request Body
````json
{
  "username": "joao",
  "password": "123456",
  "role": "USER"
}
````

| Método | Endpoint | Descrição |
|---|---|---|
| 🟢 POST | `/users` | Cria um novo usuário |
| 🔵 GET | `/users` | Lista todos os usuários |
| 🟠 PUT | `/users/{id}` | Atualiza um usuário |
| 🔴 DELETE | `/users/{id}` | Remove um usuário |
</details>

<details> 
  <summary>👥 Clientes</summary>
  
### Criar Cliente

`POST /clients`

#### Request Body
````json
{
  "name": "Maria Silva",
  "email": "maria@email.com",
  "phoneNumber": "11999999999"
}
````

| Método | Endpoint | Descrição |
|---|---|---|
| 🟢 POST | `/clients` | Cadastra um novo cliente |
| 🔵 GET | `/clients` | Lista clientes com paginação e filtros |
| 🔵 GET | `/clients/{id}` | Busca cliente por ID |
| 🟠 PUT | `/clients/{id}` | Atualiza dados de um cliente |
| 🔴 DELETE | `/clients/{id}` | Remove um cliente |

## 🔍 Exemplo de paginação e filtros
````http
GET /clients?page=0&size=5&name=maria
````
</details>

---

## 🧩 Arquitetura do Projeto

A aplicação foi desenvolvida seguindo os princípios de **Clean Code** e **Arquitetura em Camadas**, garantindo organização, separação de responsabilidades e facilidade de manutenção.

### 📂 Estrutura do projeto

```text
src/main/java/com/natan/clientmanagementapi
│
├── 📂 api
│   ├── 📂 controller      # Endpoints REST
│   ├── 📂 dto             # Request/Response DTOs
│   ├── 📂 service         # Regras de negócio
│   └── 📂 specification   # Filtros dinâmicos (JPA Specification)
│
├── 📂 domain
│   └── 📂 entity          # Entidades JPA
│
├── 📂 repository          # Interfaces JPA Repository
│
├── 📂 security
│   ├── 📂 jwt             # Geração e validação de tokens JWT
│   └── 📂 config          # Configurações do Spring Security
│
├── 📂 exception           # Tratamento global de exceções
│
└── 📂 config              # Configurações gerais da aplicação
```
----

## 🛡️ Segurança e Proteção

A API implementa múltiplas camadas de segurança para garantir autenticação, autorização e integridade dos dados:

- **JWT (JSON Web Token):** Autenticação stateless baseada em tokens com expiração configurável.
- **Spring Security:** Proteção dos endpoints e gerenciamento das regras de acesso.
- **BCrypt Password Encoder:** Criptografia segura de senhas antes da persistência no banco de dados.
- **RBAC (Role-Based Access Control):** Controle de permissões baseado em perfis (`ADMIN` e `USER`).
- **Autorização por Roles:** Restrição de acesso a endpoints específicos conforme o perfil do usuário.
- **Validação de Dados:** Uso de Jakarta Validation para validação de campos nas requisições.
- **Tratamento Global de Exceções:** Respostas padronizadas para erros de autenticação, autorização e validação.
- **Validação de Unicidade:** Regras de negócio para impedir cadastro de usuários, e-mails ou telefones duplicados.

--- 

## 📝 Notas de Desenvolvimento

- O projeto utiliza **MySQL** como banco de dados relacional.
- As tabelas são gerenciadas automaticamente pelo **Hibernate/JPA**.
- A autenticação da API é realizada com **JWT (JSON Web Token)**.
- Endpoints protegidos exigem autenticação via header `Authorization`.
- O sistema possui um usuário `ADMIN` inicial para testes da aplicação.
- As validações da API são implementadas com **Jakarta Validation**.

---

## 📌 Próximos Passos

Este projeto continua em evolução e novas funcionalidades serão adicionadas futuramente:

- [x] Paginação e filtros dinâmicos com JPA Specification
- [x] Documentação completa da API com Swagger (OpenAPI 3)
- [x] Testes unitários com JUnit e Mockito
- [x] Criação automática de usuário ADMIN

### 🔜 Melhorias planejadas

- [ ] Implementar Refresh Token
- [ ] Adicionar testes de integração com Testcontainers
- [ ] Finalizar containerização com Docker e Docker Compose
- [ ] Integrar Spring Boot Actuator e Prometheus
- [ ] Implementar Flyway para versionamento de banco
- [ ] Desenvolver Front-end com Angular ou React
- [ ] Configurar CI/CD para deploy automático
- [ ] Realizar deploy em nuvem (AWS ou Azure)

---

## 🤝 Contribuição

Contribuições são bem-vindas.

1. Faça um **Fork** do projeto
2. Crie uma branch para sua feature

```bash
git checkout -b feature/NovaFeature
````  
3. Commit suas alterações
````bash
git commit -m "feat: adiciona nova feature"
````
4. Faça push para a branch
````bash
git push origin feature/NovaFeature
````
5. Abra um Pull Request

---

## 👨🏻‍💻 Autor

Desenvolvido por **Natanael Ribeiro**.

Projeto criado para estudo e prática de:

- Spring Boot
- Spring Security
- APIs REST
- Arquitetura Backend Java
  
## 🔗 Contato
**GitHub:** github.com/natanaelrb
**LinkedIn:** linkedin.com/in/natanaelribeirodev

---

## 📄 Licença

Projeto desenvolvido para fins educacionais e de estudo.
 
