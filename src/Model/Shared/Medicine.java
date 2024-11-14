package Model.Shared;

import java.io.Serializable;

public class Medicine implements Serializable{
    private String medicineName;
    private int initialStock;
    private int lowLvlStockAlert;

    public Medicine(String mn, int initS, int lLSA){
        medicineName = mn;
        initialStock = initS;
        lowLvlStockAlert = lLSA;
    }

    public String getName(){
        return medicineName;
    }

    public int getIS(){
        return initialStock;
    }

    public int getLLSA(){
        return lowLvlStockAlert;
    }

    public void setLLSA(int newLLSA){
        this.lowLvlStockAlert = newLLSA;
    }

    public boolean equals(Object o){
        if (o instanceof Medicine){
            Medicine m = (Medicine)o;
            return (getName().equals(p.getName()));
        }
        return false;
    }

}