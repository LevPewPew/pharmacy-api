import java.util.Collection;

public interface MedicationService {
    public void addMedicine(Medication medication);

    public Collection<Medication> getMedicines();

    public Medication getMedicine(String id);
}