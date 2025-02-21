package hibernate2.crudmultitabla;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import hibernate2.crudmultitabla.Accesobd;
import hibernate2.crudmultitabla.ConsoleColors;
import hibernate2.crudmultitabla.EntidadUsuario;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
		String nombre = "";
		String apellidos = "";
		String username = "";
		int edad;
		int userInput = 0;
		int eleccionTabla = 0;
		int id;
		
		EntidadUsuario usuario = new EntidadUsuario("Paco", "ElBelludo", "PacoBelludo", "1234", "paco.elbelludo@gmail.com");
		EntidadPost post = new EntidadPost(usuario, LocalDate.of(1992, 2, 15), LocalDate.of(1992, 2, 15));
		EntidadLike like = new EntidadLike(usuario, post);
		

		Scanner sc = new Scanner(System.in);

		System.out.println(ConsoleColors.YELLOW + "¡Bienvenido al CRUD de varias tablas en Java!" + ConsoleColors.RESET);
		System.out.println(ConsoleColors.GREEN + "⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \r\n"
				+ "⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ \r\n"
				+ "⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ \r\n"
				+ "⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ \r\n" + "⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ \r\n"
				+ "⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ \r\n"
				+ "⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ \r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ \r\n" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉" + ConsoleColors.RESET);
		
		System.out.println("¿Qué quieres hacer?");

		while (userInput != 5) {
			Menu();

			userInput = sc.nextInt();

			sc.nextLine();

			switch (userInput) {

			case 1:
				
				System.out.println("¿Qué tabla quieres leer?");
				System.out.println("1. Usuarios");
				System.out.println("2. Posts");
				System.out.println("3. Likes");
				System.out.println("Introduzca el número correspondiente a la tabla.");
				
				userInput = sc.nextInt();
				
				sc.nextLine();
				
				eleccionTabla = userInput;
				

				System.out.println("¿Quieres leer la tabla entera o quieres"
						+ "sólo leer los datos de una fila en específico?");
				System.out.println("Escribe: \"0\" en el teclado si quieres leer todos los datos,"
						+ "o: \"1\" si quieres leer los datos de una fila en específico.");
				userInput = sc.nextInt();

				sc.nextLine();

				if (userInput == 0) {
					try {
						switch(eleccionTabla) {
						
						case 1:
							Accesobd.leerListaUsuarios();
							break;
							
						case 2:
							Accesobd.leerListaPosts();
							break;
						case 3:
							Accesobd.leerListaLikes();
							break;
							
						
						}
					} catch (Exception e) {
						System.out.println(ConsoleColors.RED + "Error: No se pudo leer los datos de la tabla elegida."
								+ ConsoleColors.RESET);
					}
				} else if (userInput == 1) {
					
					if(eleccionTabla == 1) {
						
						System.out.println("¿Por cuál filtro quieres leer la tabla Usuarios?");
						
						System.out.println("1. Nombre.\n" + "2. Apellidos.\n" + "3. Nombre de usuario.");
						
						userInput = sc.nextInt();
						
						sc.nextLine();
						
						switch(userInput) {
						
						case 1:
							System.out.println("Escriba el nombre del usuario por el que filtrar.");
							
							nombre = sc.nextLine();
							
							System.out.println(Accesobd.leerUsuarioNombre(nombre));
							break;
							
						case 2:
							System.out.println("Escriba el apellido del usuario por el que filtrar.");
							
							apellidos = sc.nextLine();
							
							System.out.println(Accesobd.leerUsuarioApellidos(apellidos));
							break;
							
						case 3:
							System.out.println("Escriba el nombre de usuario por el que filtrar.");
							
							username = sc.nextLine();
							
							System.out.println(Accesobd.leerUsuarioUsername(username));
							break;
						
						}
						
						
					} else {
						System.out.println("Escribe la ID de la fila de la cuál quieres recibir sus datos.");
						userInput = sc.nextInt();

						sc.nextLine();

						try {
							Accesobd.leer(userInput);
						} catch (Exception e) {
							System.out.println(ConsoleColors.RED
									+ "Error: No se pudo leer los datos de la persona especificada en la tabla Persona."
									+ ConsoleColors.RESET);
						}
					}
					
					
				}

				break;

			case 2:

				System.out.println("Introduzca el nombre: ");
				nombre = sc.nextLine();

				System.out.println("Introduzca los apellidos: ");
				apellidos = sc.nextLine();

				System.out.println("Introduzca una edad: ");

				edad = sc.nextInt();

				sc.nextLine();

				persona.setNombre(nombre);
				persona.setApellidos(apellidos);
				persona.setEdad(edad);

				try {

					Accesobd.abrir();

					Accesobd.guardar(persona);

					System.out.println(ConsoleColors.GREEN + "Persona guardada correctamente." + ConsoleColors.RESET);

					// Accesobd.cerrar();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 3:

				System.out.println("Introduzca la ID de la persona que quiera modificar.");

				id = sc.nextInt();

				sc.nextLine();

				System.out.println("Datos a editar:");
				System.out.println("Introduzca el nombre: ");
				nombre = sc.nextLine();

				System.out.println("Introduzca los apellidos: ");
				apellidos = sc.nextLine();

				System.out.println("Introduzca una edad: ");

				edad = sc.nextInt();

				sc.nextLine();

				try {
					Accesobd.actualizar(id, nombre, apellidos, edad);

					System.out
							.println(ConsoleColors.GREEN + "Persona actualizada correctamente." + ConsoleColors.RESET);
				} catch (Exception e) {
					System.out.println(
							ConsoleColors.RED + "Error: No se pudo modificar la Persona." + ConsoleColors.RESET);
				}

				break;

			case 4:

				System.out.println("Introduzca la ID de la persona que quiera eliminar.");

				id = sc.nextInt();

				sc.nextLine();

				System.out.println(ConsoleColors.RED_BACKGROUND + "¡ATENCIÓN!" + ConsoleColors.RESET);
				System.out.println(ConsoleColors.YELLOW + "Estás a punto de eliminar a la persona con ID: "
						+ Integer.toString(id) + ConsoleColors.RESET);
				
				System.out.println(ConsoleColors.YELLOW + "¿Quieres confirmar estos cambios?" + ConsoleColors.RESET);
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Recuerda que una vez confirmados estos cambios, no podrán revertirse.");
				System.out.println(ConsoleColors.YELLOW + "Escriba: \"0\" si no quiere eliminar a dicha persona, o escriba: \"1\" si quiere confirmar su eliminación." + ConsoleColors.RESET);
				
				userInput = sc.nextInt();
				
				sc.nextLine();
				
				
				
				if(userInput == 1) {
					try {
						Accesobd.borrar(id);
						
						System.out.println(ConsoleColors.GREEN + "Persona eliminada correctamente." + ConsoleColors.RESET);
					} catch (Exception e) {
						System.out.println(ConsoleColors.RED + "No pudo eliminarse a la persona. Puede ser que dicha persona no exista en la BBDD." + ConsoleColors.RESET);
					}
					
					
				} else {
					System.out.println(ConsoleColors.CYAN + "Cambios revertidos. No se ha efectuado ningún cambio." + ConsoleColors.RESET);
				}
				
				break;
			}
		}

		System.out.println(ConsoleColors.CYAN + "Saliendo del programa..." + ConsoleColors.RESET);

		sc.close();
		
    }
    
	private static void Menu() {
		System.out.println("1. Leer Tabla.");
		System.out.println("2. Crear Tablas.");
		System.out.println("3. Editar Tabla.");
		System.out.println("4. Borrar Tabla.");
		System.out.println("5. Salir.");
	}
}
