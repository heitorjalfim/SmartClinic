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
    * **O que foi feito:** Implementação e configuração do Swagger UI (OpenAPI) no ecossistema Spring Boot.
    * **Valor gerado:** Facilita o teste dos endpoints e permite que a equipe visualize todas as rotas disponíveis sem precisar ler o código-fonte.
    
* 🎥 **SCREENCAST:** [Screencast no YouTube](https://youtu.be/mi5y0C0S0eg)

    ISSUES:
    
    * ![Print das Issues](./issues.png.png)

---

## 📦 Entrega 3 - Arquitetura Isolada e Processamento Comportamental
 * ✅ *História de Usuário 10: Sistema de honra*
   * *O que foi feito:* Construção de uma variável que calcula a honra de um paciente, com base no historico de NO-SHOW.
   * *Valor gerado:* Garante que a clínica tenha uma visão geral do paciente que irá ser atendido e facilita o agendamento de consultas, com filas prioritárias para pacientes com honra alta.
 * ✅ *História de Usuário 8: Análise Comportamental e LGPD*
   * *O que foi feito:* Implementação da lógica de processamento do histórico de no-show dos últimos 12 meses utilizando identificadores anônimos para classificação de risco.
   * *Valor gerado:* Garante a proteção de dados sensíveis em conformidade com a LGPD, permitindo que o modelo reavalie o perfil do paciente a cada novo evento para refletir o comportamento mais recente.
 * 🎥 *SCREENCAST:* [Screencast no YouTube](https://youtu.be/q1ldCyb6_CQ?si=RB8ScZwr-eO7SpBS)


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

## 💻 Stack Técnica Utilizada

| Tecnologia |
| :--- |
| **Java 17** |
| **Spring Boot 3.2.4** |
| **Maven** |
| **H2 Database** |
| **SpringDoc OpenAPI** |
| **Lombok** |**
