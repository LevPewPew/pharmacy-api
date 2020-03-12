package com.lev.medication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
                () -> "json formatted string using array with 1 element is separated into 1 string");

        json = "{\"medicationStrings\": [\"186FASc73541_M_1058\",\"18673cda541_S_0061\"]}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "json formatted string using array with 2 elements is separated into 2 strings");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;\"}";
        assertEquals(1, Medication.separateMedicationStrings(json).length,
                () -> "json formatted string using 1 string with trailing semi-colon is separated into 1 string");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058\"}";
        assertEquals(1, Medication.separateMedicationStrings(json).length,
                () -> "json formatted string using 1 string is separated into 1 string");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;18673cda541_S_0061;\"}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "json formatted string using 2 semi-colon separated strings with trailing semi-colon is separated into 2 strings");

        json = "{\"medicationStrings\": \"186FASc73541_M_1058;18673cda541_S_0061\"}";
        assertEquals(2, Medication.separateMedicationStrings(json).length,
                () -> "json formatted string using 2 semi-colon separated strings with no trailing semi-colon is separated into 2 strings");
    }

    @Test
    public void medicationStringToMedicationObject() {
        String medicationString;
        Medication medication;

        medicationString = "186FASc73541_M_1058";
        medication = Medication.medicationStringToMedicationObject(medicationString);
        assertEquals("186FASc73541", medication.getMedicationId(),
                () -> "nnnnnnnnxxxx ");
        assertEquals("M", medication.getBottleSize(),
                () -> "nnnnnnnnxxxx ");
        assertEquals(1058, medication.getDosageCount(),
                () -> "nnnnnnnnxxxx ");
    }
}
