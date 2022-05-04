package epiplus.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
	
	

public class PatientAllergy implements Serializable{
	
	private static final long serialVersionUID = 3645664353573792203L;

	private Integer patientId;
	private Integer allergyId;
	
	private List<Allergy> allergies; //Many to many relationship 
	private List<Patient> patients; //Many to many relationship 

	//MANDATORY CONSTRUCTOR 
	public PatientAllergy() {
		super();
	}

	public PatientAllergy(Integer patientId, Integer allergyId, List<Allergy> allergies, List<Patient> patients) {
		super();
		this.patientId = patientId;
		this.allergyId = allergyId;
		this.allergies = allergies;
		this.patients = patients;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergyId, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientAllergy other = (PatientAllergy) obj;
		return Objects.equals(allergyId, other.allergyId) && Objects.equals(patientId, other.patientId);
	}

	@Override
	public String toString() {
		return "PatientAllergies [allergies=" + allergies + ", patients=" + patients + "]";
	}
}
