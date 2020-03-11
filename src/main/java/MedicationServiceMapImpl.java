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
        totalMedsByBottleSize.put("totalMedsInS", 0);
        totalMedsByBottleSize.put("totalMedsInM", 0);
        totalMedsByBottleSize.put("totalMedsInL", 0);
        totalMedsByBottleSize.put("totalMedsInXL", 0);
        totalMedsByBottleSize.put("totalMedsInXXL", 0);
        totalMedsByBottleSize.put("totalMedsInNA", 0);
        HashMap<String, Integer> perMedsSupplyCount = new HashMap<String, Integer>();;

        for (Medication medication : medicationMap.values()) {
            // stat 1
            totalMeds += 1;

            // stat 2
            totalDosages += medication.getDosageCount();

            // stat 3
            switch(medication.getBottleSize()) {
                case "S":
                    totalMedsByBottleSize.put("totalMedsInS", totalMedsByBottleSize.get("totalMedsInS") + 1);
                    break;
                case "M":
                    totalMedsByBottleSize.put("totalMedsInM", totalMedsByBottleSize.get("totalMedsInM") + 1);
                    break;
                case "L":
                    totalMedsByBottleSize.put("totalMedsInL", totalMedsByBottleSize.get("totalMedsInL") + 1);
                    break;
                case "XL":
                    totalMedsByBottleSize.put("totalMedsInXL", totalMedsByBottleSize.get("totalMedsInXL") + 1);
                    break;
                case "XXL":
                    totalMedsByBottleSize.put("totalMedsInXXL", totalMedsByBottleSize.get("totalMedsInXXL") + 1);
                    break;
                case "NA":
                    totalMedsByBottleSize.put("totalMedsInNA", totalMedsByBottleSize.get("totalMedsInNA") + 1);
                    break;
                default:
                    // code block
            }

            System.out.println("```````````````````````````````");
            System.out.println(medication.getId());
            System.out.println(medication.getMedicationId());
            System.out.println(medication.getBottleSize());
            System.out.println(medication.getDosageCount());
            System.out.println("_______________________________");
        }

        statistics.put("totalMeds", totalMeds);
        statistics.put("totalDosages", totalDosages);
        statistics.put("totalMedsInS", totalMedsByBottleSize.get("totalMedsInS"));
        statistics.put("totalMedsInM", totalMedsByBottleSize.get("totalMedsInM"));
        statistics.put("totalMedsInL", totalMedsByBottleSize.get("totalMedsInL"));
        statistics.put("totalMedsInXL", totalMedsByBottleSize.get("totalMedsInXL"));
        statistics.put("totalMedsInXXL", totalMedsByBottleSize.get("totalMedsInXXL"));
        statistics.put("totalMedsInNA", totalMedsByBottleSize.get("totalMedsInNA"));
        
        return statistics;
    }
}
