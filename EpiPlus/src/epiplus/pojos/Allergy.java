package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Allergy")
@XmlType(propOrder = { "name", "patients"} )
public class Allergy implements Serializable{
	
	private static final long serialVersionUID = -935621693802822621L;

	//@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String name;
	
	
	@XmlElement(name = "Patient")
	@XmlElementWrapper(name = "Patients")
	private List<Patient> patients; //Many to many relationship 

	public Allergy() {
		super();
		this.patients = new ArrayList<Patient>();
	}

	public Allergy(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.patients = new ArrayList<Patient>();
	}
	
	public Allergy(String name) {
		super();
		this.name = name;
		this.patients = new ArrayList<Patient>();
	}
	
	public void addPatient(Patient p) {
		patients.add(p);
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
	
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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
		Allergy other = (Allergy) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Allergy [ID = " + id + ", NAME = " + name + "]";
	}
}


