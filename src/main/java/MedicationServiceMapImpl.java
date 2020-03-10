import java.util.Collection;
import java.util.HashMap;

public class MedicationServiceMapImpl implements MedicationService {
    private HashMap<String, Medication> medicineMap;

    public MedicationServiceMapImpl() {
        medicineMap = new HashMap<>();
    }

    @Override
    public void addMedicine(Medication medication) {
        medicineMap.put(medication.getId(), medication);
    }

    @Override
    public Collection<Medication> getMedicines() {
        return medicineMap.values();
    }

    @Override
    public Medication getMedicine(String id) {
        return medicineMap.get(id);
    }
}
