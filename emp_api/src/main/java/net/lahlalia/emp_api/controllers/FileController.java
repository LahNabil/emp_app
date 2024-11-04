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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> saveFiles(@RequestParam List<MultipartFile> files) throws IOException {
        List<String> fileIds = fileService.store(files);
        return ResponseEntity.ok(fileIds);
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
