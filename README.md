
# 🎟️ RollTickets API

RollTickets é um sistema completo de gerenciamento de ingressos de cinema. Esta parte do projeto representa o **backend**, desenvolvido em **Java com Spring Boot**.

---

## 🎓 Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina de **Programação Orientada a Objetos (POO)** da faculdade. Ele simula um sistema real de venda de ingressos, sessões de filmes, controle de assentos e integração com gateway de pagamento.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Web
- MySQL
- Maven
- Lombok

---

## 📁 Estrutura do Projeto

```
RollTickets-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/rolltickets/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       ├── services/
│   │   │       └── RollTicketsApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
├── pom.xml
└── mvnw / mvnw.cmd
```

---

## ⚙️ Configuração

### Pré-requisitos

- Java 17 ou superior  
- MySQL  
- Maven 3.8+  

### Criar banco de dados

No MySQL, crie o banco de dados:

```sql
CREATE DATABASE rolltickets_db;
```

### Configurar variáveis de ambiente

Para proteger dados sensíveis como usuário, senha, tokens e chaves de API, o projeto utiliza variáveis de ambiente. Essas variáveis devem ser definidas no sistema operacional para serem lidas automaticamente pela aplicação.

Exemplo das propriedades no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rolltickets_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
tmdb.api.key=${TMDB_API_KEY}
mercadopago.token=${MERCADO_PAGO_TOKEN}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### Como definir variáveis de ambiente

- **No Windows (PowerShell):**

```powershell
setx DB_USER "seu_usuario_mysql"
setx DB_PASS "sua_senha_mysql"
setx TMDB_API_KEY "sua_chave_api_tmdb"
setx MERCADO_PAGO_TOKEN "seu_token_mercado_pago"
```

*Após executar, feche e abra novamente o terminal para que as variáveis estejam ativas.*

- **No Linux/macOS (bash/zsh):**

```bash
export DB_USER="seu_usuario_mysql"
export DB_PASS="sua_senha_mysql"
export TMDB_API_KEY="sua_chave_api_tmdb"
export MERCADO_PAGO_TOKEN="seu_token_mercado_pago"
```

*Essas variáveis estarão disponíveis enquanto o terminal estiver aberto.*

---

## ▶️ Executando o Projeto

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:  
📍 `http://localhost:8080`

---

## 📡 Endpoints Principais (Exemplos)

| Método | Endpoint               | Descrição                     |
|--------|------------------------|------------------------------|
| GET    | `/api/filmes`          | Lista todos os filmes         |
| POST   | `/api/clientes`        | Cadastra um novo cliente      |
| POST   | `/api/ingressos`       | Compra um ingresso            |
| GET    | `/api/sessoes/{id}`    | Detalhes de uma sessão        |
| POST   | `/api/pagamentos`      | Inicia um pagamento           |

---

## 🛠️ Comandos Úteis

- Build do projeto: `./mvnw clean install`
- Rodar testes: `./mvnw test`
- Limpar cache do Maven: `./mvnw clean`

---

## 🧪 Testes

Os testes estão localizados em `src/test/java/com/rolltickets/`. Para executá-los:

```bash
./mvnw test
```

---

## 📌 Observações

- Este repositório representa **apenas o backend**. A interface (frontend React) está em outro repositório: [`RollTickets-Front`](https://github.com/JF532/RollTickets-Front)
- A integração com o [Mercado Pago](https://www.mercadopago.com.br/) é usada para pagamentos online.

---

🎬 Integração com a TMDB API

O sistema RollTickets utiliza a TMDB API (The Movie Database) para obter dados reais de filmes, como:

- Título
- Sinopse
- Imagem
- Classificação indicativa
- Gênero

### Como funciona a integração:

- Backend: acessa a TMDB API para importar automaticamente os filmes em cartaz, que são armazenados no banco de dados.
- Frontend: acessa a TMDB API separadamente para exibir os filmes que ainda serão lançados, na seção "Em Breve".
- 🔑 A chave de API da TMDB deve ser gerada e configurada no ambiente da aplicação.

---

## 👨‍👩‍👧‍👦 Autores

- João Filipe Peixoto de Carvalho – [@JF532](https://github.com/JF532)
- Fernanda Alves Tamarindo – [@FerTamarindo](https://github.com/FerTamarindo)
- Felipe Taveira – [@Feliipee013](https://github.com/Feliipee013)
