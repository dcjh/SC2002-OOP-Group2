package Data.DataAccess;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Model.Shared.Medicine;

public class MedicineDAO {
	public static final String SEPARATOR = ",";

    // Reading medicines from a CSV file, skipping the header
    public static ArrayList<Medicine> readMedicineList(String filename) throws IOException {
        ArrayList<String> stringArray = (ArrayList<String>) read(filename);
        ArrayList<Medicine> medicineList = new ArrayList<>(); // To store medicine data

        for (int i = 1; i < stringArray.size(); i++) { // Start at i = 1 to skip header
            String st = stringArray.get(i);
            StringTokenizer tokenizer = new StringTokenizer(st, SEPARATOR);

            String medicineName = tokenizer.nextToken().trim();
            int stock = Integer.parseInt(tokenizer.nextToken().trim());
            int lowLevelStockAlert = Integer.parseInt(tokenizer.nextToken().trim());

            // Create Medicine object from file data
            Medicine med = new Medicine(medicineName, stock, lowLevelStockAlert);
            medicineList.add(med);
        }
        return medicineList;
    }

  // an example of saving
    public static void saveMedicines(String filename, List<Medicine> medicines) throws IOException {
        List<String> dataToWrite = new ArrayList<>();

        for (Medicine med : medicines) {
            StringBuilder st = new StringBuilder();
            st.append(med.getName().trim());
            st.append(SEPARATOR);
            st.append(med.getStock());
            st.append(SEPARATOR);
            st.append(med.getLLSA());
            dataToWrite.add(st.toString());
        }
        write(filename, dataToWrite);
    }

    // Writing to a file
    public static void write(String fileName, List<String> data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        try {
            for (String line : data) {
                out.println(line);
            }
        } finally {
            out.close();
        }
    }

    public static List<String> read(String fileName) throws IOException {
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }
}
