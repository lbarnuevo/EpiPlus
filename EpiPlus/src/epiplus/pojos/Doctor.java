package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Doctor implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8279974867568397173L;
	
	private Integer id;
	private String name;
	private String email;
	private String hospitalName;
	private byte[] photo; 
	private List<Patient> patients; //One to many relationship 
	//private Integer user_id;
	
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
	
	public Doctor (String name, String email, String hospitalName, byte[] photo/*,Integer user*/) {
		super();
		this.name = name;
		this.email = email;
		this.hospitalName = hospitalName;
		this.photo= photo;
		//this.user_id= user;
		
		this.patients = new ArrayList<Patient>();
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
	
	public void addPatienttoList(Patient p) {
		this.patients.add(p);
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
		return "DOCTOR [ID=" + id + ", NAME=" + name + "]" + "\nEmail=" + email + "\nHospitalName=" + hospitalName
				+ "\nPhoto=" + Arrays.toString(photo); //TODO show photo
	}

	public String toStringForPatients() {
		return "DOCTOR [ID=" + id + ", NAME=" + name + "]" + "\nEmail=" + email + "\nHospitalName=" + hospitalName;
	}
}