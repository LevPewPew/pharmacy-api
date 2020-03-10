import java.util.Collection;
import java.util.HashMap;

public class MedicineServiceMapImpl implements MedicineService {
    private HashMap<String, Medicine> medicineMap;

    public MedicineServiceMapImpl() {
        medicineMap = new HashMap<>();
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineMap.put(medicine.getId(), medicine);
    }

    @Override
    public Collection<Medicine> getMedicines() {
        return medicineMap.values();
    }

    @Override
    public Medicine getMedicine(String id) {
        return medicineMap.get(id);
    }
}
