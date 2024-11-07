package net.lahlalia.emp_api.services;

import net.lahlalia.emp_api.chat.DataLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileService {

    private final DataLoader dataLoader;

    public FileService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public List<String> store(List<MultipartFile> files) throws IOException {
        Path path = Paths.get("emp_api", "src", "main", "resources", "pdfs");
        if (!Files.exists(path)) {
            throw new RuntimeException("Le dossier de destination n'existe pas : " + path.toAbsolutePath());
        }

        List<String> fileNames = new ArrayList<>();
        List<Path> newFilePaths = new ArrayList<>();
        for (MultipartFile file : files) {
//            String fileId = UUID.randomUUID().toString();
//            Path filePath = path.resolve(fileId + ".pdf");
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                throw new RuntimeException("Nom de fichier non valide pour un des fichiers");
            }
            String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            Path filePath = path.resolve(sanitizedFileName);
            // Vérifier si le fichier existe déjà pour éviter de le remplacer
            if (Files.exists(filePath)) {
                sanitizedFileName = System.currentTimeMillis() + "_" + sanitizedFileName;
                filePath = path.resolve(sanitizedFileName);
            }

            Files.copy(file.getInputStream(), filePath);
            fileNames.add(sanitizedFileName);
            newFilePaths.add(filePath); // Ajoute le chemin du nouveau fichier pour traitement
        }

        // Appelle la méthode pour ajouter uniquement les nouveaux fichiers
        dataLoader.addFilesToVectorStore(newFilePaths);

        return fileNames;
    }

    public List<Map<String, Object>> listFilesInDirectory() throws IOException {
        Path path = Paths.get("emp_api", "src", "main", "resources", "pdfs");
        if (!Files.exists(path)) {
            throw new RuntimeException("Le dossier source n'existe pas : " + path.toAbsolutePath());
        }

        List<Map<String, Object>> fileList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.pdf")) {
            for (Path file : stream) {
                Map<String, Object> fileInfo = new HashMap<>();
                fileInfo.put("name", file.getFileName().toString());
                fileInfo.put("size", Files.size(file));
                fileInfo.put("creationTime", Files.getAttribute(file, "creationTime").toString()); // Converti en chaîne
                fileInfo.put("lastModifiedTime", Files.getLastModifiedTime(file).toString()); // Converti en chaîne
                fileInfo.put("path", file.toAbsolutePath().toString());

                fileList.add(fileInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'accès aux fichiers PDF", e);
        }

        return fileList;
    }

    public boolean deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get("emp_api", "src", "main", "resources", "pdfs", fileName);
        boolean fileDeleted = false;
        if (Files.exists(filePath)) {
            return Files.deleteIfExists(filePath);
        } else {
            throw new RuntimeException("Le fichier n'existe pas : " + fileName);
        }
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
