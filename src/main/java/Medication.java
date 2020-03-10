class Medication {
    private String id;
    private String bottleSize;
    private int dosageCount;

    public Medication(String id, String bottleSize, int dosageCount) {
        super();

        this.id = id;
        this.bottleSize = bottleSize;
        this.dosageCount = dosageCount;
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
}