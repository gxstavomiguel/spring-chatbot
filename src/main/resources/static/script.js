document.getElementById("send-btn").addEventListener("click", enviarMensagem);
document.getElementById("user-input").addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
        enviarMensagem();
    }
});

function enviarMensagem() {
    const userInput = document.getElementById("user-input").value;
    if (userInput.trim() === "") return;

    adicionarMensagem(userInput, "user");
    document.getElementById("user-input").value = "";

    fetch("/api/chatbot/mensagem", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ mensagem: userInput }),
    })
        .then((response) => response.text())
        .then((data) => {
            adicionarMensagem(data, "bot");
        })
        .catch((error) => {
            adicionarMensagem("Erro ao se comunicar com o bot.", "bot");
            console.error(error);
        });
}

function adicionarMensagem(mensagem, sender) {
    const chatBox = document.getElementById("chat-box");
    const messageDiv = document.createElement("div");

    messageDiv.classList.add("message");

    if (sender === "user") {
        messageDiv.classList.add("user-message");
    } else {
        messageDiv.classList.add("bot-message");
    }

    messageDiv.textContent = mensagem;
    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

const wrapper = document.querySelector(".carousel-wrapper");
const dots = document.querySelectorAll(".dot");
let currentIndex = 0;
const slideCount = dots.length;

function updateCarousel() {
    const offset = -currentIndex * 100; // Calcula a posição
    wrapper.style.transform = `translateX(${offset}%)`;

    dots.forEach((dot, index) => {
        dot.classList.toggle("active", index === currentIndex); // Atualiza indicador ativo
    });
}

function nextSlide() {
    currentIndex = (currentIndex + 1) % slideCount;
    updateCarousel();
}

function prevSlide() {
    currentIndex = (currentIndex - 1 + slideCount) % slideCount;
    updateCarousel();
}

function goToSlide(index) {
    currentIndex = index;
    updateCarousel();
}

setInterval(nextSlide, 5000);

updateCarousel();









