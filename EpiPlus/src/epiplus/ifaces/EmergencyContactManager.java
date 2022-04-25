package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.EmergencyContact;

public interface EmergencyContactManager {

	public void addEmergencyContact(EmergencyContact c);
	public void deleteEmergencyContact(EmergencyContact c);
	public List<EmergencyContact> listsAllEmergencyContacts();
}
