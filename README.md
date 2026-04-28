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
    
    * ![Print das Issues](./issues.png.png)

---


## 📦 Entrega 3 - Processamento Comportamental e Governança de Dados

Nesta etapa, o foco foi a implementação da lógica de negócio para gestão de assiduidade e o sistema de reputação (honra) do paciente, garantindo a conformidade com a LGPD.

### 📝 Histórias de Usuário desta Entrega:

* ✅ **História de Usuário 8: Análise Comportamental e LGPD**
    * **O que foi feito:** Implementação do endpoint de registro de faltas (`@PatchMapping("/{id}/falta")`) utilizando identificadores anônimos para o processamento de dados.
    * [cite_start]**Valor gerado:** Garante a proteção de dados sensíveis em conformidade com a LGPD, permitindo que o modelo de IA futuro processe o histórico de *no-show* sem expor a identidade real do paciente[cite: 162].

* ✅ **História de Usuário 10: Sistema de Honra Dinâmica**
    * **O que foi feito:** Desenvolvimento da lógica de backend que integra o registro de faltas ao sistema de pontuação de "honra" do paciente.
    * [cite_start]**Valor gerado:** Fornece à clínica uma métrica clara para priorização de agendamentos e gestão de listas de espera, recompensando pacientes assíduos e identificando perfis de risco[cite: 162].

* 🎥 **SCREENCAST:** [Screencast no YouTube](https://youtu.be/q1ldCyb6_CQ?si=RB8ScZwr-eO7SpBS)

**ISSUES:**
<img width="977" height="107" alt="image" src="https://github.com/user-attachments/assets/088afd83-cdaf-4fa0-ada1-f3df16d79b68" />


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

## 💻 Stack Técnica Utilizada

| Tecnologia |
| :--- |
| **Java 17** |
| **Spring Boot 3.2.4** |
| **Maven** |
| **H2 Database** |
| **SpringDoc OpenAPI** |
| **Lombok** |**
