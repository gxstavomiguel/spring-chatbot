Chatbot - Projeto de Engenharia de Software 🤖
Descrição:
Este chatbot foi desenvolvido como parte da disciplina de Engenharia de Software. Ele utiliza expressões regulares para interpretar e responder a interações com base em um arquivo de respostas configurável (respostas.json ou similar).

✨ Funcionalidades
Realiza interações baseadas em padrões definidos no respostas.json.
Processa mensagens utilizando expressões regulares (regex).
Interface web para iniciar conversas.
Configuração simples para testes e demonstrações.
🛠️ Tecnologias Utilizadas
Back-end: Java com Spring Boot.
Front-end: HTML/CSS e JavaScript.
🔧 Ferramentas Utilizadas
ngrok: Para criar um túnel seguro e disponibilizar o servidor local publicamente.
IDE: IntelliJ IDEA e Visual Studio Code.
🚀 Como Executar o Projeto
Pré-requisitos
Java instalado (versão 8 ou superior).
Ferramenta ngrok configurada no sistema.
Passos
Clone este repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/chatbot-engenharia-software.git
Abra o projeto no IntelliJ IDEA.

Inicie a aplicação pela classe ChatbotApplication.java.

A aplicação estará disponível em:
plaintext
Copiar código
http://localhost:8080
Configure o ngrok:

Execute o comando no terminal:
bash
Copiar código
ngrok http 8080
Copie o link gerado (ex.: https://abcdefg.ngrok.io) para acessar a aplicação com suporte a SSL/TLS.
Interaja com o Chatbot:

Abra a aplicação no navegador.
Clique no botão "Vamos conversar".
Realize interações para testar a capacidade de regex e as respostas configuradas.
📂 Estrutura do Projeto
plaintext
Copiar código
src/
├── main/
│   ├── java/
│   │   └── com/projeto/chatbot/    # Classes do projeto
│   ├── resources/
│   │   ├── static/                # Arquivos HTML, CSS, JS
│   │   └── application.properties # Configurações do Spring Boot
├── test/                          # Testes automatizados (opcional)
respostas.json                     # Configuração de respostas do chatbot
📌 Melhorias Futuras
 Adicionar suporte a mais formatos de interação.
 Melhorar a interface gráfica para conversas.
 Integração com APIs externas para respostas mais dinâmicas.
🤝 Contribuições
Se você deseja contribuir para o projeto:

Faça um fork do repositório.
Crie uma branch para a sua funcionalidade:
bash
Copiar código
git checkout -b feature/nova-funcionalidade
Faça o commit das suas alterações:
bash
Copiar código
git commit -m "Adicionei a funcionalidade X"
Envie as alterações para o repositório remoto:
bash
Copiar código
git push origin feature/nova-funcionalidade
Abra um Pull Request.
📄 Licença
Este projeto está sob a licença MIT.
