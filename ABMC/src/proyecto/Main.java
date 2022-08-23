package proyecto;

import java.util.Scanner;
import static proyecto.ManejoArchivo.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean activo = true;
		crearArchivo("Alumnos.txt");
		
		while (activo) {
			System.out.println("MENU \n");
			System.out.println("(1) DAR DE ALTA ALUMNO");
			System.out.println("(2) DAR DE BAJA ALUMNO");
			System.out.println("(3) MODIFICAR ALUMNO");
			System.out.println("(4) CONSULTAR REGISTROS");

			System.out.println("\n (0) SALIR");
			
			int opcion = sc.nextInt();
			if (opcion == 1) {
				// Opcion para dar de alta.
				
				System.out.println("\n (1) DAR DE ALTA ALUMNO \n");
				sc.nextLine();
				
				System.out.print("NOMBRE Y APELLIDO: ");
				String nombre = sc.nextLine();
				
				System.out.println("LEGAJO: ");
				int legajo = sc.nextInt();
				
				boolean alumnoActivo = true;
				
				String lineaTexto = nombre + "," + String.valueOf(legajo) + "," +  String.valueOf(alumnoActivo);
				escribirArchivo("Alumnos.txt", lineaTexto);
				
				System.out.println("\n Alumno " + nombre + " dado de alta correctamente. \n\n");
				
			} else if (opcion == 2) {
				System.out.println("\n (2) DAR DE BAJA ALUMNO \n");
				System.out.print("Escriba el LEGAJO del alumno que desea borrar: ");
				sc.nextLine();
				String buscaLegajo = sc.nextLine();
				
				if (darDeBaja("Alumnos.txt", buscaLegajo) == true) {
					System.out.println("Alumno con legajo " + buscaLegajo + " dado de baja correctamente." );
					sc.nextLine();
					System.out.println("Presione ENTER para volver al menu.");
					String vacio = sc.nextLine();
				} else {
					System.out.println("Alumno con legajo " + buscaLegajo + " no existe.");
					sc.nextLine();
					System.out.println("Presione ENTER para volver al menu.");
					String vacio = sc.nextLine();
				}
				
			} else if (opcion == 3) {
				System.out.println("\n (3) MODIFICAR ALUMNO \n");
				
				System.out.print("Introduzca numero de legajo a modificar: ");
				sc.nextLine();
				String buscaLegajo = sc.nextLine();
				
				if (modificarRegistro("Alumnos.txt", buscaLegajo) == true) {
					System.out.println("El alumno con legajo " + buscaLegajo + " fue modificado correctamente.");
					sc.nextLine();
					System.out.println("Presione ENTER para volver al menu.");
					String vacio = sc.nextLine();
				} else {
					System.out.println("Alumno con legajo " + buscaLegajo + " no existe.");
					sc.nextLine();
					System.out.println("Presione ENTER para volver al menu.");
					String vacio = sc.nextLine();
				}
				
			} else if (opcion == 4) {
				// Consulta los registros en el archivo.
				System.out.println("\n (4) CONSULTAR REGISTROS \n");
				leerArchivo("Alumnos.txt");
				System.out.println("\n PRESIONE ENTER PARA CONTINUAR...");
				sc.nextLine();
				String vacio = sc.nextLine();
				
			} else if (opcion == 0) {
				System.out.println("*** FIN DEL PROGRAMA ***");
				activo = false;
			} else {
				System.out.println("La opcion no es correcta.");
				sc.nextLine();
				System.out.println("Presione ENTER para volver al menu");
				String vacio = sc.nextLine();
			}
		} 
		
		

	}

}
