package springboot.projetfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
@Table(name = "authentifications")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Authentification {
	@Id
	@JsonView(JsonViews.Common.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	@JsonView(JsonViews.Common.class)
	private String login; //login=email
	//@JsonView(JsonViews.Common.class)
	//@JsonIgnore
	private String password;
	@JsonView(JsonViews.Common.class)
	@Version
	protected int version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return "User [id=" + id + ", login=" + login +"]";
	}
}
