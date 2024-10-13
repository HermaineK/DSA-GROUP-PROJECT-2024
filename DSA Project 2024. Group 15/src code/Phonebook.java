package pba;

import java.util.ArrayList;
import java.util.Comparator;

public class Phonebook {
    private LinkedList contacts;

    public Phonebook() {
        contacts = new LinkedList(); // Initializes the linked list to store contacts
    }

    // Add a new contact
    public void addContact(String name, String phoneNumber) {
        contacts.add(new Contact(name, phoneNumber)); // Add the new contact to the linked list
    }

    // Search contacts by a query (name or phone number)
    public ArrayList<Contact> searchContacts(String query) {
        return contacts.search(query); // Search in the linked list and return matches
    }

    // Delete a contact by name
    public boolean deleteContact(String name) {
        return contacts.delete(name); // Delete the contact from the linked list
    }

    // Update a contact (search by old name, then update name and phone number)
    public boolean updateContact(String oldName, String newName, String newPhoneNumber) {
        return contacts.update(oldName, newName, newPhoneNumber); // Update contact information
    }

    // Sort contacts by name (either A-Z or Z-A depending on the 'ascending' flag)
    public void sortContactsByName(boolean ascending) {
        ArrayList<Contact> contactList = contacts.toArrayList(); // Convert linked list to ArrayList
        if (ascending) {
            // Sort contacts A-Z (case-insensitive)
            contactList.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        } else {
            // Sort contacts Z-A (reverse order)
            contactList.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        }
        contacts.setContactsFromArray(contactList); // Update linked list with sorted ArrayList
    }

    // Sort contacts by phone number
    public void sortContactsByPhoneNumber() {
        ArrayList<Contact> contactList = contacts.toArrayList(); // Convert linked list to ArrayList
        // Sort contacts by phone number
        contactList.sort(Comparator.comparing(Contact::getPhoneNumber));
        contacts.setContactsFromArray(contactList); // Update linked list with sorted ArrayList
    }

    // Get all contacts as an ArrayList (useful for displaying all contacts)
    public ArrayList<Contact> getAllContacts() {
        return contacts.toArrayList(); // Convert linked list to ArrayList and return
    }
}
