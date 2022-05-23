package epiplus.pojos;

import java.util.Objects;

public class EmergencyContact {

	private Integer id;
	private String name;
	private Float number;
	private Patient patient;
	
	public EmergencyContact() {
		super();
	}

	public EmergencyContact(Integer id, String name, Float number) {
		super();
		this.id= id;
		this.name=name;
		this.number=number;
	}
	
	public EmergencyContact(String name, Float number, Patient patient) {
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

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
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
