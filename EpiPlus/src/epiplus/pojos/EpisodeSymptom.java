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
@XmlRootElement(name = "EpisodeSymptom")
@XmlType(propOrder = { "severity", "episode","symptom"} )
public class EpisodeSymptom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8368812583304410229L;
	
	@XmlAttribute
	private Integer severity; 
	@XmlElement
	private Episode episode;
	@XmlElement
	private Symptom symptom;
	
	public EpisodeSymptom() {
		super();
	}
	
	public EpisodeSymptom(Episode episode, Symptom symptom,Integer severity) {
		super();
		this.episode=episode;
		this.symptom=symptom;
		this.severity= severity;
	}
	
	public EpisodeSymptom(Integer severity) {
		super();
		this.episode=null;
		this.symptom=null;
		this.severity= severity;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(episode, symptom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EpisodeSymptom other = (EpisodeSymptom) obj;
		return Objects.equals(episode, other.episode) && Objects.equals(symptom, other.symptom);
	}

	@Override
	public String toString() {
		return "Severity = " + severity;
	}
}
