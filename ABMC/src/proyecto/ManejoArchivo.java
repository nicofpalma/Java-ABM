package proyecto;
import java.io.*;
import java.util.Scanner;

public class ManejoArchivo {
	public static void crearArchivo(String nombreArchivo) {
		// Metodo para crear el archivo.
		File archivo = new File(nombreArchivo);
		
		// Intenta crear el archivo, si ya existe, solo lo abre.
		try {
			if (archivo.exists() == false) {
				PrintWriter salida = new PrintWriter(archivo);
				salida.close();
				System.out.println("Se creo el archivo " + nombreArchivo);
			}
			else
				System.out.println("El archivo ya existe.");
			
		// Manejo de excepciones
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		}	
	}
	
	public static void escribirArchivo(String nombreArchivo, String texto) {
		// Metodo para escribir en el archivo.
		File archivo = new File(nombreArchivo);
		
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
			salida.println(texto);
			salida.close();
			System.out.println("Se escribio en el archivo " + nombreArchivo);
			
		// Manejo de excepciones	
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}	
	}
	
	public static void leerArchivo(String nombreArchivo) {
		// Metodo para leer el archivo completo.
		File archivo = new File(nombreArchivo);
		
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			
			while (linea != null) {
				if (linea.contains("true")) {
					String[] separador = linea.split(",");
					System.out.println(separador[0] + ", " + separador[1]);
				}
				linea = entrada.readLine();
			}
			entrada.close();
		
		// Manejo de excepciones
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static boolean darDeBaja(String nombreArchivo, String numLegajo) {
		File archivo = new File(nombreArchivo);
		boolean modificado = false;
		
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			String copia = "";
			
			while(linea != null) {
				String[] camposDeLinea = linea.split(",");
				
				if (camposDeLinea[1].contains(numLegajo) && camposDeLinea[2].contains("true")) {
					copia += linea.replaceAll("true", "false"+"\r\n");
					modificado = true;
					linea = entrada.readLine();
					//String lineaModificada = camposDeLinea[0] + "," + camposDeLinea[1] + "," + String.valueOf(false);
				} else {
					copia += linea+"\r\n";
					linea = entrada.readLine();
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			fileOut.write(copia.getBytes());
			fileOut.close();
			entrada.close();
			
		// Manejo de excepciones
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return modificado;
	}
	
	public static boolean modificarRegistro(String nombreArchivo, String numLegajo) {
		boolean modificado = false;
		File archivo = new File(nombreArchivo);
		Scanner scan = new Scanner(System.in);
		
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			String copia = "";
			
			// Busqueda secuencial por numero de legajo.
			while (linea != null) {
				String[] camposDeLinea = linea.split(",");
				
				if (camposDeLinea[1].contains(numLegajo) && camposDeLinea[2].contains("true")) {
					System.out.println("\n ALUMNO ENCONTRADO: ");
					String datosAlumno = camposDeLinea[0] + ", " + camposDeLinea[1];
					System.out.println(datosAlumno);
					System.out.println("----------------------------\n");
					
					System.out.print("Escriba nuevo nombre: ");
					String nuevoNombre = scan.nextLine();
					
					System.out.print("Escriba nuevo legajo: ");
					String nuevoLegajo = scan.nextLine();
					
					String nuevosDatos = nuevoNombre + "," + nuevoLegajo + "," + "true";
					copia += linea.replaceAll(linea, nuevosDatos+"\r\n");
					modificado = true;
					linea = entrada.readLine();
				} else {
					copia += linea + "\r\n";
					linea = entrada.readLine();
				}
			}
			// Reemplaza el archivo por el mismo pero modificado.
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			fileOut.write(copia.getBytes());
			fileOut.close();
			entrada.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		
		return modificado;
	}

}
