package com.exemplo.chatbot.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.UUID;

public class ChatBotService {

    private Map<String, String> respostas;
    private List<Map<String, Object>> conversas;
    private UUID idConversaAtual;
    private static final String ARQUIVO_RESPOSTAS = "src/main/resources/respostas.json";
    private static final String ARQUIVO_CONVERSAS = "src/main/resources/conversas.json";
    private static final String ARQUIVO_ENTRADAS_NAO_RECONHECIDAS = "src/main/resources/entradas.json";

    public ChatBotService() {
        this.respostas = new HashMap<>();
        this.conversas = new ArrayList<>();
        this.idConversaAtual = UUID.randomUUID();
        carregarRespostas();
        carregarConversas();
    }

    private void carregarRespostas() {
        try (Reader reader = new FileReader(ARQUIVO_RESPOSTAS)) {
            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
            this.respostas = new Gson().fromJson(reader, tipoMapa);
            System.out.println("Respostas carregadas com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo JSON de respostas: " + e.getMessage());
        }
    }

    private void carregarConversas() {
        try {
            Path caminhoArquivo = Paths.get(ARQUIVO_CONVERSAS);
            if (Files.exists(caminhoArquivo)) {
                try (Reader reader = new FileReader(caminhoArquivo.toFile())) {
                    Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                    this.conversas = new Gson().fromJson(reader, listType);

                    if (this.conversas == null) {
                        this.conversas = new ArrayList<>();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o histórico de conversas: " + e.getMessage());
        }
    }

    public String processarMensagem(String mensagem) {
        mensagem = normalizarTexto(mensagem.trim().toLowerCase());

        for (String chave : respostas.keySet()) {
            String regex = "\\b" + Pattern.quote(chave) + "\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(mensagem);

            if (matcher.find()) {
                String resposta = respostas.get(chave);
                salvarInteracao(mensagem, resposta);
                return resposta;
            }
        }

        String[] palavrasImportantes = {"agendar", "marcar", "disponível", "horário", "carro", "modelo", "cor"};
        for (String palavra : palavrasImportantes) {
            String regex = "\\b" + Pattern.quote(palavra) + "\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(mensagem);

            if (matcher.find()) {
                String resposta = "Parece que você quer falar sobre " + palavra + ". Pode reformular sua pergunta?";
                salvarInteracao(mensagem, resposta);
                return resposta;
            }
        }

        String resposta = "Desculpe, não entendi sua mensagem. Por favor, tente perguntar de outra forma.";
        salvarMensagemNaoReconhecida(mensagem);
        salvarInteracao(mensagem, resposta);
        return resposta;
    }

    private String normalizarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return texto.replaceAll("[^\\p{ASCII}]", "");
    }

    private void salvarMensagemNaoReconhecida(String mensagem) {
        try {
            Path caminhoArquivo = Paths.get(ARQUIVO_ENTRADAS_NAO_RECONHECIDAS);
            List<String> mensagensNaoReconhecidas;

            if (!Files.exists(caminhoArquivo)) {
                Files.createFile(caminhoArquivo);
                mensagensNaoReconhecidas = new ArrayList<>();
            } else {
                try (Reader reader = new FileReader(caminhoArquivo.toFile())) {
                    Type listType = new TypeToken<List<String>>() {}.getType();
                    mensagensNaoReconhecidas = new Gson().fromJson(reader, listType);

                    if (mensagensNaoReconhecidas == null) {
                        mensagensNaoReconhecidas = new ArrayList<>();
                    }
                }
            }

            if (!mensagensNaoReconhecidas.contains(mensagem)) {
                mensagensNaoReconhecidas.add(mensagem);
                try (Writer writer = new FileWriter(caminhoArquivo.toFile())) {
                    new Gson().toJson(mensagensNaoReconhecidas, writer);
                }
                System.out.println("Mensagem não reconhecida salva: " + mensagem);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar a mensagem não reconhecida: " + e.getMessage());
        }
    }

    private void salvarInteracao(String mensagemUsuario, String respostaBot) {
        String dataHoraAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> interacao = new HashMap<>();
        interacao.put("usuario", mensagemUsuario);
        interacao.put("bot", respostaBot);
        interacao.put("data_hora", dataHoraAtual);

        Optional<Map<String, Object>> conversaAtual = conversas.stream()
                .filter(conversa -> conversa.get("id").equals(idConversaAtual.toString()))
                .findFirst();

        if (conversaAtual.isPresent()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> mensagens = (List<Map<String, String>>) conversaAtual.get().get("mensagens");
            mensagens.add(interacao);
        } else {
            Map<String, Object> novaConversa = new HashMap<>();
            novaConversa.put("id", idConversaAtual.toString());
            List<Map<String, String>> mensagens = new ArrayList<>();
            mensagens.add(interacao);
            novaConversa.put("mensagens", mensagens);
            conversas.add(novaConversa);
        }
        salvarConversas();
    }

    private void salvarConversas() {
        try (Writer writer = new FileWriter(ARQUIVO_CONVERSAS)) {
            new Gson().toJson(conversas, writer);
            System.out.println("Conversas salvas com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar as conversas: " + e.getMessage());
        }
    }
}
