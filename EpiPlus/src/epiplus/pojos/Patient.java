package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sample.db.xml.utils.SQLDateAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType(propOrder = { "id","role_id","name", "email","birthday","height","weight","lifestyle","diet","ex_per_week","doctor","photo","emergency_contacts","episodes","medication","allergy"} )
public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234593157068704294L;

	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private Integer role_id;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String email;
	
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date birthday;
	
	@XmlAttribute
	private Float height;
	
	@XmlAttribute
	private Float weight;
	
	@XmlElement
	private String lifestyle;
	
	@XmlElement
	private String diet;
	
	@XmlAttribute
	private Integer ex_per_week;
	
	@XmlElement
	private Doctor doctor; // Many to one relationship
	
	@XmlElement
	private byte[] photo;

	@XmlElement(name = "EmergencyContact")
	@XmlElementWrapper(name = "emergency_contacts")
	private List<EmergencyContact> emergency_contacts; //One to many relationship
	
	@XmlElement(name = "Episode")
	@XmlElementWrapper(name = "episodes")
	private List<Episode> episodes; // Many to one relationship
	
	@XmlElement(name = "Medication")
	@XmlElementWrapper(name = "medication")
	private List<Medication> medication; // Many to many relationship
	
	@XmlElement(name = "Allergy")
	@XmlElementWrapper(name = "allergy")
	private List<Allergy> allergy; // Many to many relationship

	// MANDATORY CONSTRUCTOR
	public Patient() {
		super();
		this.episodes = new ArrayList<Episode>();
		this.medication = new ArrayList<Medication>();
		this.emergency_contacts = new ArrayList<EmergencyContact>();
		this.allergy = new ArrayList<Allergy>();
	}

	public Patient(Integer id, String name, String email, Date birthday, Float height, Float weight, String lifestyle,
			String diet, Integer exercise, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.lifestyle = lifestyle;
		this.diet = diet;
		this.ex_per_week = exercise;
		this.photo = photo;
		this.doctor = null;

		this.episodes = new ArrayList<Episode>();
		this.medication = new ArrayList<Medication>();
		this.emergency_contacts = new ArrayList<EmergencyContact>();
		this.allergy = new ArrayList<Allergy>();
	}

	public Patient(String name, String email, Date birthday, Float height, Float weight, String lifestyle, String diet,
			Integer exercise, byte[] photo,Integer user) {
		super();
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.lifestyle = lifestyle;
		this.diet = diet;
		this.ex_per_week = exercise;
		this.photo = photo;
		this.doctor = null;
		this.role_id = user;

		this.episodes = new ArrayList<Episode>();
		this.medication = new ArrayList<Medication>();
		this.emergency_contacts = new ArrayList<EmergencyContact>();
		this.allergy = new ArrayList<Allergy>();
	}

	public void addEC(EmergencyContact ec) {
		if(!emergency_contacts.contains(ec) ) {
			emergency_contacts.add(ec);
		}
	}
	
	public void removeEC(EmergencyContact ec) {
		if(emergency_contacts.contains(ec)) {
			emergency_contacts.remove(ec);
		}
	}
	
	public void addMedication(Medication m) {
		if(!medication.contains(m)) {
			medication.add(m);
		}
	}
	
	public void removeMedication(Medication m) {
		if(medication.contains(m)) {
			medication.remove(m);
		}
	}
	
	public void addEpisodes(Episode e) {
		if(!episodes.contains(e) ) {
			episodes.add(e);
		}
	}
	
	public void removeEpisodes(Episode e) {
		if(episodes.contains(e) ) {
			episodes.remove(e);
		}
	}
	
	public void addAllergy(Allergy a) {
		if(!allergy.contains(a)) {
			allergy.add(a);
		}
	}
	
	public void removeAllergy(Allergy a) {
		if(allergy.contains(a)) {
			allergy.remove(a);
		}
	}
	
	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public List<EmergencyContact> getEmergency_contacts() {
		return emergency_contacts;
	}

	public void setEmergency_contacs(List<EmergencyContact> emergency_contacts) {
		this.emergency_contacts = emergency_contacts;
	}

	public List<Allergy> getAllergy() {
		return allergy;
	}

	public void setAllergy(List<Allergy> allergy) {
		this.allergy = allergy;
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
		return "PATIENT [ID = " + this.id + ", NAME = " + this.name + "]" + "\nEmail = " + this.email + "\nDate of birth = " + this.birthday + "\nHeight = "
				+ this.height + "\nWeight = " + this.weight + "\nLifestyle = " + this.lifestyle + "\nDiet = " + this.diet
				+ "\nExercise per week (hours) = " + this.ex_per_week;
	}

	public String toStringForDoctors() {
		return "PATIENT [ID = " + this.id + ", NAME = " + this.name + "]" + "\nEmail = " + this.email + "\nDate of birth = " + this.birthday;
	}
}
