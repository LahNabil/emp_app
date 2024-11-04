package net.lahlalia.emp_api.services;

import net.lahlalia.emp_api.chat.DataLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    private final DataLoader dataLoader;

    public FileService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public String store(MultipartFile file) throws IOException {
        Path path = Paths.get("emp_api","src","main","resources","pdfs");
        if (!Files.exists(path)) {
            throw new RuntimeException("Le dossier de destination n'existe pas : " + path.toAbsolutePath());
        }
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get("emp_api","src","main","resources","pdfs",fileId+".pdf");
        Files.copy(file.getInputStream(), filePath);

        dataLoader.initStore();

        return fileId;
    }

//
//    public String uploadFile(MultipartFile file) throws IOException {
//        // Check if the uploaded file is a PDF
//        if (!"application/pdf".equals(file.getContentType())) {
//            throw new IllegalArgumentException("Only PDF files are allowed.");
//        }
//
//        // Create the full path for the new file
//        Path path = Paths.get(uploadDir, file.getOriginalFilename());
//
//        // Ensure the parent directory exists
//        Files.createDirectories(path.getParent());
//
//        // Transfer the uploaded file to the specified path
//        file.transferTo(path);
//
//        return "File uploaded successfully: " + file.getOriginalFilename();
//    }
}
