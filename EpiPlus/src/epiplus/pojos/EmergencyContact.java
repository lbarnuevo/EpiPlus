package epiplus.pojos;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EmergencyContact")
@XmlType(propOrder = {"id", "name", "number","patient"} )
public class EmergencyContact {

	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String number;
	@XmlElement
	private Patient patient;
	
	public EmergencyContact() {
		super();
	}

	public EmergencyContact(Integer id, String name, String number) {
		super();
		this.id= id;
		this.name=name;
		this.number=number;
	}
	
	public EmergencyContact(String name, String number, Patient patient) {
		super();
		this.name=name;
		this.number=number;
		this.patient=patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		EmergencyContact other = (EmergencyContact) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EMERGENCY CONTACT [ID = " + id + ", NAME = " + name + "]" + "\nNumber = " + number;
	}
}
