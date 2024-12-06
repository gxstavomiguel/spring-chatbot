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


//import com.google.cloud.dialogflow.v2.*;
//import com.google.protobuf.Struct;
//import com.google.protobuf.Value;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ChatBotService {
//
//    private final String projectId = "seu-projeto-id-dialogflow";
//
//    public String processarMensagem(String mensagem) {
//        try (SessionsClient sessionsClient = SessionsClient.create()) {
//            String sessionId = "12345"; // Pode ser qualquer identificador único de sessão
//            SessionName session = SessionName.of(projectId, sessionId);
//
//            TextInput textInput = TextInput.newBuilder().setText(mensagem).setLanguageCode("pt-BR").build();
//            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
//
//            DetectIntentRequest request = DetectIntentRequest.newBuilder()
//                    .setSession(session.toString())
//                    .setQueryInput(queryInput)
//                    .build();
//
//            DetectIntentResponse response = sessionsClient.detectIntent(request);
//            QueryResult queryResult = response.getQueryResult();
//            return queryResult.getFulfillmentText();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Erro ao processar a mensagem.";
//        }
//    }
//}

