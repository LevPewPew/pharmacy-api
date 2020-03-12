package com.lev.medication;

import com.lev.medication.Medication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class MedicationTest {
    @Test
    public void validate() {
        assertTrue(Medication.validate(new Medication(1, "123abc", "S", 1000)),
                () -> "validate should return true with all valid inputs");

        assertFalse(Medication.validate(new Medication(1, "12345abcde12345abcde1", "S", 1000)),
                () -> "validate should return false with medicationId over 20 chars");

        assertFalse(Medication.validate(new Medication(1, "123abc", "XS", 1000)),
                () -> "validate should return false with unsupported bottle size");

        assertFalse(Medication.validate(new Medication(1, "123abc", "S", 91000)),
                () -> "validate should return false with dosageCount over 9999");

        assertFalse(Medication.validate(new Medication(1, "123abc", "S", 0)),
                () -> "validate should return false with dosageCount less than 1");
    }

    @Test
    public void separateMedicationStrings() {
        String json;

        json = "{\"medicationStrings\": [\"186FASc73541_M_1058\"]}";
        assertEquals(1, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using array with single element is parsed");

        json = "{\"medicationStrings\": [\"186FASc73541_M_1058\",\"18673cda541_S_0061\"]}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using array with multiple elements is parsed");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;\"}";
        assertEquals(1, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using single string with trailing semi-colon is parsed");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058\"}";
        assertEquals(1, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using single string is parsed");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;18673cda541_S_0061;\"}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using semi-colon separated string with trailing semi-colon is parsed");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;18673cda541_S_0061\"}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "validate json formatted string using semi-colon separated string with no trailing semi-colon is parsed");
    }

//    @Test
//    public void medicationStringToMedicationObject() {
//        assertEquals(true, Medication.validate(new Medication(1, "123abc", "S", 1000)), () -> "validate should return true with all valid inputs");
//        assertEquals(false, Medication.validate(new Medication(1, "12345abcde12345abcde1", "S", 1000)), () -> "validate should return false with medicationId over 20 chars");
//        assertEquals(false, Medication.validate(new Medication(1, "123abc", "XS", 1000)), () -> "validate should return false with unsupported bottle size");
//        assertEquals(false, Medication.validate(new Medication(1, "123abc", "S", 91000)), () -> "validate should return false with dosageCount over 9999");
//        assertEquals(false, Medication.validate(new Medication(1, "123abc", "S", 0)), () -> "validate should return false with dosageCount less than 1");
//    }
}
