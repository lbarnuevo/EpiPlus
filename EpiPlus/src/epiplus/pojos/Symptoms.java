package epiplus.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symptoms implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5541692385945227457L;
	
	private Integer id;
	private String name;
	private List<Episodes> episodes; //Many to many relationship 
	
	public Symptoms() {
		super();
		episodes = new ArrayList<Episodes>();
	}

	public List<Episodes> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episodes> episodes) {
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
		Symptoms other = (Symptoms) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Symptoms [id=" + id + ", name=" + name + ", episodes=" + episodes + "]";
	}

}
