package epiplus.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Episodes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5004715535383971325L;
	
	private Integer id;
	private Date doe; //doe = date of episode 
	private Float lenght; //SHOULD CHANGE THIS IN THE SQL VERSION (DB BROWSER)
	private String activity;
	private String mood;
	private String place;
	private String previous_meal;
	private Boolean injuries;
	
	private Patient patient; // Many to one relationship 

	public Episodes() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

	public Float getLenght() {
		return lenght;
	}

	public void setLenght(Float lenght) {
		this.lenght = lenght;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPrevious_meal() {
		return previous_meal;
	}

	public void setPrevious_meal(String previous_meal) {
		this.previous_meal = previous_meal;
	}

	public Boolean getInjuries() {
		return injuries;
	}

	public void setInjuries(Boolean injuries) {
		this.injuries = injuries;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
		Episodes other = (Episodes) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Episodes [id=" + id + ", doe=" + doe + ", lenght=" + lenght + ", activity=" + activity + ", mood="
				+ mood + ", place=" + place + ", previous_meal=" + previous_meal + ", injuries=" + injuries
				+ ", patient=" + patient + "]";
	}
	
}
