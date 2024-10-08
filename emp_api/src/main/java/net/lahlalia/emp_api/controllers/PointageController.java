package net.lahlalia.emp_api.controllers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.PointageDto;
import net.lahlalia.emp_api.services.PointageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pointage")
public class PointageController {

    private final PointageService pointageService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PointageDto>> getAllPointages(){
        List<PointageDto> pointages = pointageService.getAllPointage();
        return ResponseEntity.ok(pointages);
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointageDto> savePointage(@RequestBody PointageDto dto){
        PointageDto savedDto = pointageService.savePointage(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}
