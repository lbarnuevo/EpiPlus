package epiplus.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PatientMedication")
@XmlType(propOrder = { "frequency", "amount","patient","medication"})
public class PatientMedication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8962678955441389723L;
	
	@XmlAttribute
	private Integer frequency;
	@XmlAttribute
	private Float amount; 
	@XmlElement
	private Patient patient;
	@XmlElement
	private Medication medication;
	
	public PatientMedication() {
		super();
	}
	
	public PatientMedication(Patient patient, Medication medication) {
		super();
		this.frequency = null;
		this.amount = null; 
		this.patient = patient;
		this.medication = medication;
	}
	
	public PatientMedication(Integer frequency, Float amount, Patient patient, Medication medication) {
		super();
		this.frequency = frequency;
		this.amount = amount; 
		this.patient = patient;
		this.medication = medication;
	}
	
	public PatientMedication(Integer freq, Float amount) {
		super();
		this.frequency = freq;
		this.amount = amount; 
		this.patient = null;
		this.medication = null;
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
		return "Frequency = " + frequency + ", amount = " + amount;
	}
	
}
