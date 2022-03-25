package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medication implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574063760306956061L;
	
	private Integer id;
	private String name;
	private List<Patient> patient;//Many to many relationship 
	
	public Medication() {
		super();
		patient = new ArrayList<Patient>();
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
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
		Medication other = (Medication) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", patient=" + patient + "]";
	}
	
}
