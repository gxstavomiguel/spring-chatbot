document.getElementById("send-btn").addEventListener("click", enviarMensagem);
document.getElementById("user-input").addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
        enviarMensagem();
    }
});

function enviarMensagem() {
    const userInput = document.getElementById("user-input").value;
    if (userInput.trim() === "") return;

    adicionarMensagem("Você: " + userInput);
    document.getElementById("user-input").value = "";

    fetch("/api/chatbot/mensagem", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ mensagem: userInput }),
    })
        .then((response) => response.text())
        .then((data) => adicionarMensagem("Bot: " + data))
        .catch((error) => {
            adicionarMensagem("Erro ao se comunicar com o bot.");
            console.error(error);
        });
}

function adicionarMensagem(mensagem) {
    const chatBox = document.getElementById("chat-box");
    const p = document.createElement("p");
    p.textContent = mensagem;
    chatBox.appendChild(p);
    chatBox.scrollTop = chatBox.scrollHeight;
}
