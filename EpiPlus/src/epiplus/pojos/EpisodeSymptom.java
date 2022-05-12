package epiplus.pojos;

import java.io.Serializable;
import java.util.Objects;

public class EpisodeSymptom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8368812583304410229L;
	
	private Integer severity; 
	private Episode episode;
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
		return "EpisodeSymptom [severity=" + severity + ", episode=" + episode + ", symptom=" + symptom + "]";
	}
}
