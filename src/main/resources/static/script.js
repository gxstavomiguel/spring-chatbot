// document.getElementById("send-btn").addEventListener("click", enviarMensagem);
// document.getElementById("user-input").addEventListener("keypress", function (e) {
//     if (e.key === "Enter") {
//         enviarMensagem();
//     }
// });

// function enviarMensagem() {
//     const userInput = document.getElementById("user-input").value;
//     if (userInput.trim() === "") return;

//     adicionarMensagem("Você: " + userInput);
//     document.getElementById("user-input").value = "";

//     fetch("/api/chatbot/mensagem", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify({ mensagem: userInput }),
//     })
//         .then((response) => response.text())
//         .then((data) => adicionarMensagem("Bot: " + data))
//         .catch((error) => {
//             adicionarMensagem("Erro ao se comunicar com o bot.");
//             console.error(error);
//         });
// }

// function adicionarMensagem(mensagem) {
//     const chatBox = document.getElementById("chat-box");
//     const p = document.createElement("p");
//     p.textContent = mensagem;
//     chatBox.appendChild(p);
//     chatBox.scrollTop = chatBox.scrollHeight;
// }


// Função para enviar mensagem
document.getElementById("send-btn").addEventListener("click", enviarMensagem);
document.getElementById("user-input").addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
        enviarMensagem();
    }
});

function enviarMensagem() {
    const userInput = document.getElementById("user-input").value;
    if (userInput.trim() === "") return;

    // Adiciona mensagem do usuário
    adicionarMensagem(userInput, "user");
    document.getElementById("user-input").value = "";

    // Faz uma requisição ao servidor do chatbot
    fetch("/api/chatbot/mensagem", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ mensagem: userInput }),
    })
        .then((response) => response.text())
        .then((data) => {
            // Adiciona a resposta do bot
            adicionarMensagem(data, "bot");
        })
        .catch((error) => {
            adicionarMensagem("Erro ao se comunicar com o bot.", "bot");
            console.error(error);
        });
}

// Função para adicionar mensagens ao chat
function adicionarMensagem(mensagem, sender) {
    const chatBox = document.getElementById("chat-box");
    const messageDiv = document.createElement("div");

    messageDiv.classList.add("message"); // Classe base para todas as mensagens

    // Adiciona estilo específico do remetente
    if (sender === "user") {
        messageDiv.classList.add("user-message");
    } else {
        messageDiv.classList.add("bot-message");
    }

    // Define o texto da mensagem
    messageDiv.textContent = mensagem;

    // Adiciona a mensagem ao chat box
    chatBox.appendChild(messageDiv);

    // Rola o chat automaticamente para o final
    chatBox.scrollTop = chatBox.scrollHeight;
}

// ------------------------ Lógica do Carrossel ------------------------

// Seleciona elementos do carrossel
const wrapper = document.querySelector(".carousel-wrapper");
const dots = document.querySelectorAll(".dot");
let currentIndex = 0;
const slideCount = dots.length;

// Atualiza o carrossel e indicadores
function updateCarousel() {
    const offset = -currentIndex * 100; // Calcula a posição
    wrapper.style.transform = `translateX(${offset}%)`;

    dots.forEach((dot, index) => {
        dot.classList.toggle("active", index === currentIndex); // Atualiza indicador ativo
    });
}

// Avança para o próximo slide
function nextSlide() {
    currentIndex = (currentIndex + 1) % slideCount;
    updateCarousel();
}

// Volta para o slide anterior
function prevSlide() {
    currentIndex = (currentIndex - 1 + slideCount) % slideCount;
    updateCarousel();
}

// Vai para um slide específico
function goToSlide(index) {
    currentIndex = index;
    updateCarousel();
}

// Avança automaticamente a cada 5 segundos
setInterval(nextSlide, 5000);

// Inicializa o carrossel
updateCarousel();







