package com.lev.medication;

import com.lev.medication.Medication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnit4.class)
public class MedicationTest {
    @Test
    public void Validation() {
        assertEquals(true, Medication.validate(new Medication(1, "123abc", "S", 1000)), () -> "validate should return true with all valid inputs");
        assertEquals(false, Medication.validate(new Medication(1, "12345abcde12345abcde1", "S", 1000)), () -> "validate should return false with medicationId over 20 chars");
        assertEquals(false, Medication.validate(new Medication(1, "123abc", "XS", 1000)), () -> "validate should return false with unsupported bottle size");
        assertEquals(false, Medication.validate(new Medication(1, "123abc", "S", 91000)), () -> "validate should return false with dosageCount over 9999");
        assertEquals(false, Medication.validate(new Medication(1, "123abc", "S", 0)), () -> "validate should return false with dosageCount less than 1");
    }
}
