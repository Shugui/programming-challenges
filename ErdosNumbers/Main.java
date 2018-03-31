package ErdosNumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

	private static int s;
	private static int p;
	private static int n;

	private static HashSet<Researcher> erdosNames= new HashSet<Researcher>();;
	private static HashSet<Researcher> allNames= new HashSet<Researcher>();;
	private static HashSet<Researcher> solResearchers= new HashSet<Researcher>();;
	private static HashSet<Paper> papers = new HashSet<Paper>();
	private static int erdosSize;
	
	//private static String output;

	// args == input pathfile
	public static void main(String[] args) {
		try {
			File file=new File("./output.txt");
			FileWriter fw=new FileWriter(file);
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			try {
				s = Integer.parseInt(in.readLine());
				String[] aux =in.readLine().split(" ");
				p = Integer.parseInt(aux[0]);
				n = Integer.parseInt(aux[1]);
			} catch (NumberFormatException e) {
				System.err.println("Error al intentar convertir el archivo de entrada");
				// e.printStackTrace();
			}
			//Ejecutamos escenario a escenario
			for(int k = 1; k <= s ; k++ ) {
				// Leemos la base de datos
				// Ensayos
				for (int i = 0; i < p; i++) {
					Boolean erdos = false;
					Paper paper = new Paper();
					HashSet<Researcher> authors =  new HashSet<Researcher>();

					String line = in.readLine();
					//Título
					paper.setTitle(line.substring(line.indexOf(":")+1).trim());
					
					//Investigadores
					String[] namesAndSecondNames = line.substring(0, line.indexOf(":")).split(",");
					for (int j = 0; j < namesAndSecondNames.length; j++) {
						Researcher researcher = new Researcher();
						String name = namesAndSecondNames[j].trim();
						String secondName = namesAndSecondNames[j + 1].trim();
						researcher.setName(name + ", " + secondName);
						authors.add(researcher);
						if(secondName.equals("P.") && name.equals("Erdos")){
							erdos = true;
						}
						j++;
					}
					paper.setAuthors(authors);
					papers.add(paper);
					//Si erdos == true esos investigadores han trabajado directamente con erdos
					//añadimos con puntuacion 1
					if(erdos) {
						for(Researcher r:authors) {
							r.setScore(1);
						}
						allNames.addAll(authors);
						erdosNames.addAll(authors);
					}else {
						allNames.addAll(authors);
					}
				}
				// Nombres
				for (int i = 0; i < n; i++) {
					Researcher researcher = new Researcher();
					researcher.setName(in.readLine());
					allNames.add(researcher);
					solResearchers.add(researcher);
				}
				
				int grado = 1;
				//Si la lista es del mismo tamañao q la anteerio iteracion paro
				do {
					erdosSize = erdosNames.size();
					ArrayList<Researcher> list = new ArrayList<Researcher>(erdosNames);
					for(Researcher r: list) {
						if(r.getScore() == grado) {
							for(Paper p:papers) {
								if(p.getAuthors().contains(r)) {
									HashSet<Researcher> authors = p.getAuthors();
									for(Researcher r2 :authors) {
										if(r2.getScore()==0) {
											r2.setScore(grado + 1);	
										}
									}
									erdosNames.addAll(authors);
								}
							}
						}
					}
					grado++;
				}while(erdosSize != erdosNames.size());
				
				//Output
				fw.write("Scenario " + k + "\n");
				fw.flush();
				for(Researcher r: solResearchers) {
					for(Researcher r2: allNames) {
						if(r.equals(r2)) {
							int punt = r2.getScore();
							if(punt == 0){
								fw.write(r2.getName() + " infinity\n");
								fw.flush();
								break;	
							}else {
								fw.write(r2.getName() + " " + punt + "\n" );
								fw.flush();
								break;	
							}
						}
					}	
				}			
			}
			
			fw.flush();
			fw.close();
			in.close();
			

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el archivo de entrada");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error en buffer de entrada");
			// e.printStackTrace();
		}

	}

}
