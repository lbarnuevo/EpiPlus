package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symptom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5541692385945227457L;
	
	private Integer id;
	private String name;
	private List<Episode> episodes; //Many to many relationship 
	
	public Symptom() {
		super();
		episodes = new ArrayList<Episode>();
	}

	public Symptom(Integer id, String name) {
		super();
		this.name= name;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
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
		Symptom other = (Symptom) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Symptoms [id=" + id + ", name=" + name + ", episodes=" + episodes + "]";
	}

}
