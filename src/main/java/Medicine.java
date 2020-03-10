class Medicine {
    private String id;
    private String bottleSize;
    private int dosageCount;
    private String medicationStrings;

    public Medicine(String id, String bottleSize, int dosageCount, String medicationStrings) {
        super();

        
        this.id = id;
        this.bottleSize = bottleSize;
        this.dosageCount = dosageCount;
        this.medicationStrings = medicationStrings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMedicationStrings() {
        return medicationStrings;
    }

    public void setMedicationStrings(String medicationStrings) {
        this.medicationStrings = medicationStrings;
    }
}