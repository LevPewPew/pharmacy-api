import java.util.Arrays;

class Medication {
    private int id;
    private String medicationId;
    private String bottleSize;
    private int dosageCount;

    public Medication(int id, String medicationId, String bottleSize, int dosageCount) {
        super();

        this.id = id;
        this.medicationId = medicationId;
        this.bottleSize = bottleSize;
        this.dosageCount = dosageCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(String bottleSize) {
        this.bottleSize = bottleSize;
    }

    public int getDosageCount() {
        return dosageCount;
    }

    public void setDosageCount(int dosageCount) {
        this.dosageCount = dosageCount;
    }

    static boolean validate(Medication medication) {
        boolean medicationIdValid = false;
        boolean bottleSizeValid = false;
        boolean dosageCountValid = false;
        boolean valid = false;

        String medicationId = medication.getMedicationId();
        String bottleSize = medication.getBottleSize();
        int dosageCount = medication.getDosageCount();

        if (medicationId.length() < 20) {
            medicationIdValid = true;
        }

        String[] validBottleSizes = {"S", "M", "L", "XL", "XXL", "NA"};
        if (Arrays.stream(validBottleSizes).parallel().anyMatch(bottleSize::matches)) {
            bottleSizeValid = true;
        }

        // don't need to validate for number as type is int and so could not have created this object in first place
        // also don't need to validate if json supplied had leading zeros because the parseInt already handles/allows
        // for both cases. therefore only need to check if dosage count is between 1 and 9999.
        if (1 < dosageCount && dosageCount < 9999) {
            dosageCountValid = true;
        }

        if (medicationIdValid && bottleSizeValid && dosageCountValid) {
            valid = true;
        }

        return valid;
    }
}