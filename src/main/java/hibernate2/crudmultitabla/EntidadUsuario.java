package hibernate2.crudmultitabla;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class EntidadUsuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // La opción más usada con MySQL
	@Column(name = "idUsuario")
	private int idUsuario;

	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Apellidos")
	private String apellidos;
	@Column(name = "Username")
	private String username;

	@Column(name = "Password")
	private String password;

	@Column(name = "email")
	private String email;

	public EntidadUsuario() {
	}

	public EntidadUsuario(String nombre, String apellidos, String username, String password, String email) {

		setNombre(nombre);
		setApellidos(apellidos);
		setUsername(username);
		setPassword(password);
		setEmail(email);

	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public String toString() {
		String res = "";
		
		res = ConsoleColors.GREEN + "ID: " + ConsoleColors.RESET + Integer.toString(idUsuario) + "\n" +
				ConsoleColors.GREEN + "Nombre: " + ConsoleColors.RESET + nombre + "\n" +
				ConsoleColors.GREEN + "Apellidos: " + ConsoleColors.RESET + apellidos + "\n" +
				ConsoleColors.GREEN + "Usuario: " + ConsoleColors.RESET + username + "\n" +
				ConsoleColors.GREEN + "Contraseña: " + ConsoleColors.RESET + password + "\n" +
				ConsoleColors.GREEN + "Correo electrónico: " + ConsoleColors.RESET + email + "\n" +
				ConsoleColors.BLACK + "-----------------------" + "\n" + ConsoleColors.RESET;
		
		return res;
	}
}
