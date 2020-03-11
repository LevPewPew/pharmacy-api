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
        HashMap<String, Integer> perMedsSupplyCount = new HashMap<String, Integer>();

        for (Medication medication : medicationMap.values()) {
            // stat 1
            totalMeds += 1;

            // stat 2
            totalDosages += medication.getDosageCount();

            // stat 3
            String bottleSize = medication.getBottleSize();
            totalMedsByBottleSize.merge(bottleSize, 1, Integer::sum);

            // stat 4
            String medicationId = medication.getMedicationId();
            perMedsSupplyCount.merge(medicationId, 1, Integer::sum);
        }

        statistics.put("totalMeds", totalMeds);
        statistics.put("totalDosages", totalDosages);
        for (String bottleSize : totalMedsByBottleSize.keySet()) {
            statistics.put(String.format("Total medication in bottle size %s:", bottleSize), totalMedsByBottleSize.get(bottleSize));
        }
        for (String medicationId : perMedsSupplyCount.keySet()) {
            statistics.put(String.format("Total times supplied with medicationID %s:", medicationId), perMedsSupplyCount.get(medicationId));
        }
        
        return statistics;
    }
}
