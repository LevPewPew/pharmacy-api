import java.util.Collection;
import java.util.HashMap;

public interface MedicationService {
    public void addMedicine(Medication medication);

    public Collection<Medication> getMedicines();

    public Medication getMedicine(String id);

    public HashMap<String, Integer> getStatistics();
}