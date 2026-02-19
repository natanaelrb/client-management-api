# 🚀 Client Management API

API REST desenvolvida com Spring Boot para gerenciamento de usuários e clientes.

O projeto foi construído com foco em boas práticas de desenvolvimento backend, arquitetura em camadas, autenticação com JWT e controle de acesso baseado em perfis (RBAC).

---

## 🛠 Tecnologias Utilizadas

- Java 24
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Maven
- Git & GitHub

---

# 🔐 Segurança

- Autenticação com JWT
- Senhas criptografadas com BCrypt
- Controle de acesso baseado em roles (ADMIN / USER)
- Proteção de endpoints com Spring Security
- Tratamento adequado de erro 403 (Forbidden)
- Validação para evitar username duplicado

---

## 📌 Funcionalidades
- Cadastro de usuário
- Login com geração de token JWT
- Listagem de usuários
- Atualização de usuários
- Exclusão de usuários
- Controle de acesso por perfil
- Validações e tratamento de exceções

---

## 🧱 Arquitetura
- O projeto segue arquitetura em camadas:
- Controller
- Service
- Repository
- DTOs separados das entidades
- Princípios de Clean Code

---

## ⚙️ Como executar o projeto
- Pré-requisitos
- Java JDK 21 ou superior
- MySQL 8.x
- Maven
- Passos
- Bash
- Copiar código
- git clone <url-do-repositório>
- cd client-management-api
- mvn spring-boot:run
- Configurar o application.properties com suas credenciais do MySQL.

---
## 📈 Próximas melhorias
- Implementação de testes unitários
- Paginação nas consultas
- Dockerização da aplicação
- Deploy em ambiente cloud
