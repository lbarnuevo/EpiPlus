package epiplus.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
	
	
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PatientAllergy")
@XmlType(propOrder = { "allergy","patient"})
public class PatientAllergy implements Serializable{
	
	private static final long serialVersionUID = 3645664353573792203L;

	@XmlElement
	private Allergy allergy;
	@XmlElement
	private Patient patient;
 
	public PatientAllergy() {
		super();
	}

	public PatientAllergy(Allergy allergy, Patient patient) {
		super();
		this.allergy = allergy;
		this.patient = patient;
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
		return "PatientAllergy [allergy = " + allergy + ", patient = " + patient + "]";
	}
	
	

}
