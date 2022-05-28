package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Symptom")
@XmlType(propOrder = { "id","name", "episodes"})
public class Symptom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5541692385945227457L;
	
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String name;
	
	@XmlElement(name = "Episode")
	@XmlElementWrapper(name = "episodes")
	private List<Episode> episodes; //Many to many relationship 
	
	public Symptom() {
		super();
		episodes = new ArrayList<Episode>();
	}

	public Symptom(Integer id, String name) {
		super();
		this.id=id;
		this.name= name;
		episodes = new ArrayList<Episode>();
	}
	
	public Symptom(String name) {
		super();
		this.name= name;
		episodes = new ArrayList<Episode>();
	}

	public void addEpisodes(Episode e) {
		if(!episodes.contains(e)) {
			episodes.add(e);	
		}
	}
	
	public void deleteEpisodes(Episode e) {
		if(episodes.contains(e)) {
			episodes.remove(e);	
		}
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
		return "SYMPTOM [ID = " + id + ", NAME = " + name + "]";
	}
}
