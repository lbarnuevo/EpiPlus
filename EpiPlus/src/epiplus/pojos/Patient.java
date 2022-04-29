package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private String lifestyle; //He explained to me how to do it, but it's a task for the future
	private String diet; 
	private Integer ex_per_week;
	private Doctor doctor; //Many to one relationship 
	private byte[] photo;
	private List<EmergencyContact> emergency_contacts; //Changed Integer to EC class 
	private List<Episode> episodes; //Many to one relationship 
	private List<Medication> medication; //Many to many relationship  
	

	public Patient (Integer id, String name, Integer age, Float height, Float weight, 
			String lifestyle, String diet, Integer exercise, byte[] photo) {
		super();
		this.id= id;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight= weight;
		this.lifestyle= lifestyle;
		this.diet= diet;
		this.ex_per_week= exercise;
		this.photo= photo;
		episodes = new ArrayList<Episode>();
		medication = new ArrayList<Medication>();
		emergency_contacts = new ArrayList<EmergencyContact>();
	}
	
	public Patient (String name, Integer age, Float height, Float weight, 
			String lifestyle, String diet, Integer exercise, byte[] photo) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight= weight;
		this.lifestyle= lifestyle;
		this.diet= diet;
		this.ex_per_week= exercise;
		this.photo= photo;
		episodes = new ArrayList<Episode>();
		medication = new ArrayList<Medication>();
		emergency_contacts = new ArrayList<EmergencyContact>();
	}
	
	public Patient (String name, Integer age, Float height, Float weight, 
			String lifestyle, String diet, Integer exercise) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight= weight;
		this.lifestyle= lifestyle;
		this.diet= diet;
		this.ex_per_week= exercise;
		this.photo= null;
		episodes = new ArrayList<Episode>();
		medication = new ArrayList<Medication>();
		emergency_contacts = new ArrayList<EmergencyContact>();
	}
	
	public List<Episode> getEpisodes() {
		return episodes;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public void setMedication(List<Medication> medication) {
		this.medication = medication;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public List<EmergencyContact> getEmergency_contacts() {
		return emergency_contacts;
	}

	public void setEmergency_contacs(List<EmergencyContact> emergency_contacts) {
		this.emergency_contacts = emergency_contacts;
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
				+ ", photo=" + Arrays.toString(photo) + ", emergency_contacts=" + emergency_contacts + ", episodes="
				+ episodes + ", medication=" + medication + "]";
	}
}
