# Meets-praesens

> **Status:** 🚀 Entrega 2 Concluída | API & Banco de Dados Integrados

Sistema para gestão de presença e monitoramento de agendamentos, desenvolvido com foco em arquitetura robusta e documentação clara.

---

## 📦 Entrega 1 - Planejamento e Design

*Fase inicial de levantamento de requisitos e experiência do usuário.*

* 🎨 **Protótipo Figma:** [Acesse o Layout Lo-Fi](https://www.figma.com/design/m0ydw02k0IDxkrQRFGxCNl/Prot%C3%B3tipo-lo-fi?node-id=0-1&t=SOFvXCTqBXRa8m4o-1)
* 🎥 **SCREENCAST:** [Screencast no YouTube](https://youtu.be/ml-BAICZe3Q?si=H90N-P6QnZ3pDnVu)
* 📄 **HISTÓRIAS DE USUÁRIO:** [Documento de Histórias de Usuário](https://docs.google.com/document/d/1ocbSXWxGoyLV9Z2__usxiw_ioFY_NesL6qB4kEL5eBo/edit?tab=t.0)

---

## 📦 Entrega 2 - Implementação Backend

Nesta etapa, o foco foi a construção da infraestrutura técnica e a persistência inicial dos dados.

### 📝 Histórias de Usuário desta Entrega:

* ✅ **História de Usuário 1: Estrutura de Dados e Persistência**
    * **O que foi feito:** Criação do modelo de dados utilizando JPA/Hibernate e configuração do repositório para as classes principais (Paciente/Usuário).
    * **Valor gerado:** Garante que as informações do sistema sejam salvas e organizadas de forma correta no banco de dados.

* ✅ **História de Usuário 3: Documentação Interativa da API**
    * O que foi feito:** Implementação e configuração do Swagger UI (OpenAPI) no ecossistema Spring Boot.
    * Valor gerado:** Facilita o teste dos endpoints e permite que a equipe visualize todas as rotas disponíveis sem precisar ler o código-fonte.
    
* 🎥 **SCREENCAST:** [Screencast no YouTube](https://youtu.be/mi5y0C0S0eg)

    ISSUES:
    
    <img width="981" height="105" alt="image" src="https://github.com/user-attachments/assets/0a6cf505-362a-4918-b820-e5b24e67e246" />


---


# Entrega 3 

## 📝 Histórias de Usuário Implementadas

### ✅ História de Usuário 7: Arquitetura de Camadas e Regras de Negócio
* **O que foi feito:** Organização do projeto utilizando o padrão de camadas (Controller, Service e Repository). O `AgendamentoController` gerencia as requisições, enquanto o `AgendamentoService` isola a lógica de negócio para o processamento de faltas.
* **Valor gerado:** Garante um código limpo, fácil de manter e escalável, separando a exposição da API da lógica de processamento interna.

### ✅ História de Usuário 8: Registro de Absenteísmo (Faltas) e LGPD
* **O que foi feito:** Implementação do endpoint `PATCH /{id}/falta` para registro de faltas de pacientes. O sistema foi estruturado para focar em identificadores anônimos, garantindo a privacidade dos dados sensíveis conforme as diretrizes da LGPD.
* **Valor gerado:** Permite o controle de presença necessário para alimentar o modelo de inteligência preditiva sem expor dados pessoais dos pacientes.

---

## 🛠️ Issues do Projeto 
<img width="980" height="101" alt="image" src="https://github.com/user-attachments/assets/f4b7bfd0-0b12-4161-a6f8-680399f56c9b" />


---

## 🎥 Screencast (Demonstração)
* **Link do Vídeo:** (https://youtu.be/q1ldCyb6_CQ?si=RB8ScZwr-eO7SpBS)
* *Nota: O vídeo demonstra a estrutura do projeto e os endpoints mapeados no Swagger, validando a integração entre Controller e Service.*

---

## Entrega 4

### 📝 Histórias de Usuário Implementadas

✅ **História de Usuário 9: Cálculo de Risco Configurável e Rastreável**
* **O que foi feito:** Refatoração do `RiscoService` para consumir os pesos do algoritmo (clima, trânsito, histórico de no-show) diretamente do arquivo `application.properties`. Foi adicionada uma proteção matemática contra divisão por zero para pacientes sem histórico, e o DTO de resposta agora expõe o objeto `detalhesPesos`.
* **Valor gerado:** Permite que a gestão da clínica ajuste o comportamento da inteligência preditiva em tempo real, sem necessidade de recompilar o código, além de garantir total auditoria e transparência nas decisões do sistema.
* 🔍 **Como testar localmente:** Com o sistema rodando, acesse no navegador: 
  `http://localhost:8080/api/risco/1` *(Observe o campo "detalhesPesos" no JSON de retorno)*.

✅ **História de Usuário 12: Métrica de Chair Utilization (Taxa de Ocupação)**
* **O que foi feito:** Implementação da lógica matemática no `AgendamentoService` para calcular o percentual de ocupação diária das salas. Essa métrica foi acoplada ao endpoint `GET /api/agendamentos/disponibilidade`, retornando dinamicamente a chave `chairUtilization`.
* **Valor gerado:** Fornece um indicador de eficiência operacional em tempo real, permitindo que a gestão monitore a ociosidade da clínica e tome decisões baseadas em dados (como encaixes ou campanhas).
`http://localhost:8080/api/agendamentos/disponibilidade?data=2026-05-19` *(Substitua pela data do agendamento de teste e observe o percentual atualizado)*.




---

### 🛠️ Issues do Projeto



---

### 🎥 Screencast (Demonstração)

* **Link do Vídeo:** ([Insira_o_link_do_YouTube_Aqui](https://youtu.be/...))
* *Nota: O vídeo demonstra o consumo dos pesos dinâmicos na API de Risco (H9), o cálculo em tempo real da ocupação no endpoint de disponibilidade (H12) e a execução do ambiente via Docker, conforme documentado no README.*

---

## 🟣 Persistência de Dados (H2 Database)

*Para esta entrega, utilizamos o banco de dados H2, que opera em modo in-memory.*

* **Vantagem:** Permite rodar o projeto instantaneamente sem configurações complexas de servidores SQL externos.
* **Acesso Visual:** O console do banco fica disponível enquanto a aplicação está rodando.

---

## 🛠️ Como Executar e Validar

1.  **Subir o Servidor:**
    ```bash
    mvn spring-boot:run
    ```
2.  **Validar História 3 (Swagger):** Acesse no navegador: `http://localhost:8080/swagger-ui/index.html`
3.  **Validar História 1 (Banco de Dados):** Acesse o console: `http://localhost:8080/h2-console`

---

# Documentação de Instalação e Execução — Praesens Meets Intelligence

## 🚀 Guia de Configuração e Setup do Ambiente Local (Entrega 4)

Este guia permite que qualquer pessoa — seja um avaliador ou outro desenvolvedor — consiga clonar o repositório, provisionar a infraestrutura de dados e executar o projeto localmente em menos de 3 minutos, eliminando o problema do *"na minha máquina funciona"*.

### 1) Pré-requisitos Mínimos
Antes de começar, certifique-se de ter instalado em sua máquina:
* **Git** (v2.30 ou superior)
* **Docker e Docker Compose** (Essencial para a orquestração da infraestrutura de dados)
* **Java JDK 17**
* **Maven 3.9+** (Opcional, pois o projeto conta com o Maven Wrapper)

### 2) Clonar o Repositório e Acessar a Pasta
Abra o seu terminal e execute os comandos abaixo para baixar o código e entrar no diretório raiz do projeto:
``bash
git clone https://github.com/ENZOBRS/Praesens-Meets-Inteligence.git
cd Praesens-Meets-Inteligence

🛠️ 3) Orquestração da Infraestrutura (Banco de Dados via Docker)
Para simplificar o setup, o banco de dados relacional PostgreSQL está totalmente containerizado. Você não precisa instalar ou configurar um servidor de banco de dados localmente na sua máquina. O Docker trata de baixar a imagem oficial, expor a porta padrão "hdj251106" e criar a base de dados praesens_db automaticamente.

Na raiz do projeto (onde está o arquivo docker-compose.yml), execute o comando para subir o banco em segundo plano:

Bash
docker-compose up -d
4) Build e Execução da Aplicação (Spring Boot)
Com o banco de dados de pé e rodando no Docker, use os comandos abaixo para baixar as dependências do Java e iniciar o servidor web:

No Linux / Mac:
Bash
./mvnw clean install
./mvnw spring-boot:run
No Windows:
Bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
(Nota: Se você tiver o Maven instalado globalmente nas variáveis de ambiente, pode substituir por mvn clean install e mvn spring-boot:run).

A aplicação fará o build, executará as migrações automáticas de tabelas via Hibernate JPA (ddl-auto=update) e subirá por padrão no endereço:
👉 http://localhost:8080
---



---

## 💻 Stack Técnica Utilizada

| Tecnologia |
| :--- |
| **Java 17** |
| **Spring Boot 3.2.4** |
| **Maven** |
| **H2 Database** |
| **SpringDoc OpenAPI** |
| **Lombok** |**
