import java.util.Collection;

public interface MedicineService {
    public void addMedicine(Medicine medicine);

    public Collection<Medicine> getMedicines();

    public Medicine getMedicine(String id);
}