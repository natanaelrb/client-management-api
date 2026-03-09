# 📈  Client Management API - Spring Boot REST API

API RESTful desenvolvida com Spring Boot para gerenciamento de clientes e usuários. A aplicação permite realizar operações completas de cadastro, consulta, atualização e remoção de clientes, seguindo boas práticas de arquitetura em camadas com autenticação baseada em JWT (JSON Web Token), controle de acesso e permissões por perfis de usuário (ADMIN e USER) e controle de acesso utilizando Spring Security.
O projeto segue boas práticas de arquitetura em camadas, separando responsabilidades entre controller, service, repository, domain, exception, DTOs e security, proporcionando uma aplicação escalável e organizada.

## 🛠️ Tecnologias e Ferramentas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

### Core & Frameworks
* **Java 17+** (Linguagem Principal)
* **Spring Boot 3.x** (Ecossistema de Microserviços)
* **Spring Security** & **JWT** (Autenticação e Autorização)
* **Jakarta Validation** (Validação de dados)

### Persistência & Dados
* **MySQL** (Banco de Dados Relacional)
* **Spring Data JPA** & **Hibernate** (ORM)
* **Flyway** (Gerenciamento de Migrations de Banco)

### Produtividade & Qualidade
* **Lombok** (Redução de Boilerplate)
* **MapStruct** (Mapeamento de DTOs)
* **Swagger (OpenAPI 3)** (Documentação da API)
* **JUnit 5** & **Mockito** (Testes Unitários)

### Infraestrutura & Ferramentas
* **Maven** (Gerenciador de Dependências)
* **Docker** (Containerização)
* **Git & GitHub** (Versionamento)
* **Postman** (Testes de Endpoints)

## 🎯 Funcionalidades

### 👤 Autenticação e Segurança
- **Registro de usuários:** Cadastro de novos usuários no sistema.
- **Login com JWT:** Geração de tokens para autenticação segura.
- **Autorização por Roles:** Controle de acesso baseado em perfis (**ADMIN** e **USER**).
- **Spring Security:** Proteção robusta de todos os endpoints.
- **Validação:** Verificação de dados em requisições autenticadas.

### 📋 Gerenciamento de Clientes
- **Cadastro:** Registro de novos clientes com validação de dados.
- **Listagem:** Consulta de todos os clientes cadastrados.
- **Busca por ID:** Filtro específico para encontrar um cliente.
- **Atualização:** Edição de informações existentes.
- **Remoção:** Exclusão de clientes do banco de dados.

### 🔐 Controle de Permissões
- **ADMIN:** Possui acesso total (Criar, Atualizar, Excluir e Visualizar).
- **USER:** Acesso limitado apenas à visualização e consulta de clientes.

### ⚙️ Diferenciais Técnicos
- **API REST:** Endpoints seguindo as melhores práticas e padrões HTTP.
- **Validações:** Regras de negócio para evitar duplicidade de e-mail e telefone.
- **Persistência:** Integração completa com banco de dados relacional.

## ⚙️ Como executar o projeto

Pré-requisitos
- Java 17 ou superior
- Maven instalado
- MySQL rodando localmente (ou via Docker)

Siga os passos abaixo para rodar a aplicação localmente:

### 1. Clonar o repositório
```bash
Bash
git clone https://github.com/seu-usuario/client-management-api.git
````

### 2. Acessar a branch de desenvolvimento
````bash
Bash
cd client-management-api
git checkout development
````

## 3. Configurar o banco:
````bash
Ajuste as propriedades em src/main/resources/application.properties com suas credenciais do MySQL.
````
## 4. Executar
````bash
Bash
mvn spring-boot:run
````
## A API estará disponível em:
````
http://localhost:8080
````

## 🚀 Endpoints da API

Abaixo estão os principais recursos da aplicação. 
> **Nota:** Os endpoints de Usuários e Clientes requerem o header `Authorization: Bearer <seu_token>`.


<details>
  <summary>🔐 Autenticação (Clique para expandir)</summary>

### Login
`POST /auth/login`
## Request Body
```json
{ 
  "username": "admin",
  "password": "123456"
}
````
````json
JSON
{
  "token": "JWT_TOKEN"
}
Retorna um JWT_TOKEN para ser usado nas demais requisições.
````
</details>

<details>
  <summary>👤 Usuários</summary>

## Criar Usuário
`POST /users`

## Request Body:
````json
JSON
{
  "username": "joao",
  "password": "123456",
  "role": "USER"
}
````


| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| POST | `/users` | Cria um novo usuário (Admin/User) |
| GET | `/users` | Lista todos os usuários do sistema |
| PUT | `/users/{id}` | Atualizar dados de um usuário |
| DELETE | `/users/{id}` | Remover um usuário |

</details>

<details>
  <summary>👥 Clientes</summary>

  ## Criar Cliente
