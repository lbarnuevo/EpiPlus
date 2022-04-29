package epiplus.pojos;

import java.io.Serializable;
import java.util.Objects;

public class PatientMedication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8962678955441389723L;
	
	private Integer frequency;
	private Float amount; 
	private Patient patient;
	private Medication medication;
	
	public PatientMedication() {
		super();
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	@Override
	public int hashCode() {
		return Objects.hash(medication, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientMedication other = (PatientMedication) obj;
		return Objects.equals(medication, other.medication) && Objects.equals(patient, other.patient);
	}
	
	@Override
	public String toString() {
		return "PatientMedication [frequency=" + frequency + ", amount=" + amount + ", patient=" + patient
				+ ", medication=" + medication + "]";
	}
	
}
