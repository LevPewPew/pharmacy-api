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

    @Override
    public HashMap<String, Integer> getStatistics() {
        HashMap<String, Integer> statistics = new HashMap<String, Integer>();
        int totalMeds;
        int totalDosages;
        HashMap<String, Integer> totalMedsByBottleSize;
        HashMap<String, Integer> perMedsSupplyCount;

        for (Medication medication : medicationMap.values()) {

            System.out.println("```````````````````````````````");
            System.out.println(medication.getId());
            System.out.println(medication.getBottleSize());
            System.out.println(medication.getDosageCount());
            System.out.println("_______________________________");

        }

        statistics.put("foo", 123);
        statistics.put("bar", 789);

        return statistics;
    }
}
