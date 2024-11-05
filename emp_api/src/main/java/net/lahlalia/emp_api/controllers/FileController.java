package net.lahlalia.emp_api.controllers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> saveFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<String> fileIds = fileService.store(files);
        return ResponseEntity.ok(fileIds);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listFiles() {
        try {
            List<Map<String, Object>> fileList = fileService.listFilesInDirectory();
            return ResponseEntity.ok(fileList); // Renvoie un statut 200 avec la liste des fichiers
        } catch (IOException e) {
            // En cas d'erreur, retourne une réponse avec un message d'erreur et un statut 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la lecture des fichiers PDF : " + e.getMessage());
        }
    }
    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            boolean deleted = fileService.deleteFile(fileName);
            if (deleted) {
                return ResponseEntity.ok("Fichier supprimé avec succès : " + fileName);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Le fichier n'a pas pu être supprimé : " + fileName);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du fichier : " + e.getMessage());
        }
    }

//    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<List<String>> saveFile(@RequestParam List<MultipartFile> files) {
//        try {
//            <List<String> fileIds = fileService.store(files);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body("Fichier enregistré avec succès. ID: " + fileId);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Erreur: " + e.getMessage());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur lors de l'enregistrement du fichier: " + e.getMessage());
//        }
//    }

}
