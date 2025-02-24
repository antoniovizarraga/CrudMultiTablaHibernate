package hibernate2.crudmultitabla;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Posts")
public class EntidadPost implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // La opción más usada con MySQL
	@Column(name = "idPosts")
	private int idPosts;
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private EntidadUsuario usuario;

	@Column(name = "created_at")
	private LocalDate createdAt;

	@Column(name = "updated_at")
	private LocalDate updatedAt;

	public EntidadPost() {
	}

	public EntidadPost(EntidadUsuario usuario, LocalDate createdAt, LocalDate updatedAt) {

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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

}