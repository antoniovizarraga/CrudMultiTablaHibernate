package hibernate2.crudmultitabla;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Accesobd {
	private static SessionFactory sf;
	private static Session sesion;
	private static Transaction transaction;
	private static boolean fk = false;

	protected static void setUp() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // por defecto:
																									// hibernate.cfg.xml
				.build();
		try {
			sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

	public static Session abrir() throws Exception {
		setUp();
		sesion = sf.openSession();
		transaction = sesion.beginTransaction();

		return sesion;
	}

	public static void cerrar() {
		try {
			transaction.commit();
		} catch (Exception e) {

			if (e instanceof PersistenceException) {
				System.out.println(ConsoleColors.RED
						+ "Error: No se pudo eliminar o editar la fila porque contiene una Foreign Key no válida."
						+ "Borra primero la fila en la tabla a la que hace referencia para poder borrar esta fila.\nO si estabas editando la fila e intentaste añadir una Foreign Key no válida, no tienes que hacer nada. Los cambios fueron desechados automáticamente."
						+ ConsoleColors.RESET);
				fk = true;
			} else {
				fk = false;
			}

			transaction.rollback();
		}
		sf.close();
	}

	/* Métodos para gestionar usuarios */

	public static void guardarUsuario(EntidadUsuario usuario) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(usuario);

		cerrar();
	}

	// Leer Persona
	public static void leerUsuario(int id) throws Exception {
		sesion = abrir();
		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);// PersonasEntity persona =
																		// session.get(PersonasEntity.class, id); //
																		// Esta línea también funcionaría como la
																		// anterior
		System.out.println(usuario);
		cerrar();
	}

	public static EntidadUsuario obtenerUsuario(int id) throws Exception {
		sesion = abrir();

		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);

		cerrar();

		return usuario;
	}

	// Leer Persona con todos sus datos
	public static void leerListaUsuarios() throws Exception {
		sesion = abrir();

		CriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<EntidadUsuario> cq = cb.createQuery(EntidadUsuario.class);
		Root<EntidadUsuario> rootEntry = cq.from(EntidadUsuario.class);
		CriteriaQuery<EntidadUsuario> all = cq.select(rootEntry);

		TypedQuery<EntidadUsuario> allQuery = sesion.createQuery(all);

		List<EntidadUsuario> listado = allQuery.getResultList();

		// List<EntidadPersona> listado = sesion.createQuery("SELECT * FROM Persona",
		// EntidadPersona.class).getResultList();

		listado.forEach((usuarioListado) -> {

			if (usuarioListado != null) {
				System.out.println(usuarioListado);
			}

		});

		cerrar();
	}

	public static List leerUsuarioNombre(String name) {
		try {
			sesion = abrir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List listado = sesion.createQuery("SELECT c FROM EntidadUsuario c WHERE nombre LIKE :nombreUser")
				.setParameter("nombreUser", name).setMaxResults(10).getResultList();

		cerrar();

		return listado;
	}

	public static List leerUsuarioApellidos(String apellidos) {

		try {
			sesion = abrir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List listado = sesion.createQuery("SELECT c FROM EntidadUsuario c WHERE apellidos LIKE :apellidosUser")
				.setParameter("apellidosUser", apellidos).setMaxResults(10).getResultList();

		cerrar();

		return listado;
	}

	public static List leerUsuarioUsername(String username) {

		try {
			sesion = abrir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List listado = sesion.createQuery("SELECT c FROM EntidadUsuario c WHERE username LIKE :usernameUser")
				.setParameter("usernameUser", username).setMaxResults(10).getResultList();

		cerrar();

		return listado;
	}

	// Actualizar Persona
	public static void actualizarUsuario(int id, String nombre, String apellidos, String username, String password,
			String email) throws Exception {
		sesion = abrir();
		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);

		System.out.println(usuario);

		// session.saveOrUpdate(persona); // session.merge(persona);
		sesion.update(usuario);
		cerrar();
	}

	// Borrar persona
	public static boolean borrarUsuario(int id) throws Exception {

		boolean res;

		sesion = abrir();
		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);
		sesion.delete(usuario);

		cerrar();

		res = fk;

		return res;
	}

	/* Fin métodos Usuarios */

	/* Métodos para gestionar Posts */

	public static void guardarPost(EntidadPost post) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(post);

		cerrar();

		System.out.println(id);

	}

	// Leer Persona
	public static void leerPost(int id) throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.load(EntidadPost.class, id);// PersonasEntity persona =
																// session.get(PersonasEntity.class, id); //
																// Esta línea también funcionaría como la
																// anterior
		System.out.println(post);
		cerrar();
	}

	public static EntidadPost obtenerPost(int id) throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.get(EntidadPost.class, id);

		cerrar();

		return post;
	}

	// Leer Persona con todos sus datos
	public static void leerListaPosts() throws Exception {
		sesion = abrir();

		CriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<EntidadPost> cq = cb.createQuery(EntidadPost.class);
		Root<EntidadPost> rootEntry = cq.from(EntidadPost.class);
		CriteriaQuery<EntidadPost> all = cq.select(rootEntry);

		TypedQuery<EntidadPost> allQuery = sesion.createQuery(all);

		List<EntidadPost> listado = allQuery.getResultList();

		// List<EntidadPersona> listado = sesion.createQuery("SELECT * FROM Persona",
		// EntidadPersona.class).getResultList();

		listado.forEach((postListado) -> {

			if (postListado != null) {
				System.out.println(postListado);
			}

		});

		cerrar();
	}

	// Actualizar Persona
	public static void actualizarPost(int id, EntidadUsuario usuario, LocalDate createdAt, LocalDate updatedAt)
			throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.get(EntidadPost.class, id);
		post.setUsuario(usuario);
		post.setCreatedAt(createdAt);
		post.setUpdatedAt(updatedAt);
		sesion.update(post);
		cerrar();
	}

	// Borrar persona
	public static boolean borrarPost(int id) throws Exception {

		boolean res;

		sesion = abrir();
		EntidadPost post = sesion.get(EntidadPost.class, id);
		sesion.delete(post);

		cerrar();

		res = fk;

		return fk;
	}

	/* Fin métodos Posts */

	/* Métodos para gestionar Likes */

	public static void guardarLike(EntidadLike like) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(like);
		cerrar();
		System.out.println(id);

	}

	// Leer Persona
	public static void leerLike(int id) throws Exception {
		sesion = abrir();
		EntidadLike like = sesion.load(EntidadLike.class, id);// PersonasEntity persona =
																// session.get(PersonasEntity.class, id); //
																// Esta línea también funcionaría como la
																// anterior
		System.out.println(like);
		cerrar();
	}

	// Leer Persona con todos sus datos
	public static void leerListaLikes() throws Exception {
		sesion = abrir();

		CriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<EntidadLike> cq = cb.createQuery(EntidadLike.class);
		Root<EntidadLike> rootEntry = cq.from(EntidadLike.class);
		CriteriaQuery<EntidadLike> all = cq.select(rootEntry);

		TypedQuery<EntidadLike> allQuery = sesion.createQuery(all);

		List<EntidadLike> listado = allQuery.getResultList();

		// List<EntidadPersona> listado = sesion.createQuery("SELECT * FROM Persona",
		// EntidadPersona.class).getResultList();

		listado.forEach((likeListado) -> {

			if (likeListado != null) {
				System.out.println(likeListado);
			}

		});

		cerrar();
	}

	public static void actualizarLike(int id, EntidadUsuario usuario, EntidadPost post) throws Exception {
		sesion = abrir();
		EntidadLike like = sesion.get(EntidadLike.class, id);
		like.setUsuario(usuario);
		like.setPost(post);
		// session.saveOrUpdate(persona); // session.merge(persona);
		sesion.update(like);
		cerrar();
	}

	// Borrar persona
	public static boolean borrarLike(int id) throws Exception {

		boolean res;

		sesion = abrir();
		EntidadLike like = sesion.get(EntidadLike.class, id);
		sesion.delete(like);

		cerrar();

		res = fk;

		return res;
	}
	
	public static void dropearTabla(int tabla) {
		

		
		try {
			sesion = abrir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (tabla) {
		
		case 1:
			sesion.createNativeQuery("DROP TABLE Usuarios").executeUpdate();
			System.out.println(ConsoleColors.GREEN + "Tabla Usuarios eliminada correctamente." + ConsoleColors.RESET);
			break;
			
		case 2:
			sesion.createNativeQuery("drop table Posts").executeUpdate();
			System.out.println(ConsoleColors.GREEN + "Tabla Posts eliminada correctamente." + ConsoleColors.RESET);
			break;
			
		case 3:
			sesion.createNativeQuery("drop table Likes").executeUpdate();
			System.out.println(ConsoleColors.GREEN + "Tabla Likes eliminada correctamente." + ConsoleColors.RESET);
			break;
		
		}
	}

	/* Fin métodos Likes */
}