`POST /clients`
```json
JSON

{
  "name": "Maria Silva",
  "email": "maria@email.com",
  "phoneNumber": "11999999999"
}
```

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| POST | `/clients` | Cadastra um novo cliente |
| GET | `/clients` | Lista todos os clientes |
| GET | `/clients/{id}` | Busca cliente por ID |
| DELETE | `/clients/{id}` | Remover um cliente |

</details>

## 📂 Estrutura do Projeto
  ## 🏗️ Arquitetura

O projeto foi construído seguindo os princípios de **Clean Code** e a **Arquitetura em Camadas**:
- **Controller:** Gerenciamento dos endpoints e rotas REST.
- **Service:** Onde reside toda a inteligência e regras de negócio.
- **Repository:** Interface de comunicação direta com o banco de dados via JPA.
- **DTOs:** Separação total entre as entidades do banco e os dados que trafegam na API.
  
A organização do código segue o padrão de camadas para garantir a separação de responsabilidades e facilitar a manutenção:

```text
src/main/java/com/natan/clientmanagementapi/api
├── 🎮 controller   # Porta de entrada (Endpoints REST)
├── ⚙️ service      # Regras de negócio da aplicação
├── 🏛️ domain       # Entidades do banco de dados (JPA Entities)
├── 📦 repository   # Comunicação com o banco (Spring Data JPA)
├── ✉️ dto          # Objetos de transferência de dados (Request/Response)
├── 🛡️ security     # Configurações de JWT e Spring Security
├── ⚠️ exception    # Tratamento de erros e exceções globais
└── 🛠️ config       # Configurações gerais (Password/Swagger/OpenAPI)
````
## 🛡️ Segurança e Proteção

A API implementa diversas camadas de segurança para garantir a integridade dos dados:

* **Autenticação com JWT:** Tokens com tempo de expiração para validar sessões.
* **Criptografia BCrypt:** Senhas de usuários nunca são salvas em texto puro no banco.
* **RBAC (Role Based Access Control):** Controle de acesso baseado em perfis (`ADMIN` e `USER`).
* **Tratamento de Erro 403:** Respostas customizadas para tentativas de acesso não autorizado.
* **Validação de Unicidade:** Regras de negócio para evitar `username` ou e-mails duplicados.

## 📝 Notas de Desenvolvimento

Para fins de demonstração e facilitação de testes, o projeto atual conta com as seguintes características:

* **🗄️ Banco de Dados:** Atualmente integrado com **MySQL**, utilizando o **Flyway** para garantir que as tabelas sejam criadas automaticamente ao rodar o projeto.
* **🛡️ Segurança:** A autenticação é realizada via **JWT (JSON Web Token)**, protegendo os endpoints de escrita (`POST`, `PUT`, `DELETE`) e exigindo o token no Header das requisições.
* **🔑 Dados Iniciais:** O sistema já conta com um usuário `ADMIN` padrão (configurado via `import.sql` ou seeder) para permitir o primeiro login e teste das funcionalidades.
* **✅ Validações:** Implementadas com **Jakarta Validation**, garantindo que não existam e-mails duplicados ou campos vazios no banco.

---

## 🤝 Próximos Passos & Contribuição

Este é um projeto de portfólio em constante evolução. Os próximos marcos de desenvolvimento incluem:

- [ ] **Testes de Integração:** Implementar testes completos de ponta a ponta com **Testcontainers**.
- [ ] **Containerização:** Criar um arquivo `docker-compose.yml` para subir a API e o Banco com um único comando.
- [ ] **Monitoramento:** Integrar o **Spring Boot Actuator** e o **Prometheus** para métricas em tempo real.
- [ ] **Front-end:** Desenvolver um Dashboard em **Angular** ou **React** para consumir esta API.
- [ ] **Deploy:** Configurar uma esteira de CI/CD para deploy automático na **AWS** ou **Azure**.

---

### 👨‍💻 Como contribuir
1. Faça um **Fork** do projeto.
2. Crie uma **Branch** para sua feature (`git checkout -b feature/NovaFeature`).
3. Dê um **Commit** nas suas alterações (`git commit -m 'Add: Nova Feature'`).
4. Faça um **Push** para a Branch (`git push origin feature/NovaFeature`).
5. Abra um **Pull Request**.

## 📈 Melhorias Futuras

- [ ] **Refresh Token:** Implementar renovação de sessão sem necessidade de novo login.
- [ ] **Paginação de Resultados:** Melhorar a performance em listagens grandes.
- [ ] **Testes com Mockito:** Aumentar a cobertura de testes unitários.
- [ ] **Dockerização:** Facilitar o deploy com Docker e Docker Compose.
- [ ] **Cloud Deploy:** Hospedar a API em um ambiente de nuvem (AWS/Azure).
- [ ] **Documentação:** Adicionar documentação com Swagger

## 👨🏻‍💻 Autor

Desenvolvido por **Natanael Ribeiro**. 
Projeto criado para estudo e prática intensiva de **Spring Boot, Spring Security e APIs REST**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/natanaelribeirodev)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/natanaelrb)

---
## 📄 Licença
Projeto desenvolvido para fins educacionais e de estudo.
 
