package hibernate2.crudmultitabla;

import java.text.DateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.PropertyValueException;

import hibernate2.crudmultitabla.Accesobd;
import hibernate2.crudmultitabla.ConsoleColors;
import hibernate2.crudmultitabla.EntidadUsuario;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		String nombre = "";
		String apellidos = "";
		String username = "";
		String email = "";
		String password = "";
		int edad;
		int userInput = 0;
		int eleccionTabla = 0;
		int id;
		int id2;
		int id3;
		boolean state;
		LocalDate fechaCreacion = LocalDate.now();
		LocalDate fechaUpdate = LocalDate.now();

		EntidadUsuario usuario = new EntidadUsuario("Paco", "ElBelludo", "PacoBelludo", "1234",
				"paco.elbelludo@gmail.com");
		EntidadPost post = new EntidadPost(usuario, LocalDate.of(1992, 2, 15), LocalDate.of(1992, 2, 15));
		EntidadLike like = new EntidadLike(usuario, post);
		
		List listado = null;

		Scanner sc = new Scanner(System.in);

		System.out
				.println(ConsoleColors.YELLOW_BACKGROUND + "¡Bienvenido al CRUD de varias tablas en Java!" + ConsoleColors.RESET);
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

				menuTabla();

				userInput = sc.nextInt();

				sc.nextLine();

				eleccionTabla = userInput;

				System.out.println(
						"¿Quieres leer la tabla entera o quieres" + "sólo leer los datos de una fila en específico?");
				System.out.println("Escribe: \"0\" en el teclado si quieres leer todos los datos,"
						+ "o: \"1\" si quieres leer los datos de una fila en específico.");
				userInput = sc.nextInt();

				sc.nextLine();

				if (userInput == 0) {
					try {
						switch (eleccionTabla) {

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

					if (eleccionTabla == 1) {

						System.out.println("¿Por cuál filtro quieres leer la tabla Usuarios?");

						System.out.println("1. Nombre.\n" + "2. Apellidos.\n" + "3. Nombre de usuario.");

						userInput = sc.nextInt();

						sc.nextLine();

						switch (userInput) {

						case 1:
							System.out.println("Escriba el nombre del usuario por el que filtrar.");

							nombre = sc.nextLine();

							listado = Accesobd.leerUsuarioNombre(nombre);
							
							
							
							break;

						case 2:
							System.out.println("Escriba el apellido del usuario por el que filtrar.");

							apellidos = sc.nextLine();

							listado = Accesobd.leerUsuarioApellidos(apellidos);

							break;

						case 3:
							System.out.println("Escriba el username por el que filtrar.");

							username = sc.nextLine();

							listado = Accesobd.leerUsuarioUsername(username);
							
							break;

						}
						
						for(Object usuarioListado : listado) {
							System.out.println(usuarioListado);
						}

					} else {
						System.out.println("Escribe la ID de la fila de la cuál quieres recibir sus datos.");
						userInput = sc.nextInt();

						sc.nextLine();

						try {

							if (eleccionTabla == 2) {
								Accesobd.leerPost(userInput);
							} else {
								Accesobd.leerLike(userInput);
							}

						} catch (Exception e) {
							System.out.println(ConsoleColors.RED
									+ "Error: No se pudo leer los datos de la fila especificada en la tabla."
									+ ConsoleColors.RESET);
						}
					}

				}

				break;

			case 2:

				menuTabla();

				userInput = sc.nextInt();

				sc.nextLine();

				eleccionTabla = userInput;

				switch (eleccionTabla) {

				case 1:

					System.out.println("Introduzca el nombre del usuario:");
					nombre = sc.nextLine();

					System.out.println("Introduzca el apellido del usuario:");

					apellidos = sc.nextLine();

					System.out.println("Introduzca el username del usuario:");

					username = sc.nextLine();

					System.out.println("Inserte el email del usuario:");
					email = sc.nextLine();

					System.out.println("Inserte el password del usuario:");

					password = sc.nextLine();

					usuario.setNombre(nombre);
					usuario.setApellidos(apellidos);
					usuario.setUsername(username);
					usuario.setEmail(email);
					usuario.setPassword(password);

					try {

						Accesobd.guardarUsuario(usuario);


						System.out
								.println(ConsoleColors.GREEN + "Usuario guardado correctamente." + ConsoleColors.RESET);

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				case 2:

					System.out.println("Introduzca el ID del usuario al que hace referencia el Post:");
					id = sc.nextInt();

					sc.nextLine();

					System.out.println("Introduzca la fecha de creación del post:");
					System.out.println("El formato de la fecha debe ser el siguiente:");
					System.out.println("Ejemplo: 2025-12-03");

					
					try {
						fechaCreacion = LocalDate.parse(sc.nextLine());
						

						System.out.println("Introduzca la fecha de actualización del post:");
						System.out.println("El formato de la fecha debe ser el siguiente:");
						System.out.println("Ejemplo: 2025-12-03");
						
						fechaUpdate = LocalDate.parse(sc.nextLine());
						



						try {
							usuario = Accesobd.obtenerUsuario(id);
						} catch (Exception e) {
							System.out.println(ConsoleColors.RED
									+ "Error: ID del usuario al que hace referencia el Post no fue encontrado."
									+ ConsoleColors.RESET);
						}

						post.setUsuario(usuario);
						post.setCreatedAt(fechaCreacion);
						post.setUpdatedAt(fechaUpdate);

						try {

							Accesobd.guardarPost(post);

							System.out.println(ConsoleColors.GREEN + "Post guardado correctamente." + ConsoleColors.RESET);

						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch(DateTimeParseException e) {
						System.out.println(ConsoleColors.RED + "Error: Has introducido la fecha en un formato no válido. Por favor, la próxima vez introduce la fecha con este formato:" + ConsoleColors.RESET);
						System.out.println(ConsoleColors.CYAN + "Ejemplo: 2025-12-03" + ConsoleColors.RESET);
					}
					
					


					break;

				case 3:

					System.out.println("Introduce el ID del usuario al que hace referencia el Like:");
					id = sc.nextInt();

					sc.nextLine();

					System.out.println("Introduce el ID del Post al que hace referencia el Like:");
					id2 = sc.nextInt();

					sc.nextLine();

					try {
						usuario = Accesobd.obtenerUsuario(id);
						post = Accesobd.obtenerPost(id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					like.setUsuario(usuario);
					like.setPost(post);

					try {

						Accesobd.guardarLike(like);


						System.out.println(ConsoleColors.GREEN + "Like guardado correctamente." + ConsoleColors.RESET);

					} catch (Exception e) {
						
						if(e instanceof PropertyValueException) {
							System.out.println(ConsoleColors.RED + "Error: Has introducido valores en los campos de Foreign Key que no existen en la BBDD." + ConsoleColors.RESET);
						} else {
							e.printStackTrace();
						}
						
						
					}

					break;
				}

				break;

			case 3:

				menuTabla();

				userInput = sc.nextInt();

				sc.nextLine();

				eleccionTabla = userInput;

				switch (eleccionTabla) {

				case 1:

					System.out.println("Introduzca la ID del usuario que quiera modificar:");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println("Datos a editar:");

					System.out.println("Introduzca el nombre del usuario:");
					nombre = sc.nextLine();

					System.out.println("Introduzca el apellido del usuario:");

					apellidos = sc.nextLine();

					System.out.println("Introduzca el username del usuario:");

					username = sc.nextLine();

					System.out.println("Inserte el email del usuario:");
					email = sc.nextLine();

					System.out.println("Inserte el password del usuario:");

					password = sc.nextLine();

					try {
						
						

						Accesobd.actualizarUsuario(id, nombre, apellidos, username, password, email);

						System.out.println(
								ConsoleColors.GREEN + "Usuario actualizado correctamente." + ConsoleColors.RESET);

					} catch (Exception e) {
						System.out.println(
								ConsoleColors.RED + "Error: No se pudo modificar el Usuario." + ConsoleColors.RESET);
					}

					break;

				case 2:
					
					System.out.println("Introduzca la ID del Post que quiera modificar:");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println("Datos a editar:");

					System.out.println("Introduzca el ID del usuario al que hace referencia el Post:");
					id2 = sc.nextInt();

					sc.nextLine();

					System.out.println("Introduzca la fecha de creación del post:");
					System.out.println("El formato de la fecha debe ser el siguiente:");
					System.out.println("Ejemplo: 2025-12-03");

					fechaCreacion = LocalDate.parse(sc.nextLine());

					System.out.println("Introduzca la fecha de actualización del post:");
					System.out.println("El formato de la fecha debe ser el siguiente:");
					System.out.println("Ejemplo: 2025-12-03");

					fechaUpdate = LocalDate.parse(sc.nextLine());

					try {
						
						usuario = Accesobd.obtenerUsuario(id2);

						Accesobd.actualizarPost(id, usuario, fechaCreacion, fechaUpdate);

						System.out.println(
								ConsoleColors.GREEN + "Post actualizado correctamente." + ConsoleColors.RESET);

					} catch (Exception e) {
						System.out.println(
								ConsoleColors.RED + "Error: No se pudo modificar el Post." + ConsoleColors.RESET);
					}

					break;

				case 3:

					System.out.println("Introduzca la ID del Like que quiera modificar:");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println("Datos a editar:");

					System.out.println("Introduce el ID del usuario al que hace referencia el Like:");
					id2 = sc.nextInt();

					sc.nextLine();

					System.out.println("Introduce el ID del Post al que hace referencia el Like:");
					id3 = sc.nextInt();

					sc.nextLine();

					try {
						
						usuario = Accesobd.obtenerUsuario(id2);
						post = Accesobd.obtenerPost(id3);

						Accesobd.actualizarLike(id, usuario, post);

						System.out.println(
								ConsoleColors.GREEN + "Like actualizado correctamente." + ConsoleColors.RESET);

					} catch (Exception e) {
						System.out.println(
								ConsoleColors.RED + "Error: No se pudo modificar el Like." + ConsoleColors.RESET);
					}
					
					break;

				}

				break;

			case 4:

				menuTabla();

				userInput = sc.nextInt();

				sc.nextLine();

				eleccionTabla = userInput;
				
				
				switch (eleccionTabla) {
				
				
				case 1:
					
					System.out.println("Introduzca la ID del usuario a eliminar.");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println(ConsoleColors.RED_BACKGROUND + "¡ATENCIÓN!" + ConsoleColors.RESET);
					System.out.println(ConsoleColors.YELLOW + "Estás a punto de eliminar a un usuario que contiene los siguientes datos: "
							+ ConsoleColors.RESET);
					
					try {
						Accesobd.leerUsuario(id);
						
						System.out.println(ConsoleColors.YELLOW + "¿Quieres confirmar estos cambios?" + ConsoleColors.RESET);
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT
								+ "Recuerda que una vez confirmados estos cambios, no podrán revertirse.");
						System.out.println(ConsoleColors.YELLOW
								+ "Escriba: \"0\" si no quiere eliminar a dicho usuario, o escriba: \"1\" si quiere confirmar su eliminación."
								+ ConsoleColors.RESET);

						userInput = sc.nextInt();

						sc.nextLine();

						if (userInput == 1) {
							try {
								state = Accesobd.borrarUsuario(id);

								if(!state) {
									System.out.println(
											ConsoleColors.GREEN + "Usuario eliminado correctamente." + ConsoleColors.RESET);
								} else {
									System.out.println(ConsoleColors.RED
											+ "No pudo eliminarse al usuario. Puede ser que dicho usuario no exista en la BBDD."
											+ ConsoleColors.RESET);
								}
								
								
								
							} catch (Exception e) {
								System.out.println(ConsoleColors.RED
										+ "No pudo eliminarse al usuario. Puede ser que dicho usuario no exista en la BBDD."
										+ ConsoleColors.RESET);
							}

						} else {
							System.out.println(ConsoleColors.CYAN + "Cambios revertidos. No se ha efectuado ningún cambio."
									+ ConsoleColors.RESET);
						}
						
					} catch (Exception e) {
						System.out.println(ConsoleColors.RED + "Error: El usuario que has especificado no existe en la BBDD.");
					}

					

					
					
					break;
					
				
				case 2:
					
					System.out.println("Introduzca la ID del post a eliminar.");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println(ConsoleColors.RED_BACKGROUND + "¡ATENCIÓN!" + ConsoleColors.RESET);
					System.out.println(ConsoleColors.YELLOW + "Estás a punto de eliminar un post que contiene los siguientes datos: "
							+ ConsoleColors.RESET);
					
					try {
						Accesobd.leerPost(id);
						
						System.out.println(ConsoleColors.YELLOW + "¿Quieres confirmar estos cambios?" + ConsoleColors.RESET);
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT
								+ "Recuerda que una vez confirmados estos cambios, no podrán revertirse.");
						System.out.println(ConsoleColors.YELLOW
								+ "Escriba: \"0\" si no quiere eliminar dicho post, o escriba: \"1\" si quiere confirmar su eliminación."
								+ ConsoleColors.RESET);

						userInput = sc.nextInt();

						sc.nextLine();

						if (userInput == 1) {
							try {
								state = Accesobd.borrarPost(id);
								
								if(!state) {

									System.out.println(
											ConsoleColors.GREEN + "Post eliminado correctamente." + ConsoleColors.RESET);
								} else {
									System.out.println(ConsoleColors.RED
											+ "No pudo eliminarse el post. Puede ser que dicho post no exista en la BBDD."
											+ ConsoleColors.RESET);
								}

							} catch (Exception e) {
								System.out.println(ConsoleColors.RED
										+ "No pudo eliminarse el post. Puede ser que dicho post no exista en la BBDD."
										+ ConsoleColors.RESET);
							}

						} else {
							System.out.println(ConsoleColors.CYAN + "Cambios revertidos. No se ha efectuado ningún cambio."
									+ ConsoleColors.RESET);
						}
						
					} catch (Exception e) {
						System.out.println(ConsoleColors.RED + "Error: El post que has especificado no existe en la BBDD.");
					}

					

					
					
					break;
					
				case 3:
					
					System.out.println("Introduzca la ID del Like a eliminar.");

					id = sc.nextInt();

					sc.nextLine();

					System.out.println(ConsoleColors.RED_BACKGROUND + "¡ATENCIÓN!" + ConsoleColors.RESET);
					System.out.println(ConsoleColors.YELLOW + "Estás a punto de eliminar un like que contiene los siguientes datos: "
							+ ConsoleColors.RESET);
					
					try {
						Accesobd.leerLike(id);
						
						System.out.println(ConsoleColors.YELLOW + "¿Quieres confirmar estos cambios?" + ConsoleColors.RESET);
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT
								+ "Recuerda que una vez confirmados estos cambios, no podrán revertirse.");
						System.out.println(ConsoleColors.YELLOW
								+ "Escriba: \"0\" si no quiere eliminar dicho like, o escriba: \"1\" si quiere confirmar su eliminación."
								+ ConsoleColors.RESET);

						userInput = sc.nextInt();

						sc.nextLine();

						if (userInput == 1) {
							try {
								state = Accesobd.borrarLike(id);
								
								if (!state) {

									System.out.println(
											ConsoleColors.GREEN + "Like eliminado correctamente." + ConsoleColors.RESET);
								} else {
									System.out.println(ConsoleColors.RED
											+ "No pudo eliminarse el Like. Puede ser que dicho like no exista en la BBDD."
											+ ConsoleColors.RESET);
								}

							} catch (Exception e) {
								System.out.println(ConsoleColors.RED
										+ "No pudo eliminarse el Like. Puede ser que dicho like no exista en la BBDD."
										+ ConsoleColors.RESET);
							}

						} else {
							System.out.println(ConsoleColors.CYAN + "Cambios revertidos. No se ha efectuado ningún cambio."
									+ ConsoleColors.RESET);
						}
						
					} catch (Exception e) {
						System.out.println(ConsoleColors.RED + "Error: El like que has especificado no existe en la BBDD.");
					}

					

					
					
					break;
				
				}
				

				
				
				break;
			}
		}

		System.out.println(ConsoleColors.CYAN + "Saliendo del programa..." + ConsoleColors.RESET);

		sc.close();

	}

	private static void Menu() {
		System.out.println(ConsoleColors.CYAN + "1. Leer Tabla." + ConsoleColors.RESET);
		System.out.println(ConsoleColors.CYAN + "2. Insertar datos en Tablas." + ConsoleColors.RESET);
		System.out.println(ConsoleColors.CYAN + "3. Editar datos de una Tabla." + ConsoleColors.RESET);
		System.out.println(ConsoleColors.CYAN + "4. Borrar datos Tabla." + ConsoleColors.RESET);
		System.out.println(ConsoleColors.CYAN + "5. Salir." + ConsoleColors.RESET);
	}

	private static void menuTabla() {
		System.out.println("¿Con qué tabla quieres interactuar?");
		System.out.println("1. Usuarios");
		System.out.println("2. Posts");
		System.out.println("3. Likes");
		System.out.println("Introduzca el número correspondiente a la tabla.");
	}
}
