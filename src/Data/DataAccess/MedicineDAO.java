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
	public static ArrayList readMedicineLiist(String Medicine_List) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(Medicine_List);
		ArrayList alr = new ArrayList() ;// to store medicine data

        for (int i = 1 ; i < stringArray.size() ; i++) { // i = 1 to skip header
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  medicineName = star.nextToken().trim();	// first token
				int  initialStock = Integer.parseInt(star.nextToken().trim());	// second token
				int  lowLvlStockAlert = Integer.parseInt(star.nextToken().trim()); // third token
				// create medicine object from file data
				Medicine med = new Medicine(medicineName, initialStock, lowLvlStockAlert);
				// add to medcine list
				alr.add(med) ;
			}
			return alr ;
	}
  // an example of saving
public static void addMedicine(String Medicine_List, List al) throws IOException {
		List alw = new ArrayList() ;// to store medicine data

        for (int i = 1 ; i < al.size() ; i++) {
				Medicine med = (Medicine)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(med.getName().trim());
				st.append(SEPARATOR);
				st.append(med.getIS().trim());
				st.append(SEPARATOR);
				st.append(med.getLLSA());
				alw.add(st.toString()) ;
			}
			write(Medicine_List,alw);
	}


  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }

public static void main(String[] aArgs)  {
    	TextDB txtDB = new TextDB();
    	String filename = "professor.txt" ;
		try {
			// read file containing Professor records.
			ArrayList al = TextDB.readProfessors(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					Professor prof = (Professor)al.get(i);
					System.out.println("Name " + prof.getName() );
					System.out.println("Contact " + prof.getContact() );
			}
			Professor p1 = new Professor("Joseph","jos@ntu.edu.sg",67909999);
			// al is an array list containing Professor objs
			al.add(p1);
			// write Professor record/s to file.
			TextDB.saveProfessors(filename, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }
}