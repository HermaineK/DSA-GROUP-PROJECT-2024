Data Structures and Algorithmns Project

Project Name: Phonebook Application

Project Overview

The Phonebook Application is a Java-based desktop application built using Swing that allows users to manage their contacts. It supports features such as adding, updating, deleting, searching, and sorting contacts, including advanced options like handling favorite contacts, organizing contacts into groups, and switching between light and dark modes. The application is designed with a graphical user interface (GUI) and provides an intuitive user experience for managing a digital phonebook.


Features

1.Add Contact

-Allows the user to add a new contact by providing the contact’s name, phone number, favorite status, and group.

-If marked as a favorite, the contact is displayed with a checkmark (✔).

2.Update Contact

-The user can select a contact from the table and update its details such as name, phone number, favorite status, and group.

3.Delete Contact

-The user can select a contact from the table and delete it from the phonebook.

4.Search Contacts

-Search contacts by name or group.

-Displays all contacts with the same name if duplicates exist.

5.Display All Contacts

-Shows all contacts in the phonebook in a table format with columns for name, phone number, favorite status, and group.

6.Favorite Contacts

-Displays favorite contacts when the "Show Favorites" button is clicked.

-Favorite contacts are marked with a checkmark (✔).

7.Group Contacts

-Contacts can be assigned to a group such as “Family,” “Work,” etc.

-The group name is displayed along with the contact’s details.

8.Light and Dark Mode

-Users can toggle between light and dark mode for the application.

-Dark mode turns the entire JFrame dark, and light mode returns it to the default color scheme.

9.Sorting

-Contacts can be sorted by:

-First 3 letters of their name.

-First 5 digits of their phone number.

10.Multiple Contact Handling

-If multiple contacts share the same name, all are displayed when searched.


Code Structure

The project is divided into the following classes, each representing different components of the phonebook application:

1.Contact Class

-Represents a contact in the phonebook.

-Contains fields for the name, phone number, favorite status, and group.

-Provides getter and setter methods for the fields.

2.Node Class

-Represents a node in the linked list.

-Contains a Contact object and a reference to the next node.

3.LinkedList Class

Implements a linked list to manage contacts.

-Provides methods to add, delete, update, and sort contacts.

-Includes an insertion sort algorithm to sort contacts by name or phone number.

4.Phonebook Class

-Acts as the main data manager for contacts.

-Utilizes the LinkedList to store and manipulate contacts.

-Provides methods to add, delete, search, and retrieve contacts, as well as to manage favorite and grouped contacts.

5.PhonebookApp Class

-The main GUI class built with Java Swing.

-Manages the display and interaction of the phonebook on a JFrame.

-Handles user actions such as adding, updating, deleting, and searching contacts.

-Switches between light and dark mode.

-Uses a JTable to display contacts in a tabular format.


Algorithms and Functions

1.Insertion Sort (for Sorting Contacts)

-Sorts the contacts in the linked list based on either the first 3 letters of the contact’s name or the first 5 digits of the contact’s phone number.

-The algorithm iterates through the list, inserting each node into the correct position in a new sorted list.

2.Search Contacts

-The search function checks for multiple contacts with the same name and returns all matching entries.

3.Dark and Light Mode Toggle

-Dynamically changes the background colors of the entire JFrame and its components.


Technical Stack

1.Language

-Java

2.GUI Framework

-Java Swing

-Used for creating the graphical user interface, including windows, buttons, tables, and dialogs.

3.Data Structures

-Linked List:

-Used to store and manage the contacts dynamically.

-Supports easy insertion, deletion, and traversal of contacts.

-ArrayList

-Used when returning the list of contacts (e.g., when retrieving all contacts or searching for contacts).

4.Design Patterns

-Model-View-Controller (MVC): The code loosely follows the MVC pattern where:

-The Contact class represents the Model.

-The PhonebookApp class acts as the View and Controller, handling user input and displaying data.

-The Phonebook and LinkedList classes function as the data manager or backend logic.


Usage Instructions

1.Running the Application

-Clone or download the repository.

-Compile the Java files.

-Run the PhonebookApp class.

2.Using the Features

-Add contacts using the "Add Contact" button.

-Search contacts by entering a name in the search bar and clicking "Search."

-Update or delete a contact by selecting a contact in the table and clicking the respective buttons.

-Toggle between dark and light mode using the toggle button.

-View favorite contacts by clicking "Show Favorites."

-View all contacts and grouped contacts using the display buttons.


CONTRIBUTORS

Leslie DT  Langendorf

Denzel MM Simataa

Kabo N Kabuna

Penny Kashidulika

Hemaine Kharugas
