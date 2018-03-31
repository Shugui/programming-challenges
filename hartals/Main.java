package hartals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Main {
	
	private static int t; //number of test cases 
	private static int n; //number of days over the simulation must be run
	private static int p; //number of political parties 
	
	private static int[] hartalParameters;
	
	private static HashSet<Integer> hartalDays = new HashSet<Integer>();
	

	public static void main(String[] args) {
		try {
			File file=new File("./output.txt");
			FileWriter fw=new FileWriter(file);
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			t = Integer.parseInt(in.readLine());
			
			for(int j= 0; j<t; j++) {
				n = Integer.parseInt(in.readLine());
				p = Integer.parseInt(in.readLine());
				hartalParameters = new int[p];
				for(int i = 0; i<p; i++) {
					int h = Integer.parseInt(in.readLine());
					hartalParameters[i] = h; 					
					int hartalsNumber = n/h;
					//Data processing
					//Será huelga si Hi mod N == 0 y el día sepecificado cumple con los 
					//requisitos
					for(int k = 1; k <= hartalsNumber; k++) {
						int indice = k*h;
						if(!(indice%7 == 6 || indice%7 == 0)){
							hartalDays.add(indice);
						}
					}
				}
				
				//Output
				fw.write(String.valueOf(hartalDays.size()) + "\n");
				fw.flush();
				hartalDays.removeAll(hartalDays);
			}
			fw.close();
			in.close();
		} catch (NumberFormatException e) {
			System.err.println("Error al intentar convertir el archivo de entrada");
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el archivo de entrada");
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error en buffer de entrada");
			// e.printStackTrace();
		}

	}

}
