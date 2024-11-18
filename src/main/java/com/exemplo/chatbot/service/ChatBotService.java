package com.exemplo.chatbot.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ChatBotService {

    private Map<String, String> respostas;

    public ChatBotService() {
        this.respostas = new HashMap<>();
        carregarRespostas();
    }

    private void carregarRespostas() {
        try (Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("respostas.json"))) {

            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
            this.respostas = new Gson().fromJson(reader, tipoMapa);

            System.out.println("Respostas carregadas com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }

    public String processarMensagem(String mensagem) {
        mensagem = mensagem.toLowerCase();

        for (String chave : respostas.keySet()) {
            if (mensagem.contains(chave)) {
                return respostas.get(chave);
            }
        }
        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
    }
}
