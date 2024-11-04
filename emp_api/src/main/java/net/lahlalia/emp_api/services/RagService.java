package net.lahlalia.emp_api.services;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class RagService {

    @Value("classpath:/prompts/prompt.st")
    private Resource promptResource;


    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagService(ChatClient.Builder builder, VectorStore vectorStore){
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }


    public String ask(String question){
        PromptTemplate promptTemplate = new PromptTemplate(promptResource);
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.query(question).withTopK(4)
        );
        List<String> context = documents.stream().map(d-> d.getContent()).toList();
        Prompt prompt = promptTemplate.create(Map.of("context", context,"question",question));
        return chatClient.prompt(prompt).call().content();
    }

//    public void addPdfFile(MultipartFile file) throws IOException{
//        try (var inputStream = file.getInputStream()) {
//            PagePdfDocumentReader documentReader = new PagePdfDocumentReader(inputStream);
//            List<Document> documents = documentReader.get();
//    }
}
