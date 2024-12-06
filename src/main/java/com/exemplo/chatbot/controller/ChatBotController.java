package com.exemplo.chatbot.controller;

import com.exemplo.chatbot.service.ChatBotService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    public ChatBotController() {
        this.chatBotService = new ChatBotService();
    }

    @PostMapping("/mensagem")
    public String receberMensagem(@RequestBody Map<String, String> payload) {
        String mensagem = payload.get("mensagem");
        return chatBotService.processarMensagem(mensagem);
    }
}

//import com.exemplo.chatbot.service.ChatBotService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/chatbot")
//public class ChatBotController {
//
//    private final ChatBotService chatBotService;
//
//    public ChatBotController() {
//        this.chatBotService = new ChatBotService();
//    }
//
//    @PostMapping("/mensagem")
//    public String receberMensagem(@RequestBody Map<String, String> payload) {
//        String mensagem = payload.get("mensagem");
//        return chatBotService.processarMensagem(mensagem);
//    }
//}

