package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;
import epiplus.pojos.Doctor;

public interface AllergyManager {

	public void addAllergy(Allergy a);
	public void deleteAllergy(Allergy a);
	public Allergy getAllergyByName(String nameall);
	public Allergy getAllergyById(Integer choiceAllergy);
	public List<Allergy> listAllAllergies();
}
