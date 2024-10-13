package pba;

import java.util.ArrayList;

public class LinkedList {
    private Node head;

    public LinkedList() {
        head = null; // Initializes an empty linked list
    }

    // Add a new contact to the end of the list
    public void add(Contact data) {
        Node newNode = new Node(data); // Create a new node with the contact
        if (head == null) {
            head = newNode; // If the list is empty, set the head to the new node
        } else {
            Node current = head;
            while (current.getNext() != null) { // Traverse to the end of the list
                current = current.getNext();
            }
            current.setNext(newNode); // Set the new node at the end of the list
        }
    }

    // Search for contacts by name or phone number
    public ArrayList<Contact> search(String query) {
        ArrayList<Contact> matchingContacts = new ArrayList<>();
        Node current = head;

        String lowerCaseQuery = query.toLowerCase(); // Convert query to lowercase for case-insensitive matching
        while (current != null) {
            Contact contact = current.getData();
            // Perform case-insensitive partial match for name or exact match for phone number
            if (contact.getName().toLowerCase().contains(lowerCaseQuery) || contact.getPhoneNumber().contains(query)) {
                matchingContacts.add(contact); // Add matching contact to the result list
            }
            current = current.getNext();
        }

        return matchingContacts; // Return the list of matching contacts
    }

    // Delete a contact by name
    public boolean delete(String name) {
        if (head == null) return false; // If the list is empty, return false

        if (head.getData().getName().equalsIgnoreCase(name)) { // If the head is the contact to be deleted
            head = head.getNext(); // Move the head to the next node
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getName().equalsIgnoreCase(name)) { // Found the contact to be deleted
                current.setNext(current.getNext().getNext()); // Skip the node to delete it
                return true;
            }
            current = current.getNext();
        }
        return false; // If no match was found, return false
    }

    // Update a contact by name
    public boolean update(String oldName, String newName, String newPhone) {
        ArrayList<Contact> contacts = search(oldName); // Search for the old name
        if (contacts.isEmpty()) {
            return false; // Return false if no contact is found
        }
        Contact contact = contacts.get(0); // Get the first matching contact
        contact.setName(newName); // Update the contact's name
        contact.setPhoneNumber(newPhone); // Update the contact's phone number
        return true; // Return true if the update was successful
    }

    // Convert the linked list to an ArrayList for easier manipulation
    public ArrayList<Contact> toArrayList() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Node current = head;
        while (current != null) {
            contacts.add(current.getData()); // Add each contact to the ArrayList
            current = current.getNext();
        }
        return contacts; // Return the ArrayList containing all contacts
    }

    // Set contacts from an ArrayList (used after sorting)
    public void setContactsFromArray(ArrayList<Contact> contactList) {
        head = null; // Clear the linked list
        for (Contact contact : contactList) {
            add(contact); // Re-add contacts in sorted order
        }
    }

    // Node class represents each element in the linked list
    private class Node {
        private Contact data;
        private Node next;

        public Node(Contact data) {
            this.data = data;
            this.next = null;
        }

        public Contact getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
