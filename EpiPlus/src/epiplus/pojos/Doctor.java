package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "name", "email","hospitalName","photo","patients"} )
public class Doctor implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8279974867568397173L;
	//@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private String hospitalName;
	@XmlElement
	private byte[] photo; 

	@XmlElement(name = "Patient")
	@XmlElementWrapper(name = "Patients")
	private List<Patient> patients; //One to many relationship 
	private Integer role_id;
	
	public Doctor() {
		super();
		this.patients = new ArrayList<Patient>();
	}
	
	public Doctor (Integer id, String name, String email, String hospitalName, byte[] photo) {
		super();
		this.id= id;
		this.name = name;
		this.email = email;
		this.hospitalName = hospitalName;
		this.photo= photo;
		this.patients = new ArrayList<Patient>();
	}
	
	public Doctor (String name, String email, String hospitalName, byte[] photo, Integer user) {
		super();
		this.name = name;
		this.email = email;
		this.hospitalName = hospitalName;
		this.photo= photo;
		this.role_id = user;
		
		this.patients = new ArrayList<Patient>();
	}
	
	public void addPatient(Patient p) {
		if(!patients.contains(p)) {
			patients.add(p);
		}
	}
	
	public void removePatient(Patient p) {
		if(patients.contains(p)) {
			patients.remove(p);
		}
	}
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getUser_id() {
		return role_id;
	}

	public void setUser_id(Integer user_id) {
		this.role_id = user_id;
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
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DOCTOR [ID = " + id + ", NAME = " + name + "]" + "\nEmail = " + email + "\nHospitalName = " + hospitalName;
	}
}