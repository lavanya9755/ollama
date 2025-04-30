
package com.local.ollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OllamaController {

    private ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel){
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/")  
public ResponseEntity<String> getAnswer(@RequestParam String message) { //dont use path variable 
    ChatResponse chatResponse = chatClient
            .prompt(message)
            .call()
            .chatResponse();

    System.out.println(chatResponse.getMetadata().getModel());

    String response = chatResponse.getResult().getOutput().getText();

    return ResponseEntity.ok(response);
}


}