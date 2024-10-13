Data Structures and Algorithmns Project

Project Name: Phonebook Application


Project Overview
-This project is a simple Phonebook Application built using Java Swing for the graphical user interface (GUI) and linked list for data storage. It allows users to manage a list of contacts, including adding, updating, deleting, and searching for contacts. The contacts are displayed in a table, and the application provides options for both user interaction and data management.


Features

-Add Contact: Allows users to add a new contact by specifying the name and phone number.

-Search Contact: Enables users to search for a specific contact by name.

-Update Contact: Provides an option to update the details of a selected contact (name and phone number).

-Delete Contact: Allows users to delete a contact from the list.

-Display All Contacts: Lists all the contacts in the phonebook in a table view.

-Sorting Options: Users can sort contacts by name (A-Z, Z-A) or by phone number.

-Recently Accessed Contacts: Keeps track of the last five accessed contacts and displays them when requested.

-Preloaded Data: The application starts with some predefined contacts for demonstration purposes.


Code Structure

-PhonebookApp.java: The main class for the GUI logic and event handling.

-Phonebook.java: Handles the logic for managing contacts (add, update, delete, search).

-Contact.java: Defines the Contact class, which holds the contact details such as name and phone number.

-LinkedList.java:Custom implementation of a singly linked list for managing contact data.

-Node.java:Represents a node in the linked list.


Algorithms

-Linear Search:Used for searching contacts by name.

-Merge Sort:Used to sort contacts alphabetically by name in the linked list.


Functions

-addContact(String name, String phone): Adds a new contact to the linked list.

-searchContact(String name): Searches for a contact by name using linear search.

-deleteContact(String name): Deletes a contact from the linked list.

-updateContact(String oldName, String newName, String newPhone): Updates an existing contact.

-sortContactsByName(boolean ascending): Sorts contacts in alphabetical order or reverse alphabetical order.

-sortContactsByPhoneNumber(): Sorts contacts by phone number.

-refreshContactTable(): Updates the displayed contact list in the GUI by retrieving and sorting contacts.


Technologies Used

-Java: The programming language used for developing the application.

-Swing: A Java's GUI toolkit for building the graphical interface.

-JTable: Used to displaying contacts in a tabular format.

-JDialog: Used to add and update contacts through dialog boxes.

-Linked List: Uses singly linked list for storing and managing contacts.


Contributors

-Leslie DT Langendorf

-Denzel MM Simataa

-Kabo N Kabuna

-Penny Kashidulika

-Hermaine Kharugas

