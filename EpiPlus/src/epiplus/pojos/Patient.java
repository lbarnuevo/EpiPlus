package epiplus.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2234593157068704294L;
	
	private Integer id;
	private String name;
	private Integer age;
	private Float height;
	private Float weight;
	private String lifestyle; //Could we do a class that emulates the create datatype?
	private String diet; //Same question 
	private Integer ex_per_week;
	private Doctor doctor; //Many to one relationship 
	// How do we add photo?
	private Integer emergency_contact; //SHOULD CHANGE THIS IN THE SQL VERSION (DB BROWSER) 
	
	public Patient() {
		super();
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public Integer getEx_per_week() {
		return ex_per_week;
	}

	public void setEx_per_week(Integer ex_per_week) {
		this.ex_per_week = ex_per_week;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Integer getEmergency_contact() {
		return emergency_contact;
	}

	public void setEmergency_contact(Integer emergency_contact) {
		this.emergency_contact = emergency_contact;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight
				+ ", lifestyle=" + lifestyle + ", diet=" + diet + ", ex_per_week=" + ex_per_week + ", doctor=" + doctor
				+ ", emergency_contact=" + emergency_contact + "]";
	}
}
