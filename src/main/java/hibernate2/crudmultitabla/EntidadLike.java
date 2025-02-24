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
	@JoinColumn(name = "idUsuario")
	private EntidadUsuario usuario;

	@ManyToOne
	@JoinColumn(name = "idPosts")
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

}