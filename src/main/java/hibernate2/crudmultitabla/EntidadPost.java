package hibernate2.crudmultitabla;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EntidadPost implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // La opción más usada con MySQL
	@Column(name = "idPosts")
	private int idPosts;
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private EntidadUsuario usuario;

	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.DATE)
	private Date updatedAt;

	public EntidadPost() {
	}

	public EntidadPost(EntidadUsuario usuario, Date createdAt, Date updatedAt) {

		setUsuario(usuario);
		setCreatedAt(createdAt);
		setUpdatedAt(updatedAt);

	}

	public int getIdPosts() {
		return idPosts;
	}

	public void setIdPosts(int idPosts) {
		this.idPosts = idPosts;
	}

	public EntidadUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(EntidadUsuario usuario) {
		this.usuario = usuario;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}