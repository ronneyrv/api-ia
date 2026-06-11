# Budgeting API

API inteligente para registro e consulta de transações financeiras através de linguagem natural e reconhecimento de voz, desenvolvida com Java, Spring Boot, Spring AI e OpenAI.

## 📖 Visão Geral

O objetivo do projeto é permitir que usuários registrem despesas utilizando voz ou texto em linguagem natural.

Exemplo:

> "Fui jantar no shopping e gastei 79 reais."

Fluxo da aplicação:

1. Recebe um áudio enviado pelo usuário.
2. Realiza a transcrição utilizando Whisper.
3. Interpreta a mensagem com OpenAI.
4. Identifica descrição, valor e categoria.
5. Executa os casos de uso através de Tool Calling.
6. Persiste a transação no banco de dados.
7. Permite consultas por categoria.

---

## 🏗️ Arquitetura

O projeto foi estruturado seguindo princípios de Clean Architecture.

```text
src/main/java

application
├── input
│   └── PersitTransactionInput
├── output
│   └── PersistTransactionOutput
├── PersistTransactionUseCase
└── ListTransactionsByCategoryUseCase

config
└── AiConfig

controller
├── ChatClientController
├── ChatModelController
├── TextToSpeechController
└── TransactionController

domain
├── Transaction
├── Transactionld
├── Category
└── TransactionRepository

infrastructure
├── persistence
│   ├── entity
│   └── repository
│
├── ai
│   └── prompts
│
└── web
    ├── request
    └── response

```

### Domain

Contém as regras de negócio e contratos da aplicação.

**Entidades e Objetos de Valor**

- Transaction
- TransactionId
- Category

**Portas**

- TransactionRepository

A camada de domínio não possui dependência de frameworks.

---

### Application

Contém os casos de uso da aplicação.

**Inputs**

- PersistTransactionInput

**Outputs**

- PersistTransactionOutput

**Use Cases**

- PersistTransactionUseCase
- ListTransactionsByCategoryUseCase

Os casos de uso representam as regras de negócio da aplicação e podem ser executados tanto por endpoints REST quanto por ferramentas utilizadas pela IA.

---

### Infrastructure

Responsável pelas implementações técnicas da aplicação.

#### Persistência

- TransactionEntity
- JpaTransactionRepository
- TransactionRepositoryImpl

Implementa a comunicação com o banco de dados utilizando Spring Data JPA.

#### HTTP

- TransactionController
- TransactionRequest
- TransactionResponse

Responsável pela exposição dos endpoints REST.

---

### Configuração

#### AiConfig

Centraliza a configuração dos componentes de IA utilizados pelo sistema:

- ChatModel
- ChatClient
- OpenAI
- Whisper

---

### Controllers

#### TransactionController

Responsável pelas operações de transações:

- Criar transação
- Consultar transações por categoria
- Processar transações via áudio

#### ChatModelController

Exemplos de utilização direta do ChatModel.

#### ChatClientController

Exemplos de utilização do ChatClient com Prompt Engineering.

#### TextToSpeechController

Responsável pela geração de áudio através da API Text-to-Speech da OpenAI.

---

## 🤖 Arquitetura de IA

A aplicação utiliza recursos do Spring AI para processamento de linguagem natural.

```text
Áudio
 ↓
Whisper
 ↓
Transcrição
 ↓
GPT-4o Mini
 ↓
ChatClient
 ↓
Tool Calling
 ├── PersistTransactionUseCase
 └── ListTransactionsByCategoryUseCase
 ↓
MySQL
```

### Recursos Implementados

- ChatModel
- ChatClient
- Prompt Engineering
- Tool Calling
- Speech-to-Text (Whisper)
- Text-to-Speech (OpenAI TTS)

---

## 🎯 Padrões Arquiteturais

- Clean Architecture
- Ports and Adapters
- Repository Pattern
- Use Case Pattern
- Dependency Injection
- Tool Calling com Spring AI
### Inicialização

```bash
./gradlew bootRun
```

O Spring Boot detecta automaticamente o arquivo `compose.yml` e inicializa o banco de dados local.

---

## ⚙️ Configuração

### application.properties

```properties
spring.application.name=budgeting

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.ai.openai.api-key=${OPENAI_API_KEY}

spring.ai.openai.chat.options.model=gpt-4o-mini

spring.ai.model.audio.transcription=openai
spring.ai.openai.audio.transcription.options.model=whisper-1
```

### Variáveis de Ambiente

```bash
OPENAI_API_KEY=your_api_key
```

---

## 📡 Endpoints

### Criar Transação

```http
POST /transactions
```

#### Request

```json
{
  "description": "Mercado",
  "amount": 150,
  "category": "GROCERIES"
}
```

#### Response

```json
{
  "description": "Mercado",
  "amount": 150,
  "category": "GROCERIES"
}
```

---

### Consultar por Categoria

```http
GET /transactions/{category}
```

#### Exemplo

```http
GET /transactions/GROCERIES
```

---

### Registrar Transação por Voz

```http
POST /transactions/ai
```

#### Content-Type

```text
multipart/form-data
```

#### Exemplo com curl

```bash
curl -X POST \
  -F "file=@audio.mp3" \
  http://localhost:8080/transactions/ai
```

---

## 🤖 Inteligência Artificial

### Fluxo de Processamento

```text
Áudio
 ↓
Whisper
 ↓
Transcrição
 ↓
GPT-4o Mini
 ↓
Tool Calling
 ↓
PersistTransactionUseCase
 ↓
MySQL
```

---

## 🔧 Tool Calling

Os casos de uso são expostos para a IA através de `@Tool`.

### PersistTransactionUseCase

Responsável por registrar transações.

### ListTransactionsByCategoryUseCase

Responsável por consultar transações por categoria.

A IA pode utilizar essas ferramentas automaticamente durante uma conversa.

---

## 📝 Prompt Engineering

O comportamento do assistente é definido em:

```text
src/main/resources/prompts/system-message.st
```

O prompt instrui o modelo a:

- Interpretar gastos financeiros.
- Extrair informações relevantes.
- Selecionar categorias válidas.
- Executar ferramentas da aplicação.
- Responder de forma objetiva.

---

## 🎙️ Exemplo de Uso

### Entrada por Voz

```text
Fui jantar no shopping e gastei 79 reais.
```

### Processo

```text
Whisper
↓
"Fui jantar no shopping e gastei 79 reais"

GPT-4o Mini
↓
Descrição: Jantar no shopping
Valor: 79
Categoria: FOOD

Tool Calling
↓
PersistTransactionUseCase
↓
MySQL
```

---

## 🎯 Objetivos de Aprendizado

Este projeto foi desenvolvido para explorar:

- Clean Architecture
- Spring Boot
- Spring AI
- OpenAI GPT-4o Mini
- OpenAI Whisper
- Tool Calling
- Docker Compose
- MySQL
- JPA/Hibernate
- APIs REST
- Prompt Engineering

---

**Ronney Rocha**

- GitHub: https://github.com/ronneyrv/api-ia
- LinkedIn: https://www.linkedin.com/in/ronney-rocha