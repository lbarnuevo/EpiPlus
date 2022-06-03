package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.EmergencyContact;

public interface EmergencyContactManager {

	public void addEmergencyContact(EmergencyContact c);
	public void updateEmergencyContact(EmergencyContact c);
	public void deleteEmergencyContact(EmergencyContact c);
	public EmergencyContact getECbyId(Integer id);
	public List<EmergencyContact> getEmergencyContactsOfPatient(Integer pacId);
	public List<EmergencyContact> listsAllEmergencyContacts();
}
