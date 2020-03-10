import java.util.List;

class MedicationStringsList {
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