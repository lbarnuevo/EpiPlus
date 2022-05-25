package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Medication")
@XmlType(propOrder = { "name", "patient"} )
public class Medication implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574063760306956061L;
	
	private Integer id;
	
	@XmlAttribute
	private String name;
	
	@XmlElement(name = "Patient")
	@XmlElementWrapper(name = "Patients")
	private List<Patient> patient;//Many to many relationship 
	
	public Medication() {
		super();
		this.patient = new ArrayList<Patient>();
	}

	public Medication(Integer id, String name) {
		super();
		this.id= id;
		this.name= name;
		this.patient = new ArrayList<Patient>();
	}
	
	public Medication(String name) {
		super();
		this.name= name;
		this.patient = new ArrayList<Patient>();
	}

	public void addPatient(Patient p) {
		if(!patient.contains(p)) {
			patient.add(p);
		}
	}
	
	public void removePatient(Patient p) {
		if(patient.contains(p)) {
			patient.remove(p);
		}
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
		return "MEDICATION [ID=" + id + ", NAME=" + name + "]";
	}
	
}
