package com.jrl.exercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VinCheckerTest {

    @Test
    public void testCheckPattern() {
        VinChecker vinChecker = new VinChecker();
        assertTrue(vinChecker.checkVinFormat("047195869-2"));
        assertTrue(vinChecker.checkVinFormat("089195869-10"));
        assertFalse(vinChecker.checkVinFormat("082195869a-10"));
    }
}