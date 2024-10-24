package net.lahlalia.emp_api.controllers;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.DepartementDto;
import net.lahlalia.emp_api.dtos.EmployeDto;
import net.lahlalia.emp_api.dtos.PageResponse;
import net.lahlalia.emp_api.services.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departement")
public class DepartementController {

    private final DepartementService departementService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartementDto>> getAllDeps(){
        List<DepartementDto> dtos = departementService.getAllDeps();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping(value = "/employes/{idDep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeDto>> getEmployeByDep(@PathVariable Integer idDep){
        List<EmployeDto> dtos = departementService.getEmployesByDepartement(idDep);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<DepartementDto>> getAllDepartements(
            @RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(departementService.getAllDepartements(page,size));
    }
    @GetMapping(value = "/{idDep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartementDto>getDepById(@PathVariable Integer idDep){
        DepartementDto departementDto =departementService.getDepartementById(idDep);
        return ResponseEntity.ok(departementDto);
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartementDto> saveProduct(@RequestBody @Valid DepartementDto dto){
        DepartementDto savedDep = departementService.saveDepartement(dto);
        return new ResponseEntity<>(savedDep, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartementDto>updateDepartement(@PathVariable Integer id,@RequestBody @Valid DepartementDto dto) throws EntityNotFoundException {
        DepartementDto updatedDep = departementService.updateDepartement(id,dto);
        return ResponseEntity.ok(updatedDep);
    }
    @PatchMapping(value ="/{idDep}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateArchiveStatus(@PathVariable Integer idDep){
        return ResponseEntity.ok(departementService.updateArchivedStatus(idDep));
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartement(@PathVariable Integer id) {
        try {
            Boolean isDeleted = departementService.deleteDepartement(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build();  // Return 204 No Content if deletion is successful
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);  // Return 500 if something went wrong
            }
        } catch (DepartementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);  // Return 404 if the department is not found
        }
    }
    */




}
