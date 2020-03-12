package com.lev.medication;

import java.util.HashMap;

public interface MedicationService {
    void addMedication(Medication medication);

    HashMap<String, Integer> getStatistics();
}