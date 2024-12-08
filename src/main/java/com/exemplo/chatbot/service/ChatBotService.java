package com.exemplo.chatbot.service;

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
//        // Percorre as chaves do JSON
//        for (String chave : respostas.keySet()) {
//            // Cria uma regex para a palavra-chave
//            String regex = "\\b" + Pattern.quote(chave) + "\\b";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(mensagem);
//
//            // Verifica se a mensagem contém a palavra-chave
//            if (matcher.find()) {
//                return respostas.get(chave);
//            }
//        }
//
//        // Verifica palavras-chave mais genéricas
//        String[] palavrasImportantes = {"agendar", "marcar", "disponível", "horário", "carro", "modelo", "cor"};
//        for (String palavra : palavrasImportantes) {
//            String regex = "\\b" + Pattern.quote(palavra) + "\\b";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(mensagem);
//
//            if (matcher.find()) {
//                return "Parece que você quer falar sobre " + palavra + ". Pode reformular sua pergunta?";
//            }
//        }
//
//        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
//    }
//}







//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.lang.reflect.Type;
//import java.text.Normalizer;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ChatBotService {
//
//    private Map<String, String> respostas;
//    private Map<String, List<String>> sinonimos;
//
//    // Intenções com suas respectivas respostas ou lógica
//    private Map<String, String> intencoes;
//
//    public ChatBotService() {
//        this.respostas = new HashMap<>();
//        this.sinonimos = new HashMap<>();
//        this.intencoes = new HashMap<>();
//        carregarRespostas();
//        carregarSinonimos();
//        carregarIntencoes();
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
//    private void carregarSinonimos() {
//        this.sinonimos = Map.of(
//                "agendar", List.of("marcar", "programar"),
//                "carro", List.of("veículo", "automóvel"),
//                "horário", List.of("tempo", "agenda"),
//                "modelo", List.of("tipo", "design"),
//                "cor", List.of("tonalidade", "coloração")
//        );
//    }
//
//    private void carregarIntencoes() {
//        this.intencoes = Map.of(
//                "agendar_corrida", "Para agendar uma corrida, informe o horário, local e destino.",
//                "consultar_modelos", "Os modelos disponíveis são sedan, SUV e hatch.",
//                "informar_cor", "Qual é a cor do carro desejado?"
//        );
//    }
//
//    private String normalizarTexto(String texto) {
//        texto = texto.toLowerCase().trim();
//        texto = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
//        texto = texto.replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", " ");
//        return texto;
//    }
//
//    private List<String> gerarChavesComSinonimos(String mensagem) {
//        List<String> chavesExpandida = new ArrayList<>();
//        for (String palavra : mensagem.split(" ")) {
//            chavesExpandida.add(palavra);
//            if (sinonimos.containsKey(palavra)) {
//                chavesExpandida.addAll(sinonimos.get(palavra));
//            }
//        }
//        return chavesExpandida;
//    }
//
//    private String detectarIntencao(List<String> palavras) {
//        if (palavras.contains("agendar") || palavras.contains("marcar")) {
//            return "agendar_corrida";
//        } else if (palavras.contains("modelo") || palavras.contains("carro")) {
//            return "consultar_modelos";
//        } else if (palavras.contains("cor")) {
//            return "informar_cor";
//        }
//        return null; // Nenhuma intenção detectada
//    }
//
//    public String processarMensagem(String mensagem) {
//        mensagem = normalizarTexto(mensagem);
//        List<String> chavesUsuario = gerarChavesComSinonimos(mensagem);
//
//        String intencao = detectarIntencao(chavesUsuario);
//        if (intencao != null && intencoes.containsKey(intencao)) {
//            return intencoes.get(intencao);
//        }
//
//        for (String chave : respostas.keySet()) {
//            String regex = "\\b" + Pattern.quote(chave) + "\\b";
//            Pattern pattern = Pattern.compile(regex);
//            for (String palavra : chavesUsuario) {
//                Matcher matcher = pattern.matcher(palavra);
//                if (matcher.find()) {
//                    return respostas.get(chave);
//                }
//            }
//        }
//
//        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
//    }
//
//    public static void main(String[] args) {
//        ChatBotService chatBot = new ChatBotService();
//
//        System.out.println(chatBot.processarMensagem("Quero marcar um horário para corrida"));
//        System.out.println(chatBot.processarMensagem("Quais os modelos de carro disponíveis?"));
//        System.out.println(chatBot.processarMensagem("Que cor de carro posso escolher?"));
//        System.out.println(chatBot.processarMensagem("Posso fazer outra coisa?"));
//    }
//}





import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBotService {

    private Map<String, String> respostas;
    private static final String ARQUIVO_ENTRADAS_NAO_RECONHECIDAS = "src/main/resources/entradas.json";
    private static final String ARQUIVO_RESPOSTAS = "src/main/resources/respostas.json";

    public ChatBotService() {
        this.respostas = new HashMap<>();
        carregarRespostas();
    }

    private void carregarRespostas() {
        try (Reader reader = new FileReader(ARQUIVO_RESPOSTAS)) {

            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
            this.respostas = new Gson().fromJson(reader, tipoMapa);

            System.out.println("Respostas carregadas com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }

    public String processarMensagem(String mensagem) {
        mensagem = mensagem.toLowerCase();

        // Verifica palavras-chave no JSON
        for (String chave : respostas.keySet()) {
            String regex = "\\b" + Pattern.quote(chave) + "\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(mensagem);

            if (matcher.find()) {
                return respostas.get(chave);
            }
        }

        // Verifica palavras-chave genéricas
        String[] palavrasImportantes = {"agendar", "marcar", "disponível", "horário", "carro", "modelo", "cor"};
        for (String palavra : palavrasImportantes) {
            String regex = "\\b" + Pattern.quote(palavra) + "\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(mensagem);

            if (matcher.find()) {
                return "Parece que você quer falar sobre " + palavra + ". Pode reformular sua pergunta?";
            }
        }

        // Mensagem não reconhecida
        salvarMensagemNaoReconhecida(mensagem);
        return "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
    }

    private void salvarMensagemNaoReconhecida(String mensagem) {
        try {
            Path caminhoArquivo = Paths.get(ARQUIVO_ENTRADAS_NAO_RECONHECIDAS);
            List<String> mensagensNaoReconhecidas;

            // Cria o arquivo se ele não existir
            if (!Files.exists(caminhoArquivo)) {
                Files.createFile(caminhoArquivo);
                mensagensNaoReconhecidas = new ArrayList<>();
            } else {
                // Lê mensagens existentes
                try (Reader reader = new FileReader(caminhoArquivo.toFile())) {
                    Type listType = new TypeToken<List<String>>() {}.getType();
                    mensagensNaoReconhecidas = new Gson().fromJson(reader, listType);

                    if (mensagensNaoReconhecidas == null) {
                        mensagensNaoReconhecidas = new ArrayList<>();
                    }
                }
            }

            // Evita duplicatas
            if (!mensagensNaoReconhecidas.contains(mensagem)) {
                mensagensNaoReconhecidas.add(mensagem);

                // Salva no arquivo
                try (Writer writer = new FileWriter(caminhoArquivo.toFile())) {
                    new Gson().toJson(mensagensNaoReconhecidas, writer);
                }

                System.out.println("Mensagem não reconhecida salva: " + mensagem);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar a mensagem não reconhecida: " + e.getMessage());
        }
    }
}













