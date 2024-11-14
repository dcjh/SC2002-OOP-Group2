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

    // an example of reading
	public static ArrayList readMedicineLiist(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(Medicine_List.csv);
		ArrayList alr = new ArrayList() ;// to store medicine data

        for (int i = 1 ; i < stringArray.size() ; i++) { // i = 1 to skip header
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  medicineName = star.nextToken().trim();	// first token
				int  stock = Integer.parseInt(star.nextToken().trim());	// second token
				int  lowLvlStockAlert = Integer.parseInt(star.nextToken().trim()); // third token
				// create medicine object from file data
				Medicine med = new Medicine(medicineName, stock, lowLvlStockAlert);
				// add to medcine list
				alr.add(med) ;
			}
			return alr ;
	}
  // an example of saving
public static void addMedicine(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store medicine data

        for (int i = 1 ; i < al.size() ; i++) {
				Medicine med = (Medicine)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(med.getName().trim());
				st.append(SEPARATOR);
				st.append(med.getStock().trim());
				st.append(SEPARATOR);
				st.append(med.getLLSA());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}
    
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

public static void main(String[] aArgs)  {
    	MedicineDAO medDAO = new MedicineDAO();
    	String filename = "Medicine_List.csv" ;
		try {
			// read file containing medicine records.
			ArrayList al = MedicineDAO.readMedicineLiist(Medicine_List.csv) ;
			for (int i = 1 ; i < al.size() ; i++) {
					Medicine med = (Medicine)al.get(i);
					System.out.println("Name " + med.getName() );
					System.out.println("Stock " + med.getStock() );
			}
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }
}