package pba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LinkedList {
    private Node head;

    public LinkedList() {
        head = null;
    }

    public void add(Contact data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean delete(String name) {
        if (head == null) return false;

        if (head.getData().getName().equalsIgnoreCase(name)) {
            head = head.getNext();
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getName().equalsIgnoreCase(name)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Contact search(String name) {
        Node current = head;
        while (current != null) {
            if (current.getData().getName().equalsIgnoreCase(name)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public ArrayList<Contact> searchByGroup(String groupName) {
        ArrayList<Contact> groupContacts = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (current.getData().getGroup().equalsIgnoreCase(groupName)) {
                groupContacts.add(current.getData());
            }
            current = current.getNext();
        }
        return groupContacts;
    }

    public ArrayList<Contact> toArrayList() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Node current = head;
        while (current != null) {
            contacts.add(current.getData());
            current = current.getNext();
        }
        return contacts;
    }

    public ArrayList<Contact> getFavorites() {
        ArrayList<Contact> favorites = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (current.getData().isFavorite()) {
                favorites.add(current.getData());
            }
            current = current.getNext();
        }
        return favorites;
    }

    public void sortByName() {
        ArrayList<Contact> contacts = toArrayList();
        Collections.sort(contacts, Comparator.comparing(Contact::getName));
        head = null; // Clear the current list
        for (Contact contact : contacts) {
            add(contact);
        }
    }

    public void sortByPhoneNumber() {
        ArrayList<Contact> contacts = toArrayList();
        Collections.sort(contacts, Comparator.comparing(Contact::getPhoneNumber));
        head = null; // Clear the current list
        for (Contact contact : contacts) {
            add(contact);
        }
    }
}
