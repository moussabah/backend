package springboot.projetfinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authentification")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Authentification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String login;
	private String password;
	@Version
	private int version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Authentification() {
		super();
	}
	public Authentification(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
}
