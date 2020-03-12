package com.lev.medication;

import java.util.List;

public class MedicationStringsList {
    private List<String> medicationStrings;

    public MedicationStringsList(List<String> medicationStrings) {
        super();

        this.medicationStrings = medicationStrings;
    }

    public List<String> getMedicationStrings() {
        return medicationStrings;
    }

    public void setMedicationStrings(List<String> medicationStrings) {
        this.medicationStrings = medicationStrings;
    }
}