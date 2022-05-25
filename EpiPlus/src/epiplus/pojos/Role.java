package epiplus.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "roles")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Role")
@XmlType(propOrder = { "name", "users"})
public class Role implements Serializable {

	private static final long serialVersionUID = 3815457760670380250L;
	
	@Id
	@GeneratedValue(generator = "roles")
	@TableGenerator(name = "roles", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "roles")
	
	private Integer id;
	@XmlAttribute
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	@XmlElement(name = "User")
	@XmlElementWrapper(name = "users")
	
	private List<User> users;
	
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}
	}
	
	public void removeUser(User u) {
		if (users.contains(u)) {
			users.remove(u);
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
