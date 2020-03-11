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
}