package hibernate2.crudmultitabla;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Likes")
public class EntidadLike implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // La opción más usada con MySQL
	@Column(name = "idLikes")
	private int idLikes;
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private EntidadUsuario usuario;

	@ManyToOne
	@JoinColumn(name = "idPosts", nullable = false)
	private EntidadPost post;

	public EntidadLike() {
	}

	public EntidadLike(EntidadUsuario usuario, EntidadPost post) {

		setUsuario(usuario);
		setPost(post);

	}

	public int getIdLikes() {
		return idLikes;
	}

	public void setIdLikes(int idLikes) {
		this.idLikes = idLikes;
	}

	public EntidadUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(EntidadUsuario usuario) {
		this.usuario = usuario;
	}

	public EntidadPost getPost() {
		return post;
	}

	public void setPost(EntidadPost post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res = ConsoleColors.GREEN + "ID Like: " + ConsoleColors.RESET + Integer.toString(idLikes) + "\n" +
				ConsoleColors.GREEN + "ID Usuario: " + ConsoleColors.RESET + Integer.toString(usuario.getIdUsuario()) + "\n" +
				ConsoleColors.GREEN + "ID Post: " + ConsoleColors.RESET + Integer.toString(post.getIdPosts()) + "\n" +
				ConsoleColors.BLACK + "-----------------------" + "\n" + ConsoleColors.RESET;
		
		return res;
	}

}