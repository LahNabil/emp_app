package net.lahlalia.emp_api.controllers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.QuestionResponse;
import net.lahlalia.emp_api.services.RagService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class RagRestController {

    private final RagService ragService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponse> ask(@RequestParam String question){
        String response = ragService.ask(question);
        QuestionResponse questionResponse = new QuestionResponse(response);
        // QuestionResponse response =  ragService.ask(question);
        return ResponseEntity.ok(questionResponse);
    }
}
