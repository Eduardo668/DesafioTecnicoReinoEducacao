# 🧭Desafio Técnico Reino Educação

API RESTful para **gerenciamento de milhas de clientes**, desenvolvida com **Java 21** e **Spring Boot**.  
Esta API permite cadastrar, consultar, atualizar, excluir clientes e **gerenciar o saldo de milhas** (adicionar ou reduzir).

---

## 🧰 Tecnologias Utilizadas
- ☕ **Java 21**
- 🌱 **Spring Boot 3**
- 🧩 **Spring Data JPA**
- 🧪 **H2 Database** (ambiente de teste)
- ⚙️ **ModelMapper** (ou MapStruct, conforme projeto)
- 🐳 **Docker** e **Docker Compose**
- 📘 **Swagger / OpenAPI 3.1**

---

## 🚀 Como Executar a Aplicação

### 1) 🐋 Via **Docker Compose** (recomendado para testadores)
> Não precisa ter Java instalado.

```bash
# primeiramente (gera imagem e sobe)
docker compose up --build

# execuções seguintes
docker compose up

# Caso não queira acessar o container ao rodar
docker compose up -d
````

**Acesso:** `http://localhost:8080`

**Swagger UI (Documentação automatica dos endpoints):**

* `http://localhost:8080/swagger-ui.html`
* ou `http://localhost:8080/swagger-ui/index.html`
  **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`
  **OpenAPI YAML:** `http://localhost:8080/v3/api-docs.yaml`

---

### 2) 🐋 Via **Docker** (sem compose)

```bash
# build da imagem
docker build -t reino-educacao-api .

# executa o container
docker run -d -p 8080:8080 --name reino-educacao-api reino-educacao-api

# logs (opcional)
docker logs -f reino-educacao-api
```

**Acesso:** `http://localhost:8080`
**Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

### 3) ☕ Via **Maven** (para quem já tem Java 21 configurado)

```bash
# build
mvn clean package

# executar com plugin
mvn spring-boot:run

# ou rodar o JAR
java -jar target/MileManagementReinoEdu-0.0.1-SNAPSHOT.jar
```

**Acesso:** `http://localhost:8080`
**Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

## 🌐 Base URL

```
http://localhost:8080/api/v1
```

---

## 📚 Endpoints

### 📦 ClienteController

#### 🔹 `GET /clientes`

**Descrição:** Busca todos os clientes.
**Resposta 200 OK (array de `ClienteDto` completo):**

```json
[
  {
    "id": 1,
    "nome": "Eduardo Paixão",
    "email": "eduardosehn20@gmail.com",
    "cartao": "Gold",
    "saldoMilhas": 10000,
    "destinoDesejado": "Florianópolis"
  }
]
```

---

#### 🔹 `GET /clientes/{clienteId}`

**Descrição:** Busca um cliente pelo ID.
**Parâmetros de caminho:** `clienteId` (integer)

**Resposta 200 OK (`ClienteDto` completo):**

```json
{
  "id": 1,
  "nome": "Eduardo Paixão",
  "email": "eduardosehn20@gmail.com",
  "cartao": "Black",
  "saldoMilhas": 10000,
  "destinoDesejado": "Florianópolis"
}
```

---

#### 🔹 `POST /clientes`

**Descrição:** Cria um novo cliente.
**Body (`ClienteDto` mínimo exigido pelo schema):**

```json
{
  "nome": "Maria Silva",
  "email": "maria.silva@example.com",
  "cartao": "Gold",
  "saldoMilhas": 5000,
  "destinoDesejado": "Paris"
}
```

**Resposta 200 OK (`ClienteDto` completo):**

```json
{
  "id": 2,
  "nome": "Maria Silva",
  "email": "maria.silva@example.com",
  "cartao": "Gold",
  "saldoMilhas": 5000,
  "destinoDesejado": "Paris"
}
```

---

#### 🔹 `PUT /clientes/{clienteId}`

**Descrição:** Atualiza os dados do cliente.
**Parâmetros de caminho:** `clienteId` (integer)

**Body (`UpdateClienteDto` – atualização de campos):**

```json
{
  "nome": "Maria Silva Atualizada",
  "email": "maria.silva@update.com",
  "cartao": "Black",
  "saldoMilhas": 6000,
  "destinoDesejado": "Lisboa"
}
```

**Resposta 200 OK (`ClienteDto` completo):**

```json
{
  "id": 2,
  "nome": "Maria Silva Atualizada",
  "email": "maria.silva@update.com",
  "cartao": "Black",
  "saldoMilhas": 6000,
  "destinoDesejado": "Lisboa"
}
```

---

#### 🔹 `DELETE /clientes/{clienteId}`

**Descrição:** Remove completamente o cliente.
**Parâmetros de caminho:** `clienteId` (integer)

**Resposta 200 OK (string):**

```json
"Cliente com ID:1 deletado com sucesso!"
```

---

### ✈️ Gerenciamento de Milhas

#### 🔹 `PATCH /clientes/{clienteId}/adicionar-milhas`

**Descrição:** Soma milhas ao saldo do cliente.
**Parâmetros de caminho:** `clienteId` (integer)

**Body (`UpdateMilesRequest`):**

```json
{
  "quantidade": 1000
}
```

**Resposta 200 OK (`ClienteDto` completo):**

```json
{
  "id": 1,
  "nome": "Eduardo Paixão",
  "email": "eduardosehn20@gmail.com",
  "cartao": "Gold",
  "saldoMilhas": 11000,
  "destinoDesejado": "Florianópolis"
}
```

---

#### 🔹 `PATCH /clientes/{clienteId}/reduzir-milhas`

**Descrição:** Reduz milhas do saldo do cliente (se houver saldo suficiente).
**Parâmetros de caminho:** `clienteId` (integer)

**Body (`UpdateMilesRequest`):**

```json
{
  "quantidade": 2000
}
```

**Resposta 200 OK (`ClienteDto` completo):**

```json
{
  "id": 1,
  "nome": "Eduardo Paixão",
  "email": "eduardosehn20@gmail.com",
  "cartao": "Black",
  "saldoMilhas": 8000,
  "destinoDesejado": "Florianópolis"
}
```

**Erro 400 Bad Request (exemplo de regra de negócio):**

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "A quantidade de milhas a reduzir é maior que o saldo atual do cliente.",
  "path": "/api/v1/clientes/1/reduzir-milhas"
}
```

---

## 🧩 Schemas

### `ClienteDto`

* `id` (integer) – Identificador do cliente
* `nome` (string, max 200)
* `email` (string, max 100)
* `cartao` (string, ex.: `"Gold"`, `"Black"`)
* `saldoMilhas` (integer)
* `destinoDesejado` (string, max 400)

### `UpdateClienteDto`

Campos opcionais para atualização:

* `nome`, `email`, `cartao`, `saldoMilhas`, `destinoDesejado`

### `UpdateMilesRequest`

* `quantidade` (integer, obrigatório)


---

## 📬 Contato

**Autor:** Eduardo Paixão
**E-mail:** [eduardosehn20@gmail.com](mailto:eduardosehn20@gmail.com)

---

```
```
