<section align="center">

# EM DESENVOLVIMENTO

# 🛍️ Spinning Joias - Backend

Este repositório contém a API responsável pela lógica de funcionamento do sistema da Spinning Joias.

</section>

---

## 📌 Sobre o Projeto

Spinning Joias é um catálogo de produtos desenvolvido para facilitar a comunicação entre cliente e loja.  
Este backend permite que usuários se cadastrem, façam login e adicionem produtos a um carrinho. Ao finalizar a compra, é gerada uma **mensagem automática** contendo os dados da compra, que será enviada para o **WhatsApp da loja**.

> ⚠️ Este sistema **não realiza transações financeiras**. O objetivo é **gerar um carrinho de compras e facilitar o atendimento da loja via WhatsApp**.

---

## ✨ Funcionalidades

- Cadastro e autenticação de usuários
- Cadastro de produtos com informações como:
  - Nome, descrição, preço, categoria, imagem e informações extras
- Adição e remoção de produtos no carrinho
- Geração de mensagem automática com os dados do pedido
- Integração com o WhatsApp (via link)

---

## 🛠️ Tecnologias Utilizadas

- [Java 17](https://www.oracle.com/br/java/technologies/downloads/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/)
- [JUnit](https://junit.org/) para testes

---

## ⚙️ Como rodar localmente

### 1. Pré-requisitos

- Java JDK 17 ou superior
- Maven instalado

### 2. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/spinning-joias-backend.git
cd spinning-joias-backend
```

### 3. Criar o arquivo de propriedades
Crie um arquivo chamado application.properties na raiz do projeto com base no arquivo application.example.properties.
Nele devem estar as configurações necessárias para rodar o projeto localmente (porta, configurações de segurança, etc.).

### 4. Executar a aplicação
````bash
./mvnw spring-boot:run
````

## 🧪 Testes
Para rodar os testes automatizados:
```bash
./mvnw test
```

## 📁 Estrutura Inicial
````
src/
├── main/
│   ├── java/
│   │   └── com/spinningjoias/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── service/
│   │       └── SpinningJoiasApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/
└── test/
````

## 🔗 Repositórios Relacionados
[🔸 Frontend - Spinning Joias](https://github.com/vpaesi/spinning-joias/)
