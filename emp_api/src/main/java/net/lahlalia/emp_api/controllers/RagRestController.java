package net.lahlalia.emp_api.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class RagRestController {
    private final ChatClient chatClient;
    @Value("classpath:/prompts/prompt.st")
    private Resource promptResource;
    private final VectorStore vectorStore;


    public RagRestController(ChatClient.Builder builder, VectorStore vectorStore){
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }

    @GetMapping("/")
    public String ask(String question){
        PromptTemplate promptTemplate = new PromptTemplate(promptResource);
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.query(question).withTopK(4)
        );
        List<String> context = documents.stream().map(d-> d.getContent()).toList();
        Prompt prompt = promptTemplate.create(Map.of("context", context,"question",question));
        return chatClient.prompt(prompt).call().content();
    }
}
