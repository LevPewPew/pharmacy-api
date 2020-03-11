package com.lev.medication;

//import com.lev.medication.Medication;

import com.lev.medication.Medication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MedicationTest {
    @Test
    public void testMe() {
        Medication.validate(new Medication(1, "123abc", "S", 1000));
    }
}
