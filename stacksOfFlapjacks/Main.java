package stacksOfFlapjacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			File file=new File("./output.txt");
			FileWriter fw=new FileWriter(file);
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String pancake = "";
			while((pancake = in.readLine()) != null) {
				PancakeStack stack = new PancakeStack();
				String[] sizes = pancake.split(" ");
				for(int i = sizes.length -1; i>=0 ;i--) {
					int size = Integer.parseInt(sizes[i]);
					stack.getStack().add(size);
				}
							
				int k = 0;
				//ordenación
				fw.write(pancake + "\n");
				fw.flush();
				
				while(!stack.isSorted()) {
					//Obtengo el pancake mayor
					int maxSize = 0;
					for(int i = k; i<stack.getStack().size() ;i++) {
						int size = stack.getStack().get(i);
						if(size > maxSize) {
							maxSize = size;
							stack.setLargestPancake(i);
					//		System.out.println("max indice: " + i + ": " + size + "\n") ;
						}						
					}
					
					if(stack.getLargestPancake() != stack.getStack().size() - 1) {
						//hago flip para que el pancake mayor rote hacia el top
						//System.out.println("flip n: " + (stack.getLargestPancake() + 1)) ;
						stack.flip(stack.getLargestPancake() + 1);
						fw.write((stack.getLargestPancake() + 1) + " ");
					}
					
					//hago flip sobre k
					stack.flip(k + 1);
				//	System.out.println("flip desde top n: " + (k + 1)) ;
					fw.write((k + 1) + " ");
					
					k++;
					
					}
			
				fw.write("0\n");
				fw.flush();									
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
