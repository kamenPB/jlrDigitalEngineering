package com.jrl.exercise;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinService {

    private ArrayList<String> vinRepository = new ArrayList<>();

    public boolean createVin(String vin) {
        // validate before creating
        VinChecker vinChecker = new VinChecker();
        if (vinChecker.checkVinFormat(vin)) {
            vinRepository.add(vin);
            return true;
        }
        return false;
    }

    public String getVin(String vin) {
        for (String v: this.vinRepository) {
            if (v.equals(vin)) return vin;
        }
        throw new VinNotFoundException(String.format("VIN '%s' not found.", vin));
    }

    public ArrayList<String> getAllVins() {
        return this.vinRepository;
    }
}
