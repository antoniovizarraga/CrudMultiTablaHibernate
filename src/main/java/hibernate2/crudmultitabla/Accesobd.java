package hibernate2.crudmultitabla;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Accesobd {
	private static SessionFactory sf;
	private static Session sesion;
	private static Transaction transaction;

	protected static void setUp() {
		// java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
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
			transaction.rollback();
		}
		sf.close();
	}

	/* Métodos para gestionar usuarios */

	public static void guardarUsuario(EntidadUsuario usuario) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(usuario);
		transaction.commit();
		System.out.println(id);
		sf.close();
	}

	// Leer Persona
	public static void leerUsuario(int id) throws Exception {
		sesion = abrir();
		EntidadUsuario usuario = sesion.load(EntidadUsuario.class, id);// PersonasEntity persona =
																		// session.get(PersonasEntity.class, id); //
																		// Esta línea también funcionaría como la
																		// anterior
		System.out.println("Nombre: " + usuario.getNombre());
		System.out.println("Apellidos: " + usuario.getApellidos());
		System.out.println("Usuario: " + usuario.getUsername());
		System.out.println("Contraseña: " + usuario.getPassword());
		System.out.println("Correo electrónico: " + usuario.getEmail());
		cerrar();
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
				System.out.println("ID: " + Integer.toString(usuarioListado.getIdUsuario()));
				System.out.println("Nombre: " + usuarioListado.getNombre());
				System.out.println("Usuario: " + usuarioListado.getUsername());
				System.out.println("Contraseña: " + usuarioListado.getPassword());
				System.out.println("Correo electrónico: " + usuarioListado.getEmail());
				System.out.println(ConsoleColors.BLACK + "-----------------------" + ConsoleColors.RESET);
			}

		});

		cerrar();
	}

	// Actualizar Persona
	public static void actualizarUsuario(int id, String nombre, String apellidos, String username, String password,
			String email) throws Exception {
		sesion = abrir();
		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);

		// session.saveOrUpdate(persona); // session.merge(persona);
		sesion.update(usuario);
		cerrar();
	}

	// Borrar persona
	public static void borrarUsuario(int id) throws Exception {
		sesion = abrir();
		EntidadUsuario usuario = sesion.get(EntidadUsuario.class, id);
		sesion.delete(usuario);

		cerrar();
	}

	/* Fin métodos Usuarios */

	/* Métodos para gestionar Posts */

	public static void guardarPost(EntidadPost post) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(post);
		transaction.commit();
		System.out.println(id);
		sf.close();
	}

	// Leer Persona
	public static void leerPost(int id) throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.load(EntidadPost.class, id);// PersonasEntity persona =
																// session.get(PersonasEntity.class, id); //
																// Esta línea también funcionaría como la
																// anterior
		System.out.println("Id Post: " + Integer.toString(post.getIdPosts()));
		System.out.println("Id Usuario: " + Integer.toString(post.getUsuario().getIdUsuario()));
		System.out.println("Creado el: " + post.getCreatedAt());
		System.out.println("Actualizado el: " + post.getUpdatedAt());
		cerrar();
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
				System.out.println("Id Post: " + Integer.toString(postListado.getIdPosts()));
				System.out.println("Id Usuario: " + Integer.toString(postListado.getUsuario().getIdUsuario()));
				System.out.println("Creado el: " + postListado.getCreatedAt());
				System.out.println("Actualizado el: " + postListado.getUpdatedAt());
				System.out.println(ConsoleColors.BLACK + "-----------------------" + ConsoleColors.RESET);
			}

		});

		cerrar();
	}

	// Actualizar Persona
	public static void actualizarPost(int id, EntidadUsuario usuario, Date createdAt, Date updatedAt) throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.get(EntidadPost.class, id);
		post.setUsuario(usuario);
		post.setCreatedAt(createdAt);
		post.setUpdatedAt(updatedAt);
		cerrar();
	}

	// Borrar persona
	public static void borrarPost(int id) throws Exception {
		sesion = abrir();
		EntidadPost post = sesion.get(EntidadPost.class, id);
		sesion.delete(post);

		cerrar();
	}

	/* Fin métodos Posts */
	
	
	
	
	
	
	

	/* Métodos para gestionar Likes */

	public static void guardarLike(EntidadLike like) throws Exception {

		sesion = abrir();
		int id = (int) sesion.save(like);
		transaction.commit();
		System.out.println(id);
		sf.close();
	}

	// Leer Persona
	public static void leerLike(int id) throws Exception {
		sesion = abrir();
		EntidadLike like = sesion.load(EntidadLike.class, id);// PersonasEntity persona =
																// session.get(PersonasEntity.class, id); //
																// Esta línea también funcionaría como la
																// anterior
		System.out.println("Id Like: " + Integer.toString(like.getIdLikes()));
		System.out.println("Id Usuario: " + Integer.toString(like.getUsuario().getIdUsuario()));
		System.out.println("Id Post: " + Integer.toString(like.getPost().getIdPosts()));
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
				System.out.println("Id Like: " + Integer.toString(likeListado.getIdLikes()));
				System.out.println("Id Usuario: " + Integer.toString(likeListado.getUsuario().getIdUsuario()));
				System.out.println("Id Post: " + Integer.toString(likeListado.getPost().getIdPosts()));
				System.out.println(ConsoleColors.BLACK + "-----------------------" + ConsoleColors.RESET);
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
	public static void borrarLike(int id) throws Exception {
		sesion = abrir();
		EntidadLike like = sesion.get(EntidadLike.class, id);
		sesion.delete(like);

		cerrar();
	}

	/* Fin métodos Likes */
}
