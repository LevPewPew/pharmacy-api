import java.util.Collection;
import java.util.HashMap;

public class MedicationServiceMapImpl implements MedicationService {
    private HashMap<Integer, Medication> medicationMap;

    public MedicationServiceMapImpl() {
        medicationMap = new HashMap<>();
    }

    @Override
    public void addMedicine(Medication medication) {
        medicationMap.put(medication.getId(), medication);
    }

    @Override
    public Collection<Medication> getMedicines() {
        return medicationMap.values();
    }

    @Override
    public Medication getMedicine(String id) {
        return medicationMap.get(id);
    }

    // in this method i have assumed that "the total number of medications that have been inputted" refers to total
    // amount of medicine strings that have been posted (i.e. total amount of Medicine objects created), not the total
    // amount of unique medicationIds.
    @Override
    public HashMap<String, Integer> getStatistics() {
        HashMap<String, Integer> statistics = new HashMap<String, Integer>();

        int totalMeds = 0;
        int totalDosages = 0;
        HashMap<String, Integer> totalMedsByBottleSize = new HashMap<String, Integer>();
        HashMap<String, Integer> perMedsSupplyCount = new HashMap<String, Integer>();;

        for (Medication medication : medicationMap.values()) {
            totalMeds += 1;
            totalDosages += medication.getDosageCount();
            System.out.println("```````````````````````````````");
            System.out.println(medication.getId());
            System.out.println(medication.getMedicationId());
            System.out.println(medication.getBottleSize());
            System.out.println(medication.getDosageCount());
            System.out.println("_______________________________");
        }

        statistics.put("totalMeds", totalMeds);
        statistics.put("totalDosages", totalDosages);

        return statistics;
    }
}
