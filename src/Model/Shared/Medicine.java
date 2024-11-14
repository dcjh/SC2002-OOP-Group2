package Model.Shared;

import java.io.Serializable;
import java.util.Objects;

public class Medicine implements Serializable {
    private String medicineName;
    private int stock;
    private int lowLvlStockAlert;

    public Medicine(String mn, int s, int lLSA) {
        this.medicineName = mn;
        this.stock = s;
        this.lowLvlStockAlert = lLSA;
    }

    // Getters
    public String getName() {
        return medicineName;
    }

    public int getStock() {
        return stock;
    }

    public int getLLSA() {
        return lowLvlStockAlert;
    }

    // Setters
    public void setName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setLLSA(int newLLSA) {
        this.lowLvlStockAlert = newLLSA;
    }

    @Override //chheck if have more than one instasnce of same medication
    public boolean equals(Object o) {
        if (this == o) return true; // if same instance then true
        if (o == null || getClass() != o.getClass()) return false; //if differnet class, false
        Medicine medicine = (Medicine) o; //can case o to medicine since alrdy checked
        //checks if rest of fields are equal
        return stock == medicine.stock && 
                lowLvlStockAlert == medicine.lowLvlStockAlert &&
                Objects.equals(medicineName, medicine.medicineName);
    }
}
