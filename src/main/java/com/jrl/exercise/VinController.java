package com.jrl.exercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VinController {

    private final VinService vinService;

    public VinController(VinService vinService) {
        this.vinService = vinService;
    }

    @PostMapping
    public ResponseEntity<String> createVin(@RequestBody String vin) {
        if (vinService.createVin(vin)) {
            return new ResponseEntity<>(String.valueOf(true), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(String.valueOf(false), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/temp/{vin}")
    public ResponseEntity<Boolean> getVinValidation(@PathVariable String vin) {
        if (vinService.getVin(vin)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/temp/repository")
    public ResponseEntity<String> getVinRepository() {
        try {
            vinService.getVinRepository();
            return new ResponseEntity<>(vinService.getVinRepository(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(vinService.getVinRepository(), HttpStatus.BAD_REQUEST);
        }
    }
}
