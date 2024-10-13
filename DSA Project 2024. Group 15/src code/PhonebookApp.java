package pba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class PhonebookApp {
    private Phonebook phonebook;
    private LinkedHashSet<Contact> recentContacts; // To track recently accessed contacts
    private JFrame frame;
    private JTextField searchField;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortOptions; // ComboBox for sorting options

    public PhonebookApp() {
        phonebook = new Phonebook();
        recentContacts = new LinkedHashSet<>(); // Maintains recent contacts with unique entries

        // Preload some data
        phonebook.addContact("Maria Shikongo", "0816352537");
        phonebook.addContact("Bobby Beukes", "0856543210");
        phonebook.addContact("Sam Tjombonde", "0815552323");
        phonebook.addContact("Nandi Gowaseb", "0815678901"); // Duplicate name for testing
        phonebook.addContact("Furnmart", "0616352537");
        phonebook.addContact("James Furgeson", "0858244692");
        phonebook.addContact("Konrad Coetzee", "0815332564");
        phonebook.addContact("Chanel Hondjera", "0818378435"); // Duplicate name for testing

        
        
        frame = new JFrame("Phonebook Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create UI components
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add Contact");
        addButton.setPreferredSize(new Dimension(120, 30));

        JButton displayButton = new JButton("Display All Contacts");
        displayButton.setPreferredSize(new Dimension(150, 30));

        JButton recentButton = new JButton("Recent Contacts");
        recentButton.setPreferredSize(new Dimension(150, 30));

        // Sorting options (ComboBox)
        String[] sortingOptions = {"A-Z", "Z-A", "By Phone Number"};
        sortOptions = new JComboBox<>(sortingOptions);

        // Table model for displaying contacts
        String[] columnNames = {"Name", "Phone Number"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Update and Delete buttons
        JButton updateButton = new JButton("Update Selected Contact");
        JButton deleteButton = new JButton("Delete Selected Contact");

        // Set Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Search panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(searchField, gbc);

        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Sorting panel
        JPanel sortPanel = new JPanel();
        sortPanel.add(new JLabel("Sort by:"));
        sortPanel.add(sortOptions);
        sortPanel.add(recentButton);
        gbc.gridy = 2;
        panel.add(sortPanel, gbc);

        // Table
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(contactTable), gbc);

        // Action buttons
        JPanel actionPanel = new JPanel();
        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);
        gbc.gridy = 4;
        gbc.weighty = 0;
        panel.add(actionPanel, gbc);

        frame.getContentPane().add(panel);

        // Button actions

        // Add a contact
        addButton.addActionListener(e -> showAddContactDialog());

        // Search for contacts
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            ArrayList<Contact> matchingContacts = phonebook.searchContacts(query);
            tableModel.setRowCount(0); // Clear previous entries

            if (!matchingContacts.isEmpty()) {
                for (Contact contact : matchingContacts) {
                    tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber()});
                    addRecentContact(contact); // Add to recent contacts
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No contacts found for: " + query);
            }
        });

        // Sorting contacts based on selection in the combo box
        sortOptions.addActionListener(e -> {
            String sortOption = (String) sortOptions.getSelectedItem();
            switch (sortOption) {
                case "A-Z":
                    phonebook.sortContactsByName(true);  // Sort A-Z
                    break;
                case "Z-A":
                    phonebook.sortContactsByName(false); // Sort Z-A
                    break;
                case "By Phone Number":
                    phonebook.sortContactsByPhoneNumber(); // Sort by phone number
                    break;
            }
            refreshContactTable(); // Refresh the table to display sorted contacts
        });

        // Show recently accessed contacts
        recentButton.addActionListener(e -> {
            tableModel.setRowCount(0); // Clear previous entries
            if (!recentContacts.isEmpty()) {
                for (Contact contact : recentContacts) {
                    tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber()});
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No recent contacts available.");
            }
        });

        // Update selected contact
        updateButton.addActionListener(e -> {
            int selectedIndex = contactTable.getSelectedRow();
            if (selectedIndex != -1) {
                String currentName = (String) tableModel.getValueAt(selectedIndex, 0);
                String currentPhone = (String) tableModel.getValueAt(selectedIndex, 1);
                showUpdateContactDialog(currentName, currentPhone);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a contact to update.");
            }
        });

        // Delete selected contact with confirmation
        deleteButton.addActionListener(e -> {
            int selectedIndex = contactTable.getSelectedRow();
            if (selectedIndex != -1) {
                String nameToDelete = (String) tableModel.getValueAt(selectedIndex, 0);
                int confirmation = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to delete contact: " + nameToDelete + "?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmation == JOptionPane.YES_OPTION) {
                    phonebook.deleteContact(nameToDelete);
                    refreshContactTable();
                    JOptionPane.showMessageDialog(frame, "Deleted contact: " + nameToDelete);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a contact to delete.");
            }
        });

        // Show frame
        frame.setVisible(true);

        // Populate the table with initial data
        refreshContactTable();
    }

    // Add a recently accessed contact to the recentContacts set
    private void addRecentContact(Contact contact) {
        if (recentContacts.size() == 5) { // Keep only 5 recent contacts
            Contact first = recentContacts.iterator().next();
            recentContacts.remove(first);
        }
        recentContacts.add(contact);
    }

    // Show a dialog to add a new contact
    private void showAddContactDialog() {
        JDialog addContactDialog = new JDialog(frame, "Add Contact", true);
        addContactDialog.setLayout(new GridLayout(3, 2));

        JTextField nameField = new JTextField(15);
        JTextField phoneField = new JTextField(15);

        addContactDialog.add(new JLabel("Name:"));
        addContactDialog.add(nameField);
        addContactDialog.add(new JLabel("Phone Number:"));
        addContactDialog.add(phoneField);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addContactDialog.add(addButton);
        addContactDialog.add(cancelButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                phonebook.addContact(name, phone);
                refreshContactTable();
                addRecentContact(new Contact(name, phone)); // Add to recent contacts
                addContactDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(addContactDialog, "Please enter both name and phone number.");
            }
        });

        cancelButton.addActionListener(e -> addContactDialog.dispose());

        addContactDialog.pack();
        addContactDialog.setLocationRelativeTo(frame);
        addContactDialog.setVisible(true);
    }

    // Show a dialog to update a contact
    private void showUpdateContactDialog(String oldName, String currentPhone) {
        JDialog updateContactDialog = new JDialog(frame, "Update Contact", true);
        updateContactDialog.setLayout(new GridLayout(3, 2));

        JTextField nameField = new JTextField(oldName);
        JTextField phoneField = new JTextField(currentPhone);

        updateContactDialog.add(new JLabel("Name:"));
        updateContactDialog.add(nameField);
        updateContactDialog.add(new JLabel("Phone Number:"));
        updateContactDialog.add(phoneField);

        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        updateContactDialog.add(updateButton);
        updateContactDialog.add(cancelButton);

        updateButton.addActionListener(e -> {
            String newName = nameField.getText();
            String newPhone = phoneField.getText();
            if (!newName.isEmpty() && !newPhone.isEmpty()) {
                boolean updated = phonebook.updateContact(oldName, newName, newPhone);
                if (updated) {
                    refreshContactTable();
                    addRecentContact(new Contact(newName, newPhone)); // Add to recent contacts
                    updateContactDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(updateContactDialog, "Failed to update contact.");
                }
            } else {
                JOptionPane.showMessageDialog(updateContactDialog, "Please enter both name and phone number.");
            }
        });

        cancelButton.addActionListener(e -> updateContactDialog.dispose());

        updateContactDialog.pack();
        updateContactDialog.setLocationRelativeTo(frame);
        updateContactDialog.setVisible(true);
    }

    // Refresh the contact table
    private void refreshContactTable() {
        tableModel.setRowCount(0);
        ArrayList<Contact> contacts = phonebook.getAllContacts();
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber()});
        }
    }

    public static void main(String[] args) {
        new PhonebookApp();
    }
}
