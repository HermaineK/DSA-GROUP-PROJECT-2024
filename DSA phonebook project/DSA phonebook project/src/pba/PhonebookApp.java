package pba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PhonebookApp {
    private Phonebook phonebook;
    private JFrame frame;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private boolean darkMode = false; // Dark mode flag

    public PhonebookApp() {
        phonebook = new Phonebook();

        frame = new JFrame("Phonebook Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        // Create UI components
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JTextField groupSearchField = new JTextField(15);
        JButton groupSearchButton = new JButton("Search by Group");
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortByNumberButton = new JButton("Sort by Number");

        // Buttons
        JButton addButton = new JButton("Add Contact");
        JButton displayButton = new JButton("Display All Contacts");
        JButton favoriteButton = new JButton("Show Favorites");
        JButton deleteButton = new JButton("Delete Contact");
        JButton updateButton = new JButton("Update Contact");
        JButton darkModeButton = new JButton("Toggle Dark Mode");

        // Table model for displaying contacts
        String[] columnNames = {"Name", "Phone Number", "Favorite", "Group"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);

        // Panel setup
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(searchField, gbc);
        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(groupSearchField, gbc);
        gbc.gridx = 1;
        panel.add(groupSearchButton, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(favoriteButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(sortByNameButton);
        buttonPanel.add(sortByNumberButton);
        buttonPanel.add(darkModeButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(contactTable), gbc);

        frame.getContentPane().add(panel);

        // Button Actions
        addButton.addActionListener(e -> showAddContactDialog());

        searchButton.addActionListener(e -> {
            String searchName = searchField.getText();
            Contact contact = phonebook.searchContact(searchName);
            if (contact != null) {
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.isFavorite() ? "✔" : "", contact.getGroup()});
            } else {
                JOptionPane.showMessageDialog(frame, "Contact not found!");
            }
        });

        groupSearchButton.addActionListener(e -> {
            String groupName = groupSearchField.getText();
            ArrayList<Contact> groupContacts = phonebook.searchContactsByGroup(groupName);
            tableModel.setRowCount(0); // Clear table
            for (Contact contact : groupContacts) {
                tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.isFavorite() ? "✔" : "", contact.getGroup()});
            }
            if (groupContacts.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No contacts found in this group!");
            }
        });

        displayButton.addActionListener(e -> refreshContactTable());

        favoriteButton.addActionListener(e -> showFavorites());

        deleteButton.addActionListener(e -> deleteSelectedContact());

        updateButton.addActionListener(e -> updateSelectedContact());

        sortByNameButton.addActionListener(e -> {
            phonebook.sortContactsByName();
            refreshContactTable();
        });

        sortByNumberButton.addActionListener(e -> {
            phonebook.sortContactsByPhoneNumber();
            refreshContactTable();
        });

        darkModeButton.addActionListener(e -> toggleDarkMode());

        frame.setVisible(true);
    }

    // Show Add Contact Dialog
    private void showAddContactDialog() {
        JTextField nameField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JCheckBox favoriteCheckbox = new JCheckBox("Favorite");
        JTextField groupField = new JTextField(10);

        JPanel dialogPanel = new JPanel(new GridLayout(4, 2));
        dialogPanel.add(new JLabel("Name:"));
        dialogPanel.add(nameField);
        dialogPanel.add(new JLabel("Phone:"));
        dialogPanel.add(phoneField);
        dialogPanel.add(favoriteCheckbox);
        dialogPanel.add(new JLabel("Group:"));
        dialogPanel.add(groupField);

        int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "Add Contact", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            boolean isFavorite = favoriteCheckbox.isSelected();
            String group = groupField.getText();
            phonebook.addContact(name, phone, isFavorite, group);
            refreshContactTable();
        }
    }

    // Delete Selected Contact
    private void deleteSelectedContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) contactTable.getValueAt(selectedRow, 0);
            phonebook.deleteContact(name);
            refreshContactTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a contact to delete!");
        }
    }

    // Update Selected Contact
    private void updateSelectedContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow != -1) {
            String currentName = (String) contactTable.getValueAt(selectedRow, 0);
            Contact contact = phonebook.searchContact(currentName);
            if (contact != null) {
                JTextField nameField = new JTextField(contact.getName());
                JTextField phoneField = new JTextField(contact.getPhoneNumber());
                JCheckBox favoriteCheckbox = new JCheckBox("Favorite", contact.isFavorite());
                JTextField groupField = new JTextField(contact.getGroup());

                JPanel dialogPanel = new JPanel(new GridLayout(4, 2));
                dialogPanel.add(new JLabel("Name:"));
                dialogPanel.add(nameField);
                dialogPanel.add(new JLabel("Phone:"));
                dialogPanel.add(phoneField);
                dialogPanel.add(favoriteCheckbox);
                dialogPanel.add(new JLabel("Group:"));
                dialogPanel.add(groupField);

                int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "Update Contact", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String newName = nameField.getText();
                    String newPhone = phoneField.getText();
                    boolean isFavorite = favoriteCheckbox.isSelected();
                    String newGroup = groupField.getText();
                    phonebook.deleteContact(currentName); // Remove the old contact
                    phonebook.addContact(newName, newPhone, isFavorite, newGroup); // Add the updated contact
                    refreshContactTable();
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a contact to update!");
        }
    }

    // Refresh the contact table
    private void refreshContactTable() {
        tableModel.setRowCount(0); // Clear table
        for (Contact contact : phonebook.getAllContacts()) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.isFavorite() ? "✔" : "", contact.getGroup()});
        }
    }

    // Show favorite contacts
    private void showFavorites() {
        tableModel.setRowCount(0); // Clear table
        for (Contact contact : phonebook.getFavorites()) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.isFavorite() ? "✔" : "", contact.getGroup()});
        }
    }

    // Toggle Dark Mode
    private void toggleDarkMode() {
        darkMode = !darkMode;
        if (darkMode) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            contactTable.setBackground(Color.LIGHT_GRAY);
            contactTable.setForeground(Color.WHITE);
            tableModel.fireTableDataChanged();
        } else {
            frame.getContentPane().setBackground(Color.WHITE);
            contactTable.setBackground(Color.WHITE);
            contactTable.setForeground(Color.BLACK);
            tableModel.fireTableDataChanged();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhonebookApp::new);
    }
}
