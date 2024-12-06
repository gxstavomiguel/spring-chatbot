package com.exemplo.chatbot.service;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ChatBotService {
//
//
//    private Map<String, String> respostas;
//
//    public ChatBotService() {
//        this.respostas = new HashMap<>();
//        carregarRespostas();
//    }
//
//    private void carregarRespostas() {
//        try (Reader reader = new InputStreamReader(
//                getClass().getClassLoader().getResourceAsStream("respostas.json"))) {
//
//            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
//            this.respostas = new Gson().fromJson(reader, tipoMapa);
//
//            System.out.println("Respostas carregadas com sucesso!");
//
//        } catch (Exception e) {
//            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
//        }
//    }
//
//    public String processarMensagem(String mensagem) {
//        mensagem = mensagem.toLowerCase();
//
//        for (String chave : respostas.keySet()) {
//            if (mensagem.contains(chave)) {
//                return respostas.get(chave);
//            }
//        }
//        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
//    }
//}




//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ChatBotService {
//
//    private Map<String, String> respostas;
//
//    public ChatBotService() {
//        this.respostas = new HashMap<>();
//        carregarRespostas();
//    }
//
//    private void carregarRespostas() {
//        try (Reader reader = new InputStreamReader(
//                getClass().getClassLoader().getResourceAsStream("respostas.json"))) {
//
//            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
//            this.respostas = new Gson().fromJson(reader, tipoMapa);
//
//            System.out.println("Respostas carregadas com sucesso!");
//
//        } catch (Exception e) {
//            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
//        }
//    }
//
//    public String processarMensagem(String mensagem) {
//        mensagem = mensagem.toLowerCase();
//
//        // Usando expressões regulares para detectar palavras-chave
//        for (String chave : respostas.keySet()) {
//            // Cria um padrão regex para procurar por qualquer variação da chave (usando palavra-chave como regex)
//            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(chave) + "\\b", Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(mensagem);
//
//            if (matcher.find()) {
//                return respostas.get(chave);
//            }
//        }
//        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
//    }
//}



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        mensagem = mensagem.toLowerCase().trim(); // Remove espaços extras e converte para minúsculas

        // Usando expressões regulares para detectar palavras-chave de forma mais flexível
        for (String chave : respostas.keySet()) {
            // Cria um padrão regex que trata variações, ignora pontuação e múltiplos espaços
            String regex = "\\b" + Pattern.quote(chave) + "\\b";
            // Substituir pontuação comum que pode ocorrer nas perguntas
            regex = regex.replaceAll("[?!.]", ""); // Remove sinais de pontuação

            // Expressão regular para lidar com múltiplos espaços
            regex = regex.replaceAll("\\s+", "\\\\s*"); // Substitui múltiplos espaços por uma correspondência flexível

            // Compila o padrão para ser insensível a maiúsculas e minúsculas
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(mensagem);

            if (matcher.find()) {
                return respostas.get(chave);
            }
        }
        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
    }
}



