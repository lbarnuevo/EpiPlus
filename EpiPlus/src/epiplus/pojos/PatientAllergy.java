package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
	
	

public class PatientAllergy implements Serializable{
	
	private static final long serialVersionUID = 3645664353573792203L;

	private Allergy allergy;
	private Patient patient;
 
	public PatientAllergy() {
		super();
	}

	public PatientAllergy(Allergy allergy, Patient patient) {
		super();
		this.allergy = allergy;
		this.patient = patient;
	}

	public PatientAllergy(Patient p, Allergy a) {
		super();
		this.allergy = a;
		this.patient = p;
	}

	public Allergy getAllergy() {
		return allergy;
	}

	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergy, patient);
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
		return Objects.equals(allergy, other.allergy) && Objects.equals(patient, other.patient);
	}

	@Override
	public String toString() {
		return "PatientAllergy [allergy=" + allergy + ", patient=" + patient + "]";
	}
	
	

}
