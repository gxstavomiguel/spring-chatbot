# Chatbot - Projeto de Engenharia de Software 🤖

**Descrição:**  
Este chatbot foi desenvolvido como parte da disciplina de **Engenharia de Software**. Ele utiliza expressões regulares e frases prontas para responder a interações do usuário com base em um arquivo de respostas json (`respostas.json`).

---

## ✨ Funcionalidades

- Realiza interações baseadas em padrões definidos no `respostas.json`.  
- Processa mensagens utilizando expressões regulares (regex).  
- Interface web para iniciar conversas.  
- Configuração simples para testes e demonstrações.

---

## 🛠️ Tecnologias Utilizadas

- **Back-end:** Java com Spring Boot.  
- **Front-end:** HTML/CSS e JavaScript.  

---

## 🔧 Ferramentas Utilizadas

- **ngrok:** Para criar um túnel seguro e disponibilizar o servidor local publicamente.  
- **IDE:** IntelliJ IDEA e Visual Studio Code.  

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java instalado (versão 8 ou superior).  
- Ferramenta ngrok configurada no sistema.  

### Passos

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/seu-usuario/chatbot-engenharia-software.git
2. **Abra o projeto no IntelliJ IDEA.**
   ```bash

3. **Inicie a aplicação pela classe ChatbotApplication.java.**
   ```bash
   A aplicação estará disponível em:
   http://localhost:8080

4. **Configure o ngrok:**
   ```bash
   Execute o comando no terminal:
   ngrok http 8080
   Copie o link gerado (ex.: https://abcdefg.ngrok.io) para acessar a aplicação com suporte a SSL/TLS.

5. **Interaja com o Chatbot:**
   ```bash
   Abra a aplicação no navegador.
   Clique no botão "Vamos conversar".
   Realize interações para testar a capacidade de regex e as respostas configuradas.
