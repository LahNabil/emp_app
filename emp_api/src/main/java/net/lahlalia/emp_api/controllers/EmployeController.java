package net.lahlalia.emp_api.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.EmployeDto;
import net.lahlalia.emp_api.exceptions.DepartementNotFoundException;
import net.lahlalia.emp_api.services.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employe")
public class EmployeController {

    private final EmployeService employeService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeDto>> getAllEmployes(){
        List<EmployeDto> employes = employeService.getAllEmployes();
        return ResponseEntity.ok(employes);
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeDto> saveEmploye(@RequestBody EmployeDto dto){
        EmployeDto savedEmp = employeService.saveEmploye(dto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmploye(@PathVariable Integer id) {
        try {
            Boolean isDeleted = employeService.deleteEmp(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build();  // Return 204 No Content if deletion is successful
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);  // Return 500 if something went wrong
            }
        } catch (DepartementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);  // Return 404 if the department is not found
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeDto>updateEmploye(@PathVariable Integer id, @RequestBody EmployeDto dto) throws EntityNotFoundException {
        EmployeDto updatedEmp = employeService.updateEmploye(id,dto);
        return ResponseEntity.ok(updatedEmp);
    }


}
