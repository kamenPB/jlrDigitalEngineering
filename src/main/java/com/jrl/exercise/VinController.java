package com.jrl.exercise;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@RestController
@RequestMapping("./")
public class VinController {

    private final VinService vinService;

    public VinController(VinService vinService) {
        this.vinService = vinService;
    }

    @GetMapping("/{vin}")
    public ResponseEntity<String> getVin(@PathVariable String vin) {
        return ResponseEntity.ok(vinService.getVin(vin));
    }

    @GetMapping("/vinRepository")
    public ResponseEntity<ArrayList<String>> getAllVins() {
        return ResponseEntity.ok(vinService.getAllVins());
    }

    @PostMapping
    public ResponseEntity<Void> createVin(@RequestBody String vin, UriComponentsBuilder uriComponentsBuilder) {
        boolean b = vinService.createVin(vin);
        HttpHeaders headers = new HttpHeaders();

        if (b) {
            UriComponents uriComponents = uriComponentsBuilder.path("/{vin}").buildAndExpand(vin);
            headers.setLocation(uriComponents.toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
    }
}
