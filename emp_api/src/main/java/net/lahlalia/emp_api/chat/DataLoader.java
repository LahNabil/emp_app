package net.lahlalia.emp_api.chat;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader {

    @Value("classpath:/pdfs/*")
    private Resource[] pdfFiles;

    @Value("lahl-vs1.json")
    private String vectorStoreName;

    private JdbcClient jdbcClient;
    private VectorStore vectorStore;

    public DataLoader(JdbcClient jdbcClient, VectorStore vectorStore) {
        this.jdbcClient = jdbcClient;
        this.vectorStore = vectorStore;
    }
    public void addFilesToVectorStore(List<Path> newFiles) {
        List<Document> newDocuments = new ArrayList<>();
        for (Path pdfFilePath : newFiles) {
            try {
                Resource pdfFile = new FileSystemResource(pdfFilePath.toFile());
                PagePdfDocumentReader documentReader = new PagePdfDocumentReader(pdfFile);
                newDocuments.addAll(documentReader.get());
            } catch (Exception e) {
                log.error("Error reading PDF file: " + pdfFilePath.getFileName(), e);
            }
        }

        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> chunks = textSplitter.split(newDocuments);
        vectorStore.add(chunks);  // Ajoute uniquement les nouveaux fichiers
    }


    @PostConstruct
    public void initStore(){
        Integer count = jdbcClient.sql("select count(*) from vector_store")
                .query(Integer.class).single();
        if(count==0){
            List<Document> allDocuments = new ArrayList<>();
            for (Resource pdfFile : pdfFiles) {
                try {
                    PagePdfDocumentReader documentReader = new PagePdfDocumentReader(pdfFile);
                    allDocuments.addAll(documentReader.get());
                } catch (Exception e) {
                    log.error("Error reading PDF file: " + pdfFile.getFilename(), e);
                }
            }

            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> chunks = textSplitter.split(allDocuments);
            vectorStore.add(chunks);
            //simpleVectorStore.save(fileStore);
        }
    }

    //@Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        SimpleVectorStore simpleVectorStore = new SimpleVectorStore(embeddingModel);
        String path = Path.of("emp_api","src","main","resources","vectorstore").toFile().getAbsolutePath()+"/"+vectorStoreName;
        File fileStore = new File(path);
        if(fileStore.exists()){
            log.info("VectoreStore exist");
            simpleVectorStore.load(fileStore);
        }else{
            List<Document> allDocuments = new ArrayList<>();
            for (Resource pdfFile : pdfFiles) {
                try {
                    PagePdfDocumentReader documentReader = new PagePdfDocumentReader(pdfFile);
                    allDocuments.addAll(documentReader.get());
                } catch (Exception e) {
                    log.error("Error reading PDF file: " + pdfFile.getFilename(), e);
                }
            }

            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> chunks = textSplitter.split(allDocuments);
            simpleVectorStore.add(chunks);
            simpleVectorStore.save(fileStore);

//            PagePdfDocumentReader documentReader = new PagePdfDocumentReader(pdfFile);
//            List<Document> documents = documentReader.get();
//            TextSplitter textSplitter = new TokenTextSplitter();
//            List<Document> chunks = textSplitter.split(documents);
//            simpleVectorStore.add(chunks);
//            simpleVectorStore.save(fileStore);
        }
        return simpleVectorStore;
    }

}
