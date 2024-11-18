package com.exemplo.chatbot.service;

import java.util.HashMap;
import java.util.Map;

public class ChatBotService {

    private final Map<String, String> respostas;

    public ChatBotService() {
        this.respostas = new HashMap<>();
        inicializarRespostas();
    }

    private void inicializarRespostas() {
        respostas.put("olá", "Olá! Como posso ajudar você?");
        respostas.put("tchau", "Até logo! Tenha um bom dia!");
        respostas.put("como você está", "Eu sou um bot, estou sempre bem!");
        respostas.put("ajuda", "Estou aqui para ajudar! O que você precisa?");
    }

    public String processarMensagem(String mensagem) {
        mensagem = mensagem.toLowerCase();
        for (String chave : respostas.keySet()) {
            if (mensagem.contains(chave)) {
                return respostas.get(chave);
            }
        }
        return "Desculpe, não entendi sua mensagem.";
    }
}
