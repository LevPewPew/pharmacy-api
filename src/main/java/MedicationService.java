import java.util.HashMap;

public interface MedicationService {
    void addMedicine(Medication medication);

    HashMap<String, Integer> getStatistics();
}