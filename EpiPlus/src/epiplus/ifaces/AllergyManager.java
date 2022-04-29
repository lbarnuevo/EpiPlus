package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;

public interface AllergyManager {

	void addAllergy(Allergy a);

	void deleteAllergy(Allergy a);

	List<Allergy> listAllAllergies();

	
	
}
