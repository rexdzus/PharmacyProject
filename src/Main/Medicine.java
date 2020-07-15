package Main;

import java.util.Hashtable;

public class Medicine {
    public String drug;
    public int frequency;
    public String condition;

    public void Medicine(String drug, int frequency, String condition) {
        this.drug = drug;
        this.frequency = frequency;
        this.condition = condition;
    }
    public static class MedicineList {

        Hashtable<String, Medicine> medicines = new Hashtable<String, Medicine>();

        public void addMedicine(Medicine medicine) {
            this.medicines.put(medicine.drug, medicine);
        }

        public Medicine findDrug(String drug) {
            return medicines.get(drug);
        }
        @Override
        public String toString() {
            return medicines.toString();
        }

    }

}
