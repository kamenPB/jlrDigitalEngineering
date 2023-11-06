package com.jrl.exercise;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VinService {

    private ArrayList<String> vinRepository = new ArrayList<>();

    public boolean createVin(String vin) {
        // validate before creating
        VinChecker vinChecker = new VinChecker();
        if (vinChecker.checkVinFormat(vin)) {
            vinRepository.add(vin);
            System.out.println("Vin " + vin + " added to repo");
            return true;
        }
        return false;
    }

    public boolean getVin(String vin) {
        for (String v: vinRepository) {
            if (v.equals(vin)) return true;
        }
        return false;
    }

    public String getVinRepository() {
        String str = "Current VINs: ";
        for (String vin : vinRepository) {
            str = str.concat(vin + ", ");
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
}
