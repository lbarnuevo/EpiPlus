package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;
import epiplus.pojos.Doctor;

public interface AllergyManager {

	void addAllergy(Allergy a);

	void deleteAllergy(Allergy a);

	List<Allergy> listAllAllergies();

	Allergy getAllergyByName(String nameall);

	Allergy getAllergyById(Integer choiceAllergy);

	
	
}
