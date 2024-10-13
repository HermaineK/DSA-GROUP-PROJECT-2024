package pba;

import java.util.ArrayList;

public class Phonebook {
    private LinkedList contacts;

    public Phonebook() {
        contacts = new LinkedList();
    }

    public void addContact(String name, String phoneNumber, boolean isFavorite, String group) {
        contacts.add(new Contact(name, phoneNumber, isFavorite, group));
    }

    public Contact searchContact(String name) {
        return contacts.search(name);
    }

    public boolean deleteContact(String name) {
        return contacts.delete(name);
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts.toArrayList();
    }

    public ArrayList<Contact> getFavorites() {
        return contacts.getFavorites();
    }

    public ArrayList<Contact> searchContactsByGroup(String groupName) {
        return contacts.searchByGroup(groupName);
    }

    public void sortContactsByName() {
        contacts.sortByName();
    }

    public void sortContactsByPhoneNumber() {
        contacts.sortByPhoneNumber();
    }
}
