package net.lahlalia.emp_api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.PageResponse;
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

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PointageDto>> getAllPoints(){
        List<PointageDto> pointages = pointageService.getAllPoints();
        return ResponseEntity.ok(pointages);
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<PointageDto>> getAllPointages(
            @RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(pointageService.getAllPointage(page,size));
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointageDto> savePointage(@RequestBody @Valid PointageDto dto){
        PointageDto savedDto = pointageService.savePointage(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPoi}")
    public ResponseEntity<Boolean> deletePointage(@PathVariable Integer idPoi) {
        try {
            boolean deleted = pointageService.deletePointage(idPoi);
            if (deleted) {
                return ResponseEntity.ok(true); // Return true if deletion was successful
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false); // Return false if not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Handle any exceptions
        }
    }


    @PatchMapping(value ="/{idPoi}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateArchiveStatus(@PathVariable Integer idPoi){
        return ResponseEntity.ok(pointageService.updateArchivedStatus(idPoi));
    }
}
